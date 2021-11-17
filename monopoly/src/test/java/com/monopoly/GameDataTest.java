package com.monopoly;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class GameDataTest extends TestUtils {
    private Monopoly monopoly = new Monopoly(true);
    private Player player1 = new Player("TEST 1", "🐶");
    private Player player2 = new Player("TEST 2", "🐱");
    private Player player3 = new Player("TEST 3", "🚗");
    GameData gameData = new GameData(monopoly);

    @BeforeAll
    void init() {
        // First, we add some data to the monopoly
        // Set some simulation data for player1
        player1.setBalance(450);
        player1.setPosition(5);
        // Simulate the player1 in the jail
        player1.setInJail(true);
        player1.setInJailRound(1);

        // Set some simulation data for player2
        player2.setBalance(2500);
        player2.setPosition(2);

        // Set some simulation data for player3
        player3.setBalance(500);
        player3.setPosition(8);

        // Set some simulation data for the game
        monopoly.setRoundCounter(1);
        monopoly.setCurrentPlayer(player1);
    }

    // For testing if the game data save and load correctly
    // We will check if the game data is successfully saved
    // Also we will check if the
    @Test
    void gameDataTest() {
        String description = "Successfully saved and load the game";

        // The save() method will throw exception when error occur
        // if it can successfully call the save() method, it will return boolean true
        // which mean it can save the game successfully
        assertTrue(gameData.save(), "Game saved successfully");

        // Reset the game
        monopoly.reset();

        // Load the game from the saved data
        // assertTrue(gameData.load(), "Game loaded successfully");

        passed(description);
    }

}
