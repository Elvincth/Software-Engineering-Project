package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {
    private Dice dice;

    @Test
    void Dice1Test() {
        dice = new Dice(false);

        dice.roll();

        assertTrue(dice.getRolled()[0] < 5 && dice.getRolled()[0] > 0, "Should be between 1 to 4");
    }

    @Test
    void Dice2Test() {
        dice = new Dice(false);

        dice.roll();

        assertTrue(dice.getRolled()[1] < 5 && dice.getRolled()[1] > 0, "Should be between 1 to 4");
    }

    @Test
    void mutiDiceTest(){
        dice = new Dice(false);

        dice.roll();

        assertTrue(dice.getTotal() < 9 && dice.getTotal() > 0,  "Two dice should be between 1 to 9");
    }

}
