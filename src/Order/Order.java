package Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Book.Book;
import Cart.CartItem;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Order.class);
    private static int orderCounter = 1;
    private int orderId;
    private List<OrderItem> items;
    private double totalAmount;
    private String status;

    public Order(List<OrderItem> items, String customerId) {
        this.orderId = orderCounter++;
        this.items = items;
        this.status = "PLACED";
        calculateTotalAmount();
        reduceStockForOrderItems();
    }

    private void calculateTotalAmount() {
        this.totalAmount = 0.0;
        for (OrderItem item : items) {
            this.totalAmount += item.getTotalPrice();
        }
    }

    private void reduceStockForOrderItems() {
        for (OrderItem item : items) {
            item.reduceStock();
        }
    }

    public int getOrderId() {
        return orderId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String newStatus) {
        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty.");
        }

        if (("PLACED".equals(this.status) && "SHIPPED".equals(newStatus)) ||
                ("SHIPPED".equals(this.status) && "DELIVERED".equals(newStatus))) {
            this.status = newStatus;
            logger.info("Status updated to '{}'.", newStatus);
        } else if ("DELIVERED".equals(this.status) && "CANCELLED".equals(newStatus)) {
            throw new IllegalStateException("Cannot cancel a delivered order.");
        } else if ("CANCELLED".equals(this.status)) {
            throw new IllegalStateException("Cannot change the status of a cancelled order.");
        } else {
            throw new IllegalStateException("Invalid status transition from " + this.status + " to " + newStatus);
        }
    }

    public void displayOrderDetails() {
        logger.info("Order ID: {}", orderId);
        logger.info("Status: {}", status);
        logger.info("The Items in Order:");
        for (OrderItem item : items) {
            logger.info("- {} (Quantity: {}, Total Price: ${})", item.getBook().getTitle(), item.getQuantity(), item.getTotalPrice());
        }
        logger.info("Total Order Amount: ${}", totalAmount);
    }
    public void removeItem(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Cannot remove a null book from the cart.");
        }
        for (OrderItem item : items)
        {
            if (item.getBook().getBookId().equals(book.getBookId())) {
                items.remove(item);
                logger.info("Removed '{}' from the cart.", book.getTitle());
                break; //I used return instead of break to exit from the entire for each loop as soon as the condition is true
            }
        }
        logger.warn("Book '{}' not found in the cart.", book.getTitle());
    }

}

