
package com.monopoly;

import org.json.JSONObject;

public class GameData {
    Monopoly monopoly = null;

    GameData(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    public void save() {
        System.out.println("Save");
    }

    public boolean load(String path) {
        return false;
    }
}
