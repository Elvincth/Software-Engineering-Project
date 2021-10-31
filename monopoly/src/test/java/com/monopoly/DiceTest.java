package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;

public class DiceTest extends TestUtils {
    private Dice dice = new Dice();

    @Test
    void firstDiceTest() {
        String description = "First dice number is between 1 to 4";
        dice.roll();
        assertTrue(dice.getRolled()[0] < 5 && dice.getRolled()[0] > 0);
        passed(description);
    }

    @Test
    void secondDiceTest() {
        String description = "Second dice number is between 1 to 4";
        dice.roll();
        assertTrue(dice.getRolled()[1] < 5 && dice.getRolled()[1] > 0, description);
        passed(description);
    }

    @Test
    // Test the total sum of the dice is correct
    void totalDiceTest() {
        String description = "Two dice sum up is between 2 to 8 and correct";
        dice.roll();
        assertTrue(dice.getTotal() < 9 && dice.getTotal() > 1);
        assertTrue(dice.getTotal() == (dice.getRolled()[0] + dice.getRolled()[1]));
        passed(description);
    }

}
