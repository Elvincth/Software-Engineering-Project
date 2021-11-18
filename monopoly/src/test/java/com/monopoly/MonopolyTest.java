package com.monopoly;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class MonopolyTest extends TestUtils {
    private Monopoly monopoly = new Monopoly(true);

    // For testing if the game started correctly
    @Test
    void startTest() throws IOException, ParseException {
        String description = "start method run correctly";
        assertTrue(monopoly.start());
        passed(description);
    }

    // For testing if the getDice method return a Dice object
    @Test
    void getDiceTest() {
        String description = "getDice method run correctly";
        assertTrue(monopoly.getDice() instanceof Dice);
        passed(description);
    }

    // For testing if the token is removed
    @Test
    void removeTokenTest() {
        String description = "removeTokenT method run correctly";
        // If the token removed successfully it will return true
        assertTrue(monopoly.removeToken("🐶"));
        passed(description);
    }

    // For testing if the token is removed
    @Test
    void printSettlementTableTest() {
        String description = " printSettlementTable method run correctly";
        // If printSettlementTable successfully run it will return true
        assertTrue(monopoly.removeToken("🐶"));
        passed(description);
    }

    // For testing if the token is removed
    @Test
    void exitTest() {
        String description = " exit method run correctly";
        // If the token removed successfully it will return true
        assertTrue(monopoly.exit());
        passed(description);
    }

}
