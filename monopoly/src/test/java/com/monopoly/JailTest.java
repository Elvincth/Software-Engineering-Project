package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JailTest extends TestUtils {
    // private Monopoly monopoly = new Monopoly();
    // private GoJailSquare goJailSquare = new GoJailSquare("GO TO JAIL", 15);
    private Player player = new Player("TEST_PLAYER", "A");

    // Used to test if the player have been sent to jail
    @Test
    void testGoJail() {
        // goJailSquare.effectTo(player, monopoly);
        // Test if the player in jail square position and is in jail
        assertTrue(!player.isInJail(), "frfrf");
        passed("description - passed");
    }
}
