
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
import java.util.Iterator;

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
            JSONArray propertyArray = new JSONArray();
            if (player.getProperty().size() > 0) {
                // Loop through the player property
                for (int j = 0; j < player.getProperty().size(); j++) {
                    propertyArray.add(player.getProperty().get(j).getName());
                }
            }

            playerObj.put("balance", player.getBalance());
            playerObj.put("position", player.getPosition());
            playerObj.put("inJailRound", player.getInJailRound());
            playerObj.put("currentRound", player.getCurrentRound());
            playerObj.put("inJail", player.getInJail());

            playerObj.put("ownedProperty", propertyArray);

            playerObjArr.add(playerObj);
        }

        gameObj.put("gameRound", monopoly.getGameRound());
        gameObj.put("players", playerObjArr);
        gameObj.put("currentPlayerIndex", monopoly.getCurrentPlayerIndex());
        gameObj.put("currentPlayer", monopoly.getCurrentPlayer().getToken()); // Store the token of current player
        gameObj.put("roundCounter", monopoly.getRoundCounter());
        gameObj.put("numLostPlayer", monopoly.getNumLostPlayer());

        // Write JSON file
        try (FileWriter file = new FileWriter(FILE_NAME)) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(gameObj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Game Saved");
        // TODO: Back to main menu

        return true;
    }

    private int objToInt(Object object) {
        return ((Long) object).intValue();
    }

    // Load the game data
    public boolean load() throws IOException, ParseException {
        if (new File(FILE_NAME).exists()) {
            JSONParser parser = new JSONParser(); // create json parser
            Reader reader = new FileReader("GameData.json"); // reader for reading json file
            JSONObject gameObj = (JSONObject) parser.parse(reader); // Parse the file content
            System.out.println(TAG + " Restoring game save!");

            int gameRound = objToInt(gameObj.get("gameRound")); // get round variable in json file
            int currentPlayerIndex = objToInt(gameObj.get("currentPlayerIndex")); // get number of player variable in
                                                                                  // json file

            System.out.println("Game Round / Rounds: " + gameRound);

            System.out.println("Current Player Index: " + currentPlayerIndex);

            JSONArray playerArray = (JSONArray) gameObj.get("players");
            playerArray.forEach(pObj -> getJsonProperty((JSONObject) pObj));

        } else {
            System.out.println(TAG + " No game save!");
            // TODO: Back to main menu
        }

        return true;
    }

    private void getJsonProperty(JSONObject pObj) {

        JSONObject propertyObject = (JSONObject) pObj;

        JSONArray property = (JSONArray) propertyObject.get("property");

        Iterator<String> iterator = property.iterator();
        System.out.println("Number of Property: ");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
