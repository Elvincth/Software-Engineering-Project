package com.monopoly;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RoundTest extends TestUtils {
    private Monopoly monopoly = new Monopoly(true);
    private int gameRound = 100;

    @Test
    void endGameCheckTest() {
        String description = "When the game round is 100 the game should end";
        assertTrue(monopoly.endGameCheck(gameRound), description);// endGameCheck will check whether the game is end or
                                                                  // not
        passed(description);
    }
    // Test if the game ended correctly after x round

}
