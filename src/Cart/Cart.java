package Cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.util.ArrayList;
import Book.*;
public class Cart implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Cart.class);
    private ArrayList<CartItem> items;
    public Cart() {
        items = new ArrayList<>();
    }

    public void addToCart(Book book) {

        if (book == null) {
            throw new IllegalArgumentException("Cannot add a null book to the cart.");
        }
        if (book.getStock() == 0) {
            throw new IllegalArgumentException("SORRY, That book is not available right now");
        }
        boolean flag=false;
        for (CartItem item : items) {
            if (item.getBook().getBookId().equals(book.getBookId()))
            {
                item.setQuantity(item.getQuantity() + 1);
                logger.info("Increased quantity of '{}' to {}.", book.getTitle(), item.getQuantity());
                flag=true;
                break;
            }
        }
        if(!flag){
            items.add(new CartItem(book, 1));
        }
    }

    public void removeFromCart(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Cannot remove a null book from the cart.");
        }
        for (CartItem item : items)
        {
            if (item.getBook().getBookId().equals(book.getBookId())) {
                items.remove(item);
                logger.info("Removed '{}' from the cart.", book.getTitle());
                break; //I used return instead of break to exit from the entire for each loop as soon as the condition is true
            }
        }
        logger.warn("Book '{}' not found in the cart.", book.getTitle());
    }


    public double calculateTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void clearCart() {
        items.clear();
        logger.info("Cart has been cleared.");
    }

    public void displayCart() {
        if (items.isEmpty()) {
            logger.info("The cart is empty.");
            return;
        }
        logger.info("Items in the cart:");
        for (CartItem item : items) {
            logger.info(item.toString());
        }
        logger.info("Total Price: ${}", String.format("%.2f", calculateTotal()));
        logger.info("Total Items: {}", items.size());
    }

    public ArrayList<CartItem> getItems() {
        return new ArrayList<>(items);
    }
}
