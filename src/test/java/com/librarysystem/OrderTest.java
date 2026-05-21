package com.librarysystem;

import Book.Book;
import Order.Order;
import Order.OrderItem;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void orderReducesStockAndCalculatesTotal() {
        Book book = new Book("Title2","Author2",2024,true,12.5,"cat",10);
        OrderItem oi = new OrderItem(book, 3);
        Order order = new Order(Collections.singletonList(oi), "cust1");
        assertEquals(37.5, order.getTotalAmount(), 0.001);
        assertEquals(7, book.getStock());
    }

    @Test
    void invalidStatusTransitionThrows() {
        Book book = new Book("Title3","Author3",2024,true,8.0,"cat",5);
        OrderItem oi = new OrderItem(book, 1);
        Order order = new Order(Collections.singletonList(oi), "cust2");
        assertThrows(IllegalStateException.class, () -> order.setStatus("DELIVERED"));
    }
}
