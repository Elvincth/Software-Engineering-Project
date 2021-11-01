package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class PlayerTest extends TestUtils {
    private Player player = new Player("TEST_PLAYER", "A");

    // Before running each test cases we should set the default player balance to
    // 500
    @BeforeEach
    void setDefaultBalance() {
        player.setBalance(500);
    }

    // check whether it has a correct balance after addition
    @Test
    void testAddBalance() {
        String description = "addBalance method run correctly";
        // Assume the added amount is 100
        assertTrue(player.addBalance(100) == 600, description); // 500 + 100 = 600
        passed(description);
    }

    // check whether it has a correct balance after deduction
    @Test
    void testDeductBalance() {
        String description = "deductBalance method run correctly";
        // Assume the deducted amount is 100
        assertTrue(player.deductBalance(100) == 400, description); // 500 - 100 = 400
        passed(description);
    }

    @Test
    void setPositionTest() {
        String description = "setPosition method run correctly";
        player.setPosition(10);
        assertTrue(player.getPosition() == 10, description); // 500 - 100 = 400
        passed(description);
    }

    @Test
    void testSetToLost() {
        String description = "setToLost method run correctly";
        player.setToLost();
        assertTrue(player.getLost(), description);
        assertTrue(player.getBalance() == 0, description);
        passed(description);
    }

}
