package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class PlayerTest extends TestUtils {
    private Player player = new Player("TEST_PLAYER", "A");
    private PropertySquare propertySquare = new PropertySquare("Test1", 1, 300, 90, EColor.BLUE);
    private Monopoly monopoly = new Monopoly(true);

    // Before running each test cases we should set the default player balance to
    // 500
    @BeforeEach
    void setDefaultBalance() {
        player.setBalance(500);
    }

    // Check whether it has a correct balance after addition
    @Test
    void testAddBalance() {
        String description = "addBalance method run correctly";
        // Assume the added amount is 100
        assertTrue(player.addBalance(100) == 600, description); // 500 + 100 = 600
        passed(description);
    }

    // Check whether it has a correct balance after deduction
    @Test
    void testDeductBalance() {
        String description = "deductBalance method run correctly";
        // Assume the deducted amount is 100
        assertTrue(player.deductBalance(100) == 400, description); // 500 - 100 = 400
        passed(description);
    }

    // Check whether the setPosition method successfully set the player position
    @Test
    void setPositionTest() {
        String description = "setPosition method run correctly";
        player.setPosition(10);// Set the user position to 10
        assertTrue(player.getPosition() == 10, description);
        passed(description);
    }

    // Check whether did the player variables being rest after the player have set
    // to lost
    // the game
    // After lost the user balance should be set to 0
    // and the player should release all the owned property (if any)
    @Test
    void testSetToLost() {
        String description = "setToLost method run correctly";
        // Simulate the payer bought a property
        propertySquare.buy(player, monopoly);
        // Set the player to lost
        player.setToLost();
        // Check if the lost is true
        assertTrue(player.getLost(), description);
        // Check if player balance is 0
        assertTrue(player.getBalance() == 0, description);
        // Check if the player have released all the owned property
        assertTrue(player.getProperty().size() == 0, description);
        passed(description);
    }

    // TODO: check player lost

}
