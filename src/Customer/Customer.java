package Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Account.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Borrower.*;
import Cart.*;
import Book.*;
import Order.*;

public  class Customer extends Account implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Customer.class);
    private static int totalCustomerCount = 0;
    private Cart cart;
    private List<Order> orderHistory;
    private Borrower borrower_info;

    public Customer() {
        super();
        orderHistory=new ArrayList<>();
        this.cart = new Cart();
        totalCustomerCount++;
    }
    public void CreateBorrower(String name){
        borrower_info = new Borrower(name,getAccount_Id());
    }
//    public void viewAvailableBooks(List<Book> books) {
//        System.out.println("Available books:");
//        for (Book book : books) {
//            if (book.getStock() > 0) {
//                System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + " (Stock: " + book.getStock() + ")");
//            }
//        }
//    }
//    public void viewUnavailableBooks(List<Book> books) {
//        System.out.println("Unavailable books:");
//        for (Book book : books) {
//            if (book.getStock() == 0) {
//                System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + " (Out of stock)");
//            }
//        }
//    }
    public boolean searchBooksByAuthor(List<Book> books, String author)throws SearchNotFound {
        logger.info("Searching for books by author: '{}'", author);
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                logger.info("- {}", book.getTitle());
                return true;
            }
        }
        throw new SearchNotFound();
    }

    public void searchBooksByTitle(List<Book> books, String title) {
        logger.info("Searching for books with title: '{}'", title);
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                logger.info("- {}", book.getTitle());
                found = true;
            }
        }
        if (!found) {
            logger.info("No books found with title: '{}'", title);
        }
    }

    public void placeOrder() {
        if (cart.getItems().isEmpty()) {
            logger.info("Your cart is empty. Add books to the cart before placing an order.");
            return;
        }

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem(cartItem.getBook(), cartItem.getQuantity());
            orderItems.add(orderItem);
        }

        Order order = new Order(orderItems, this.getAccount_Id ());
        logger.info("Order placed successfully.");
        orderHistory.add(order);
        cart.clearCart();
    }
    public void displayBooksForPurchase(List<Book> books) {
        logger.info("Available Books for Purchase:");
        for (Book book : books) {
            logger.info("Title: {}, Price: {}, Stock: {}", book.getTitle(), book.getPrice(), book.getStock());
        }
    }

    public void addToCart(Book book) {
        cart.addToCart(book);
    }

    public void removeFromCart(Book book) {
        cart.removeFromCart(book);
    }

    public void viewCart() {
        cart.displayCart();
    }

    public Borrower GetBorrower_info(){
        return borrower_info;
    }

    public Cart getCart() {
        return cart;
    }

    public static void setTotalCustomerCount(int totalCustomerCount) {
        Customer.totalCustomerCount = totalCustomerCount;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public void setBorrower_info(Borrower borrower_info) {
        this.borrower_info = borrower_info;
    }

    public static int getTotalCustomerCount() {
        return totalCustomerCount;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public static int getCustomerCount() {
        return totalCustomerCount;
    }
    public void Payment(String CardholderName,String CardNumber)throws PaymentFailedException{

        if (CardholderName.isEmpty() || CardNumber.length() < 13) {
            throw new PaymentFailedException("Invalid Credit Card Details. Payment Failed.");
        }
        logger.info("Payment Successful by Credit Card.");
    }
    public void OrderHistory()throws NoOrdersFound{
        if(orderHistory.isEmpty()){
            throw new NoOrdersFound();
        }
        for(Order order:orderHistory){
            order.displayOrderDetails();
        }
    }



}
