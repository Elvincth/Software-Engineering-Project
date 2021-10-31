package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChanceTest extends TestUtils{
    private Monopoly monopoly = new Monopoly(true);
    ChanceSquare chanceSquare = new ChanceSquare("TEST_PLAYER", 12);
    private Player player = new Player("TEST_PLAYER", "A");
    int testedBalance = 5000;

    // for checking the balance will random deduct within 300 and add within 200
    @Test
    void testAddOrDeduct(){
        String description = "Deduct or Add corrected";
        player.setBalance(testedBalance);
        chanceSquare.effectTo(player, monopoly);
        assertTrue(player.getBalance() >= 4700 && player.getBalance() <= 5200, description);
        
        passed(description);
    }
    
}
