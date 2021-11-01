package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JailTest extends TestUtils {
    private Monopoly monopoly = new Monopoly(true);
    private GoJailSquare goJailSquare = new GoJailSquare("GO TO JAIL", 15);
    private JailSquare jailSquare = new JailSquare("JAIL", 5);
    private Player player = new Player("TEST_PLAYER", "A");

    // Used to test go to jail square
    @Test
    void testGoJailSquare() {
        String description = "Player is in jail and position is 5 (jail square)";
        goJailSquare.effectTo(player, monopoly); //
        assertTrue(player.isInJail() && player.getPosition() == 5, description);
        passed(description);
    }

    // Used to test if the player have been out of the jail
    @Test
    void testOutJail() {
        String description = "Player is out of jail and in jail round is rest to 0";
        player.outOfJail(); // Set the player out of jail
        // Test if isInJail is true and jail round is rest to 0
        assertTrue(!player.isInJail() && player.getJailRound() == 0, description);
        passed(description);
    }

    @Test
    // Used to test if the user can successfully pay $150 to get out of the jail
    void testPayToOut() {
        final int DEFAULT_BAL = 500; // Give a default balance to the user
        int expectBalanceLeft = 350; // Expected balance after user paid $150 to get out o

        player.goToJail(); // Set the player to jail
        jailSquare.effectTo(player, monopoly); // Pass essential objects to jail square
        player.setBalance(DEFAULT_BAL);// Set player balance to $500
        jailSquare.payToOut();// Test pay to out method in jailSquare

        String description = "Player is out of jail and its balance is $" + player.getBalance();

        assertTrue(player.getBalance() == expectBalanceLeft, description); // If player balance equal to expected
                                                                           // balance left the test case is passed
        passed(description);
    }

}
