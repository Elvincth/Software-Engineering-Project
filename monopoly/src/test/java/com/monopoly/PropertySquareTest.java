package com.monopoly;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropertySquareTest extends TestUtils {
    private PropertySquare propertySquare = new PropertySquare("Test1", 1, 300, 90, EColor.BLUE);
    private PropertySquare propertySquare2 = new PropertySquare("Test2", 2, 900, 90, EColor.BLUE);
    private Player player = new Player("TEST_PLAYER", "A");
    private Player player2 = new Player("TEST_PLAYER_2", "B");
    private Monopoly monopoly = new Monopoly(true);

    @Test
    void buyPropertyTest() {
        String description = "Property can be buy correctly(player with enough money))";
        propertySquare.buy(player, monopoly);
        // Owner should be the player who buy this property
        assertTrue(propertySquare.getOwner() == player, description);
        // The player default balance is HKD500, the price of the property is 300
        assertTrue(player.getBalance() == 500 - 300, description);
        // This property should have owner
        assertTrue(propertySquare.haveOwner() == true, description);
        passed(description);
    }

    @Test
    void effectToTest() {
        String description = "Property can be buy correctly (player without enough money)";
        propertySquare2.buy(player, monopoly);
        // The owner should be null (not enough money to buy property)
        assertTrue(propertySquare2.getOwner() != player, description);
        // Player balance should not change
        assertTrue(player2.getBalance() == 500, description);
        // The property should not have owner
        assertTrue(propertySquare2.haveOwner() == false, description);
        passed(description);
    }

}
