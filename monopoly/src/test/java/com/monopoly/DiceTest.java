package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {
    private Dice dice;

    @Test
    void diceTest() {
        dice = new Dice(false);

        dice.roll();

    }
}
