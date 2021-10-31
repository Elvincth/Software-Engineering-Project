package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {
    private Dice dice;

    @Test
    void firstDiceTest() {
        dice = new Dice(false);

        dice.roll();

        assertTrue(dice.getRolled()[0] < 5 && dice.getRolled()[0] > 0, "Should be between 1 to 4");
    }

    @Test
    void secondDiceTest() {
        dice = new Dice(false);

        dice.roll();

        assertTrue(dice.getRolled()[1] < 5 && dice.getRolled()[1] > 0, "Should be between 1 to 4");
    }

    @Test
    void doubleDiceTest() {
        dice = new Dice(false);

        dice.roll();

        assertTrue(dice.getTotal() < 9 && dice.getTotal() > 1, "Two dice should be between 2 to 8");
    }

}
