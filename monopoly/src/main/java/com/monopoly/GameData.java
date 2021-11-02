
package com.monopoly;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;

public class GameData {
    Monopoly monopoly = null;

    GameData(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    public void save() {
        ArrayList<Player> players = monopoly.getPlayers();
        JSONObject putMain = new JSONObject();
        JSONArray createPlayerObjectArray = new JSONArray();
        String arrayProperty[];

        // pushing player information
        for (int i = 0; i < players.size(); i++) {

            JSONObject addPlayerObject = new JSONObject();
            Player player = players.get(i);
            addPlayerObject.put("name", player.getName()); // String type
            addPlayerObject.put("token", player.getToken()); // String type
            addPlayerObject.put("balance", Integer.toString(player.getBalance())); // Integer to String

            // // // testing
            // JSONArray propertyArray = new JSONArray();
            // if (player.getProperty().size() > 0) {
            //     for(int j = 0; j < player.getProperty().size(); j++){
            //         propertyArray.add(player.getProperty().get(i).getName());
            //     }
            // }

            // addPlayerObject.put("property", propertyArray);

            // for (int j = 0; j < player.getProperty().size(); j++) {
            //     propertyArray.add(player.getProperty().get(i).getName());
            // }

            addPlayerObject.put("position", Integer.toString(player.getPosition())); // Integer to String
            addPlayerObject.put("currentRound", Integer.toString(player.getCurrentRound())); // Integer to String
            addPlayerObject.put("inJailRound", Integer.toString(player.getJailRound())); // Integer to String
            addPlayerObject.put("getLostStatus", String.valueOf(player.getLost())); // Integer to String

            createPlayerObjectArray.add(addPlayerObject);
        }

        putMain.put("playerDetails", createPlayerObjectArray);
        putMain.put("gameRound", monopoly.getGameRound());
        putMain.put("currentPlayerIndex", monopoly.getCurrentPlayerIndex());

        // Write JSON file
        try (FileWriter file = new FileWriter("Gamedata.json")) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(putMain.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Game Saved");
       
        // Player player = players.get(0);

        // System.out.println(player.getProperty().get(0).getName());
        
            
    }

    public void load() {

    }
}
