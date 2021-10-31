package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JailTest extends TestUtils {
    private Monopoly monopoly = new Monopoly(true);
    private GoJailSquare goJailSquare = new GoJailSquare("GO TO JAIL", 15);
    private Player player = new Player("TEST_PLAYER", "A");

    // Used to test if the player have been sent to jail if the player landed on Go
    // Jail square
    @Test
    void testGoJailSquare() {
        String description = "Player is in jail and position is 5 (jail square)";
        // Test if the player in jail square position and is in jail
        goJailSquare.effectTo(player, monopoly);
        assertTrue(player.isInJail() && player.getPosition() == 5, description);
        passed(description);
    }

    // Used to test if the player have been sent to jail
    @Test
    void testOutJail() {
        String description = "Player is out of jail and in jail round is rest to 0";
        player.outOfJail();
        assertTrue(!player.isInJail() && player.getJailRound() == 0, description);
        passed(description);
    }

    

}
