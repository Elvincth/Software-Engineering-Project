
package com.monopoly;

import java.util.ArrayList;

import org.json.JSONObject;

public class GameData {
    Monopoly monopoly = null;

    GameData(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    public void save() {
        ArrayList<Player> players = monopoly.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.printf("%s %s %n", player.getToken(), player.getBalance());
        }

        System.out.println("Save");
    }

    public boolean load(String path) {
        return false;
    }
}
