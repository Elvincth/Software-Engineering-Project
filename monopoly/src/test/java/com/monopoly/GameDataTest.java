package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class GameDataTest extends TestUtils{

    private Monopoly monopoly = new Monopoly(true);
    GameData gameData = new GameData(monopoly);

    @Test
    void saveTest() {

        String description = "Save test correct";

        assertTrue(gameData.save(), description);

        passed(description);
    } 

    @Test
    void loadTest() throws IOException, ParseException{

        // for generating the save file
        gameData.save();

        String description = "Load test correct";

        assertTrue(gameData.load(), description);

        passed(description);
    }

}
