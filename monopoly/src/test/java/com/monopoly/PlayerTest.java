package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest extends TestUtils{
    private Player player = new Player("Test1", "2");
    private int balance = 500;
    private int position = 0;
    private int inJailRound = 0;
    private int currentRound = 10;
    private boolean inJail = false;
    private String name = "";
    private String token = "";
    private boolean lost = false;
    private Monopoly monopoly = new Monopoly();
    

    @Test
    void addBalanceTest() {
        String description = "addBalance method run correctly";
        assertTrue(player.addBalance(100) == 600, description); //500 + 100 = 600
        passed(description);
    }

    @Test
    void deductBalanceTest(){
        String description = "deductBalance method run correctly";
        assertTrue(player.deductBalance(100) == 400, description); //500 - 100 = 400
        passed(description);
    }

    @Test
    void setPositionTest(){
        String description = "setPosition method run correctly";
        player.setPosition(10);
        assertTrue(player.getPosition() == 10, description); //500 - 100 = 400
        passed(description);
    }

    @Test
    void goToJailTest(){
        String description = "GoToJail method run correctly";
        player.goToJail();
        assertTrue(player.getPosition() == 5, description); //500 - 100 = 400
        assertTrue(player.getJailRound() == 1, description);
        assertTrue(player.isInJail() == true, description);
        passed(description);
    }
    @Test
    void setToLostTest(){
        String description = "setToLost method run correctly";
        player.setToLost();
        assertTrue(player.getLost(), description);
        assertTrue(player.getBalance() == 0, description);
        passed(description);
    }

}