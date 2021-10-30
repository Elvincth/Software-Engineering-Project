
package com.monopoly;
import org.json.JSONObject;

public class GameData {
    Monopoly monopoly = null;

    GameData(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    public boolean save(JSONObject data, String path) {

        return false;
    }

    public boolean load(String path) {
        return false;
    }
}
