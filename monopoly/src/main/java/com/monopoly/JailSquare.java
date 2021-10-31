package com.monopoly;

public class JailSquare extends Square implements EffectSquareAPI {
    private final String TAG = "[JAIL]";
    private Utils utils = new Utils();
    private Dice dice = new Dice(false);
    private Monopoly monopoly;
    private Player player;
    private final int OUT_JAIL_PRICE = 150;

    JailSquare(String name, int position) {
        super(name, position);
    }

    private void outOfJail(Boolean isPay) {
        // Set player out of jail
        player.outOfJail();

        monopoly.display();

        System.out.printf("%s %s You're out of jail.%n%n", TAG, isPay ? "Paid $150!" : "Same dice!");

    }

    private void rollDiceGetOut() {
        int[] rolledDice = dice.getRolled();
        dice.roll();

        dice.display();
        System.out.println();

        // If same the user is free to go
        if (rolledDice[0] == rolledDice[1]) {
            System.out.printf("%s Same dice! You're out of jail.%n%n", TAG);
            outOfJail(false);
        } else {
            System.out.printf("%s Uh oh! Dice not the same, can;t get out of jail.%n%n", TAG);
        }

    }

    public void payToOut() {
        outOfJail(true);
        player.deductBalance(OUT_JAIL_PRICE);
    }

    public void effectTo(Player player, Monopoly monopoly) {
        this.monopoly = monopoly;
        this.player = player;

        if (!monopoly.isTest()) {
            // Handle the user in jail
            if (player.isInJail()) {
                player.setJailRound(player.getJailRound() + 1); // Add jail round

                YesNo payQuestion = new YesNo(monopoly.scanner, "Do you want to pay $150 to get out of jail?");
                Boolean answer = payQuestion.ask();

                // Yes and have enough money
                if (answer && player.getBalance() >= OUT_JAIL_PRICE) {
                    payToOut();
                } else if (answer && player.getBalance() < OUT_JAIL_PRICE) {
                    System.out.printf("%s You don't have enough money, now roll the dice.%n%n", TAG);
                    utils.delay(monopoly.SHORT_DELAY_TIME);
                    rollDiceGetOut();
                } else {
                    rollDiceGetOut();
                }

            } else {
                System.out.printf("%s You're just visiting.%n%n", TAG);
            }
        }
    }
}
