
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
            addPlayerObject.put("Name", player.getName()); // String type
            addPlayerObject.put("Token", player.getToken()); // String type
            addPlayerObject.put("Balance", Integer.toString(player.getBalance())); // Integer to String
            addPlayerObject.put("Position", Integer.toString(player.getPosition())); // Integer to String
            addPlayerObject.put("Current Round", Integer.toString(player.getCurrentRound())); // Integer to String
            addPlayerObject.put("In Jail Round", Integer.toString(player.getJailRound())); // Integer to String
            addPlayerObject.put("Get Lost Status", String.valueOf(player.getLost())); // Integer to String
;
            createPlayerObjectArray.add(addPlayerObject);
        }

        putMain.put("Player Details", createPlayerObjectArray);
        putMain.put("Game Round", monopoly.getGameRound());
        putMain.put("Current Player Index", monopoly.getCurrentPlayerIndex());

        // Write JSON file
        try (FileWriter file = new FileWriter("Gamedata.json")) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(putMain.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Game Saved");
    }

    public void load() {
        
    }
}
