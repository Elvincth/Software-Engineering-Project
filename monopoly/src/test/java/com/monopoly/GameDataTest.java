package com.monopoly;

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

    // For checking can the game can be saved
    @Test
    void saveTest() {
        String description = "Successfully saved the game";
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

        // The save() method will throw exception when error occur
        // if it can successfully call the save() method, it will return boolean true
        // which mean it can save the game successfully
        assertTrue(gameData.save(), description);

        passed(description);
    }

    @Test
    void loadTest() throws IOException, ParseException {
        
        // for generating the save file first then load back for getting data
        gameData.save();

        String description = "Load test correct";

        // The load() method will throw exception when error occur
        // it will print out some of the data that is get from the GameData.json file
        // and also return true boolean value
        // which mean it can successful get the data from the json file
        assertTrue(gameData.load(), description);

        passed(description);
    }

}
