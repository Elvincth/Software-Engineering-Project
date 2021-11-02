package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class GameDataTest extends TestUtils{

    private Monopoly monopoly = new Monopoly(true);
    GameData gameData = new GameData(monopoly);

    // for checking is the game can be saved
    @Test
    void saveTest() {

        String description = "Save test correct";

        // The save() method will throw exception when error occur
        // if it can successfully call the save() method, it will return boolean true
        // which mean it can save the game successfully
        assertTrue(gameData.save(), description);

        passed(description);
    } 

    @Test
    void loadTest() throws IOException, ParseException{

        // for generating the save file first then load back for getting data
        gameData.save();

        String description = "Load test correct";

        // The load() method will throw exception when error occur
        // it will print out some of the outdata that is get from the GameData.json file and also return true boolean value
        // which mean it can successful get the data from the json file
        assertTrue(gameData.load(), description);

        passed(description);
    }

}
