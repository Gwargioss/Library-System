package com.librarysystem;

import Account.PasswordException;
import Account.UniqueUsernameException;
import Customer.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountValidationTest {

    @AfterEach
    void cleanup() {
        // Resetting static customer count and relying on unique usernames being isolated across tests
        Customer.setTotalCustomerCount(0);
    }

    @Test
    void validPasswordDoesNotThrow() {
        assertDoesNotThrow(() -> {
            Account.Account.ValidPassword("Abcdef1!");
        });
    }

    @Test
    void invalidPasswordThrows() {
        assertThrows(PasswordException.class, () -> Account.Account.ValidPassword("short"));
    }

    @Test
    void signupDuplicateUsernameThrows() throws Exception {
        Customer c1 = new Customer();
        c1.SignUp("user1", "Aa123456!", "Name", "a@b.com", "0123456789", "addr");
        Customer c2 = new Customer();
        assertThrows(UniqueUsernameException.class, () -> c2.SignUp("user1", "Aa123456!", "Name2", "a2@b.com", "0123456789", "addr2"));
    }
}
