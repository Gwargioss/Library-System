package com.librarysystem;

import Book.Book;
import Cart.Cart;
import Cart.CartItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    void addAndCalculateTotal() {
        Book book = new Book("Title","Author",2024,true,10.0,"cat",5);
        Cart cart = new Cart();
        cart.addToCart(book);
        assertEquals(1, cart.getItems().size());
        cart.addToCart(book);
        assertEquals(1, cart.getItems().size());
        CartItem item = cart.getItems().get(0);
        assertEquals(2, item.getQuantity());
        assertEquals(20.0, cart.calculateTotal(), 0.001);
    }

    @Test
    void removeAndClear() {
        Book book = new Book("Title","Author",2024,true,15.0,"cat",3);
        Cart cart = new Cart();
        cart.addToCart(book);
        assertEquals(1, cart.getItems().size());
        cart.removeFromCart(book);
        // after removal, cart should be empty
        assertTrue(cart.getItems().isEmpty());
        cart.addToCart(book);
        cart.clearCart();
        assertTrue(cart.getItems().isEmpty());
    }
}
