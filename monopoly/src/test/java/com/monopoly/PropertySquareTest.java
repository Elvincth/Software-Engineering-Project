package com.monopoly;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropertySquareTest extends TestUtils {

    private PropertySquare propertySquare = new PropertySquare("Test1", 1, 300, 90, EColor.BLUE);
    private PropertySquare propertySquare2 = new PropertySquare("Test2", 2, 900, 90, EColor.BLUE);
    private Player player = new Player("Test1", "2");
    private Player player2 = new Player("Test2", "3");
    private Monopoly monopoly = new Monopoly(true);

    @Test
    void buyPropertyTest() {
        String description = "property can be buy correctly(player with enough money))";
        propertySquare.buy(player, monopoly);
        assertTrue(propertySquare.getOwner() == player, description); // Owner should be the player who buy this
                                                                      // property
        assertTrue(player.getBalance() == 500 - 300, description); // The player default balance is HKD500, the price of
                                                                   // the property is 300
        assertTrue(propertySquare.haveOwner() == true, description);// This property should have owner
        passed(description);
    }

    @Test
    void effectToTest() {
        String description = "property can be buy correctly (player without enough money)";
        propertySquare2.buy(player, monopoly);
        assertTrue(propertySquare2.getOwner() != player, description);// The owner should be null(not enough money to buy
                                                                      // property)
        assertTrue(player2.getBalance() == 500, description);// Player balance should not change
        assertTrue(propertySquare2.haveOwner() == false, description);// The property should not have owner
        passed(description);
    }

}
