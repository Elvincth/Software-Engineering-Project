
package com.monopoly;

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
public class GameData {
    Monopoly monopoly = null;

    GameData(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    public boolean save() {
        ArrayList<Player> players = monopoly.getPlayers();
        JSONObject gameObject = new JSONObject();
        JSONArray createPlayerObjectArray = new JSONArray();

        // pushing player information
        for (int i = 0; i < players.size(); i++) {

            JSONObject playerObject = new JSONObject();
            Player player = players.get(i);
            playerObject.put("name", player.getName()); // String type
            playerObject.put("token", player.getToken()); // String type
            playerObject.put("balance", player.getBalance()); // Integer to String

            // Player property
            JSONArray propertyArray = new JSONArray();
            if (player.getProperty().size() > 0) {
                // Loop through the player property
                for (int j = 0; j < player.getProperty().size(); j++) {
                    propertyArray.add(player.getProperty().get(j).getName());
                }
            }

            playerObject.put("property", propertyArray);
            playerObject.put("position", player.getPosition());
            playerObject.put("currentRound", player.getCurrentRound());
            playerObject.put("inJailRound", player.getJailRound());
            playerObject.put("getLostStatus", player.getLost());

            createPlayerObjectArray.add(playerObject);
        }

        gameObject.put("playerDetails", createPlayerObjectArray);
        gameObject.put("gameRound", monopoly.getGameRound());
        gameObject.put("currentPlayerIndex", monopoly.getCurrentPlayerIndex());

        // Write JSON file
        try (FileWriter file = new FileWriter("Gamedata.json")) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(gameObject.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Game Saved");

        return true;
    }

    public boolean load() throws IOException, ParseException {
        // create json parser
        JSONParser parser = new JSONParser();

        // reader for reading json file
        Reader reader = new FileReader("GameData.json");

        // set json object for getting content of json file
        JSONObject gameObject = (JSONObject) parser.parse(reader);

        // get round variable in json file
        long gameRound = (long) gameObject.get("gameRound");
        System.out.println("Game Round / Rounds: " + gameRound);

        // get number of player variable in json file
        long currentPlayerIndex = (long) gameObject.get("currentPlayerIndex");
        System.out.println("Current Player Index: " + currentPlayerIndex);

        return true;
    }
}
