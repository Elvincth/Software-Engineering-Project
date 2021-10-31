package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class PlayerTest extends TestUtils {
    private Player player = new Player("Test1", "2");

    @BeforeEach
    // Before running each test cases we should set the default player balance to
    // 500
    void setDefaultBalance() {
        player.setBalance(500);
    }

    @Test
    void addBalanceTest() {
        String description = "addBalance method run correctly";
        assertTrue(player.addBalance(100) == 600, description); // 500 + 100 = 600
        passed(description);
    }

    @Test
    void deductBalanceTest() {
        String description = "deductBalance method run correctly";
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
    void setToLostTest() {
        String description = "setToLost method run correctly";
        player.setToLost();
        assertTrue(player.getLost(), description);
        assertTrue(player.getBalance() == 0, description);
        passed(description);
    }

}
