package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChanceTest extends TestUtils {
    private Monopoly monopoly = new Monopoly(true);
    ChanceSquare chanceSquare = new ChanceSquare("Chance", 8);
    private Player player = new Player("TEST_PLAYER", "A");
    int testedBalance = 5000; // the balance of the test player to $5000

    // for checking the balance will random deduct within $300 and add within $200
    @Test
    void testAddOrDeduct() {
        String description = "Random deduct balance or random add balance correct";
        player.setBalance(testedBalance);
        chanceSquare.effectTo(player, monopoly);
        // calculating the balance in default $5000
        assertTrue(player.getBalance() >= 4700 && player.getBalance() <= 5200, description);

        // check whether the deduction amount will not over $300
        if (player.getBalance() < 5000) {
            System.out.println("Balance After Deducted: $" + player.getBalance());
            System.out.println("Which is larger or equal to $4700");
        } 
        // check whether the addition amount will not over $200 
        else if (player.getBalance() > 5000) {
            System.out.println("Balance After Added: $" + player.getBalance());
            System.out.println("Which is smaller or equal to $5200");
        }
        passed(description);
    }

    // for checking the ramdom number of deduction or addition can be divisible by 10
    @Test
    void testRandomNumber() {
        String description = "Random number divisible by 10 correct";
        player.setBalance(testedBalance);
        chanceSquare.effectTo(player, monopoly);
        
        // checking the random number can divisible by 10
        assertTrue(chanceSquare.getRandomMoney() % 10 == 0, description);

        System.out.println("The random number of money is: $" + chanceSquare.getRandomMoney());
        System.out.println("Which can divisible by 10");

        passed(description);
    }

}
