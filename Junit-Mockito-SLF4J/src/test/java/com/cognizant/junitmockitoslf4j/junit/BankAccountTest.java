package com.cognizant.junitmockitoslf4j.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount(1000);
        System.out.println("BeforeEach Executed");
    }

    @AfterEach
    void tearDown() {
        account = null;
        System.out.println("AfterEach Executed");
    }

    @Test
    void testDeposit() {

        // Arrange
        double amount = 500;

        // Act
        account.deposit(amount);

        // Assert
        assertEquals(1500, account.getBalance());
    }

    @Test
    void testInitialBalance() {

        // Arrange
        double expected = 1000;

        // Act
        double actual = account.getBalance();

        // Assert
        assertEquals(expected, actual);
    }
}