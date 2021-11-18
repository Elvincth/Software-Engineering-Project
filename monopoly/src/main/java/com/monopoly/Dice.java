package com.monopoly;

public class Dice implements DiceAPI {
    private int[][] dice = { { 4, 2, 3 }, { 4, 3, 1 }, { 3, 2, 1 }, { 4, 1, 2 } };
    private int[] rolledDice = { 1, 1 };
    private Utils utils = new Utils();
    private boolean DEBUG = false;

    Dice(boolean DEBUG) {
        this.DEBUG = DEBUG;
    }

    Dice() {
        this.DEBUG = false;
    }

    private int rollDice() {
        int num1 = 0;
        int num2 = 0;
        num1 = (int) (Math.random() * 4);
        num2 = (int) (Math.random() * 3);

        // Rolling animation
        String a = "◰◳◲◱";

        long start = System.currentTimeMillis();

        // System.out.print("\033[2J"); // hide the cursor

        while (true) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    utils.clearScreen();
                    System.out.print(a.charAt(i) + " Rolling the dice");
                }

                utils.delay(DEBUG ? 10 : 80);
            }
            long now = System.currentTimeMillis();
            // stop after 0.5 seconds, say
            if (now - start >= 5)
                break;
        }

        utils.clearScreen();

        // System.out.print("\033[?25h"); // restore the cursor

        // return dice[num1][num2];
        return dice[num1][num2];
    }

    // Random the dice
    public void roll() {
        rolledDice[0] = rollDice();
        rolledDice[1] = rollDice();
    }

    // Display the dice
    @Generated
    public void display() {
        System.out.printf("◰ Dice one is %d, ◲ dice two is %d, the total is %d.", rolledDice[0], rolledDice[1],
                getTotal());
    }

    // Roll the dice and get the rolled of it
    public int[] getRolled() {
        return rolledDice;
    };

    public int getTotal() {
        return rolledDice[0] + rolledDice[1];
        // return 15;
    };
}
