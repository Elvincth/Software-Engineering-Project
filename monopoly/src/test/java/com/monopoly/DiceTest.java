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

    // Test the total sum of the dice is correct
    @Test
    void totalDiceTest() {
        String description = "The sum of of two dice is between 2 to 8";
        dice.roll(); // Roll the dice
        assertTrue(dice.getTotal() < 9 && dice.getTotal() > 1);// Check if the sum range
        assertTrue(dice.getTotal() == (dice.getRolled()[0] + dice.getRolled()[1])); // Check if the sum is same
        passed(description);
    }

}
