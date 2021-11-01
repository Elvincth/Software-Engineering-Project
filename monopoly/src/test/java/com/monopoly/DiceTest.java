package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest extends TestUtils {
    private Dice dice = new Dice();

    // Used to test the first rolled dice, the rolled dice number should be between
    // 1 to 4.
    // As the dice is a tetrahedral dice the possible values are 1,2,3,4 only
    @Test
    void firstDiceTest() {
        String description = "First dice number is between 1 to 4";
        dice.roll(); // Roll the dice
        assertTrue(dice.getRolled()[0] < 5 && dice.getRolled()[0] > 0); // Check the first dice range
        passed(description);
    }

    // Used to test the second rolled dice, the rolled dice number should be between
    // 1 to 4.
    // As the dice is a tetrahedral dice the possible values are 1,2,3,4 only
    @Test
    void secondDiceTest() {
        String description = "Second dice number is between 1 to 4";
        dice.roll(); // Roll the dice
        assertTrue(dice.getRolled()[1] < 5 && dice.getRolled()[1] > 0, description);// Check the second dice range
        passed(description);
    }

    // Test the dice.getTotal() method is correct
    // The method will return the sum of two dice
    @Test
    void totalDiceTest() {
        String description = "The sum of two dice is correct and its between 2 to 8";
        dice.roll(); // Roll the dice
        // Check the sum of two dice range
        assertTrue(dice.getTotal() < 9 && dice.getTotal() > 1, "Sum of two dice is between 2 to 8");
        // Check if the sum of two dice is correct
        assertTrue(dice.getTotal() == (dice.getRolled()[0] + dice.getRolled()[1]), "The sum of two dice is correct");
        passed(description);
    }

}
