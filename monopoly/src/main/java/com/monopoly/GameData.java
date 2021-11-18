
package com.monopoly;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class GameData extends Utils {
    private Monopoly monopoly = null;
    private final String TAG = ANSI_CYAN + "[DATA]" + ANSI_RESET;
    private final String FILE_NAME = "GameData.json";

    GameData(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    public boolean save() {
        ArrayList<Player> players = monopoly.getPlayers();
        JSONObject dataObj = new JSONObject();
        JSONObject gameObj = new JSONObject();
        JSONArray playerObjArr = new JSONArray();

        // pushing player information
        for (int i = 0; i < players.size(); i++) {

            JSONObject playerObj = new JSONObject();
            Player player = players.get(i);
            playerObj.put("name", player.getName()); // String type
            playerObj.put("token", player.getToken()); // String type
            playerObj.put("balance", player.getBalance()); // Integer to String

            // Store player property name
            JSONArray ownedPropertyArr = new JSONArray();
            if (player.getOwnedProperty().size() > 0) {
                // Loop through the player property
                for (int j = 0; j < player.getOwnedProperty().size(); j++) {
                    ownedPropertyArr.add(player.getOwnedProperty().get(j).getName());
                }
            }

            playerObj.put("balance", player.getBalance());
            playerObj.put("position", player.getPosition());
            playerObj.put("inJailRound", player.getInJailRound());
            playerObj.put("currentRound", player.getCurrentRound());
            playerObj.put("inJail", player.getInJail());
            playerObj.put("name", player.getName());
            playerObj.put("token", player.getToken());
            playerObj.put("lost", player.getLost());
            playerObj.put("ownedProperty", ownedPropertyArr);
            playerObjArr.add(playerObj);
        }

        // Put essential game data
        gameObj.put("gameRound", monopoly.getGameRound());
        gameObj.put("currentPlayerIndex", monopoly.getCurrentPlayerIndex());
        gameObj.put("currentPlayer", monopoly.getCurrentPlayer().getToken()); // Store the token of current player
        gameObj.put("roundCounter", monopoly.getRoundCounter());
        gameObj.put("numLostPlayer", monopoly.getNumLostPlayer());

        // Put all data in it
        dataObj.put("game", gameObj); // Add game data
        dataObj.put("players", playerObjArr); // Add player list

        // Write JSON file
        try (FileWriter file = new FileWriter(FILE_NAME)) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(dataObj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("The game have been saved! \n");
        printArt(ANSI_GREEN);

        delay(monopoly.SHORT_DELAY_TIME);

        return true;
    }

    public void printArt(String Color) {
        System.out.println(Color + " _________________");
        System.out.println("| | ___________ |o|");
        System.out.println("| | ___________ | |");
        System.out.println("| | ___________ | |");
        System.out.println("| | ___________ | |");
        System.out.println("| |_____________| |");
        System.out.println("|     _______     |");
        System.out.println("|    |       |   ||");
        System.out.println("|    |       |   V|");
        System.out.println("|____|_______|____|" + ANSI_RESET);
    }

    private int objToInt(Object object) {
        return ((Long) object).intValue();
    }

    // Load the game data
    public boolean load() throws IOException, ParseException {
        if (new File(FILE_NAME).exists()) {
            JSONParser parser = new JSONParser(); // create json parser
            Reader reader = new FileReader("GameData.json"); // reader for reading json file
            JSONObject dataObj = (JSONObject) parser.parse(reader); // Parse the file content
            JSONArray playerArr = (JSONArray) dataObj.get("players");
            JSONObject gameObj = (JSONObject) dataObj.get("game");

            restorePlayer(playerArr);// Restore the player list
            // Restore player before restoring the game data
            restoreGame(gameObj); // Restore essential data for the game
            // last phase restore property
            restoreProperty(playerArr);

            if (!monopoly.isTest()) {
                System.out.println(TAG + " Game have been loaded!"); // Message

                printArt(ANSI_GREEN);

                delay(monopoly.SHORT_DELAY_TIME);

                // Clear the screen
                clearScreen();

                // Continue the game play
                monopoly.nextTurn(true);

            }

        } else {
            System.out.println(TAG + " No game save found!");
            printArt(ANSI_RED);
            delay(monopoly.SHORT_DELAY_TIME);
            monopoly.start();
        }

        return true;
    }

    // Helper function
    // Return the player object by a token given
    private Player tokenToPlayer(String token) {
        ArrayList<Player> players = monopoly.getPlayers();
        Player player = null;

        for (int i = 0; i < players.size(); i++) {
            player = players.get(i);
            if (token.equals(player.getToken())) {
                break;
            }
        }

        return player;
    }

    // Helper function
    // Return the PropertySquare by its name
    private PropertySquare nameToProperty(String name) {
        Square[] squares = monopoly.getSquares();

        PropertySquare property = null;

        for (int i = 0; i < squares.length; i++) {
            // only target property square
            if (squares[i] instanceof PropertySquare && squares[i].getName().equals(name)) {
                property = (PropertySquare) squares[i];
                break;
            }
        }

        return property;

    }

    // Restore the property owners
    private void restoreProperty(JSONArray playerArray) {
        // loop through all players
        playerArray.forEach(item -> {
            JSONObject playerObj = (JSONObject) item;
            JSONArray ownedProperty = (JSONArray) playerObj.get("ownedProperty");
            String token = (String) playerObj.get("token");
            Player player = tokenToPlayer(token);

            for (int i = 0; i < ownedProperty.size(); i++) {
                String propertyName = (String) ownedProperty.get(i);
                // Add the owned property to the user
                // Restore the owned property list
                player.addProperty(nameToProperty(propertyName));

                // Restore the owner
                nameToProperty(propertyName).setOwner(player);
            }

        });

    }

    // Restore essential game data
    private void restoreGame(JSONObject gameObj) {
        Player currentPlayer = tokenToPlayer((String) gameObj.get("currentPlayer"));
        int currentPlayerIndex = objToInt(gameObj.get("currentPlayerIndex"));
        int roundCounter = objToInt(gameObj.get("roundCounter"));
        int gameRound = objToInt(gameObj.get("gameRound"));
        int numLostPlayer = objToInt(gameObj.get("numLostPlayer"));

        // Restore the game data
        monopoly.setCurrentPlayer(currentPlayer);
        monopoly.setCurrentPlayerIndex(currentPlayerIndex);
        monopoly.setRoundCounter(roundCounter);
        monopoly.setGameRound(gameRound);
        monopoly.setNumLostPlayer(numLostPlayer);
    }

    // Restore the player list
    private void restorePlayer(JSONArray playerArray) {
        ArrayList<Player> players = new ArrayList<Player>();

        playerArray.forEach(item -> {
            JSONObject playerObj = (JSONObject) item;

            int balance = objToInt(playerObj.get("balance"));
            int position = objToInt(playerObj.get("position"));
            int inJailRound = objToInt(playerObj.get("inJailRound"));
            int currentRound = objToInt(playerObj.get("currentRound"));
            boolean inJail = (Boolean) playerObj.get("inJail");
            String name = (String) playerObj.get("name");
            String token = (String) playerObj.get("token");
            boolean lost = (Boolean) playerObj.get("lost");
            // Array of string

            Player player = new Player(name, token);

            player.setBalance(balance);
            player.setPosition(position);
            player.setInJailRound(inJailRound);
            player.setCurrentRound(currentRound);
            player.setInJail(inJail);
            player.setLost(lost);

            // push the player into our player list
            players.add(player);

        });

        // Restore the player list
        monopoly.setPlayers(players);
    }

}
