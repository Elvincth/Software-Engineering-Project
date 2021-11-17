package com.monopoly;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GoTest extends TestUtils {
    private Player player = new Player("TEST_PLAYER", "A");
    private Dice dice = new Dice();

    @Test
    void passGoTest() {
        String description = "Player is pass the Go square and gain HKD1500 ";
        player.movePosition(19); // position 19 is the last square of the monopoly, therefore whether what number
                                 // of dice player roll,the player will pass the Go square
        dice.roll(); // Roll the dice
        player.movePosition(dice.getTotal()); // move the player, which will pass the Go Square (19(position of player)
                                              // +
                                              // 2(minium number of two dice) > 20(position of Go square))
        assertTrue(player.getBalance() == 2000, description); // 500(default balance of the player) + 1500(Money gain
                                                              // after player passed the Go)
        passed(description);
    }

    @Test
    void onGoTest() {
        String description = "Player is on the Go square and gain HKD1500 ";
        player.movePosition(20); // movePosition 20 mean player need to move 20 square which will back to the go
                                 // Square
        assertTrue(player.getBalance() == 2000, description); // 500(default balance of the player) + 1500(Money gain
                                                              // after player passed the Go)
        passed(description);
    }

}
