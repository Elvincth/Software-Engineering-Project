package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

public class GameDataTest extends TestUtils {
    private Monopoly monopoly = new Monopoly(true);
    private Player player1 = new Player("TEST 1", "🐶");
    private Player player2 = new Player("TEST 2", "🐱");
    private Player player3 = new Player("TEST 3", "🚗");
    GameData gameData = new GameData(monopoly);

    // For testing if the game data save and load correctly
    // We will check if the game data is successfully saved
    // Also we will check if the
    @Test
    void gameDataTest() throws IOException, ParseException {
        String description = "Successfully saved and load the game";

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
        // Simulate player 3 brought central and Shatin
        // Central
        player3.addProperty((PropertySquare) monopoly.getSquares()[1]);
        ((PropertySquare) monopoly.getSquares()[1]).setOwner(player3);
        // Shatin
        player3.addProperty((PropertySquare) monopoly.getSquares()[11]);
        ((PropertySquare) monopoly.getSquares()[11]).setOwner(player3);

        ArrayList<Player> players = new ArrayList<Player>();

        // Add player to the game
        players.add(player1);
        players.add(player2);
        players.add(player3);
        monopoly.setPlayers(players);

        // Set some simulation data for the game
        monopoly.setRoundCounter(1);
        monopoly.setCurrentPlayer(player1);

        // The save() method will throw exception when error occur
        // if it can successfully call the save() method, it will return boolean true
        // which mean it can save the game successfully
        assertTrue(gameData.save(), "Game saved successfully");

        // Reset the game
        monopoly.reset();

        // Load the game from the saved data
        assertTrue(gameData.load(), "Game loaded successfully");

        // The part below is to check if the game data is loaded to monopoly correctly
        assertTrue(monopoly.getPlayers().size() == 3, "Game loaded successfully");

        System.out.println(monopoly.getPlayers().get(0).getName());

        // Check player names, if it is same as our simulation data
        assertEquals(monopoly.getPlayers().get(0).getName(), player1.getName());
        assertEquals(monopoly.getPlayers().get(1).getName(), player2.getName());
        assertEquals(monopoly.getPlayers().get(2).getName(), player3.getName());

        // Check player tokens, if it is same as our simulation data
        assertEquals(monopoly.getPlayers().get(0).getToken(), player1.getToken());
        assertEquals(monopoly.getPlayers().get(1).getToken(), player2.getToken());
        assertEquals(monopoly.getPlayers().get(2).getToken(), player3.getToken());

        // Check player balance, if it is same as our simulation data
        assertEquals(monopoly.getPlayers().get(0).getBalance(), player1.getBalance());
        assertEquals(monopoly.getPlayers().get(1).getBalance(), player2.getBalance());
        assertEquals(monopoly.getPlayers().get(2).getBalance(), player3.getBalance());

        // Check player position, if it is same as our simulation data
        assertEquals(monopoly.getPlayers().get(0).getPosition(), player1.getPosition());
        assertEquals(monopoly.getPlayers().get(1).getPosition(), player2.getPosition());
        assertEquals(monopoly.getPlayers().get(2).getPosition(), player3.getPosition());

        // Check player owned property, if it is same as our simulation data (As player
        // 3 bought 2 property)
        assertEquals(monopoly.getPlayers().get(2).getOwnedProperty().get(0).getName(),
                player3.getOwnedProperty().get(0).getName());

        assertEquals(monopoly.getPlayers().get(2).getOwnedProperty().get(1).getName(),
                player3.getOwnedProperty().get(1).getName());

        // Check jail data if it is same as our simulation data
        assertEquals(monopoly.getPlayers().get(0).getInJail(), player1.getInJail());
        assertEquals(monopoly.getPlayers().get(1).getInJail(), player2.getInJail());
        assertEquals(monopoly.getPlayers().get(2).getInJail(), player3.getInJail());

        assertEquals(monopoly.getPlayers().get(0).getInJailRound(), player1.getInJailRound());
        assertEquals(monopoly.getPlayers().get(1).getInJailRound(), player2.getInJailRound());
        assertEquals(monopoly.getPlayers().get(2).getInJailRound(), player3.getInJailRound());

        // At last we check the game data
        // Check if the current round is same
        assertEquals(monopoly.getRoundCounter(), 1);
        assertEquals(monopoly.getCurrentPlayer().getToken(), player1.getToken());

        passed(description);
    }

}
