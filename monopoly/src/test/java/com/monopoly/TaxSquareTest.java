package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaxSquareTest extends TestUtils {
    private Monopoly monopoly = new Monopoly(true);
    private TaxSquare taxSquare = new TaxSquare("INCOME TAX", 3);
    private Player player = new Player("TEST_PLAYER", "A");

    @Test
    // Used to test if the square landed on tax square
    // did the tax paid correctly by the player
    void taxSquareTest() {
        String description = "Tax paid correctly by the player";
        final int DEFAULT_BAL = 500; // Give a default balance to the user
        // Player with $500 balance paid 10% of tax 500 - (500*0.1) = 450
        // Should left with $450 balance
        int expectBalanceLeft = 450;

        // Set player balance to $500
        player.setBalance(DEFAULT_BAL);
        // Simulate user landed on the tax square
        taxSquare.effectTo(player, monopoly);
        // If player balance is qual to expect balance left
        // that mean the tax is paid by the user correctly
        assertTrue(player.getBalance() == expectBalanceLeft);
        passed(description);
    }

}
