package com.monopoly;

public class JailSquare extends Square implements EffectSquareAPI {
    private final String TAG = "[JAIL]";
    private Utils utils = new Utils();
    private Dice dice = new Dice(false);

    JailSquare(String name, int position) {
        super(name, position);
    }

    private void outOfJail(Player player, Monopoly monopoly, Boolean isPay) {
        // Set player out of jail
        player.setInJail(false);
        player.setJailRound(0);

        monopoly.display();

        System.out.printf("%s %s You're out of jail.%n%n", TAG, isPay ? "Paid $50!" : "Same dice!");

    }

    private void rollDiceGetOut(Player player, Dice dice) {
        int[] rolledDice = dice.getRolled();
        dice.roll();

        // If same the user is free to go
        if (rolledDice[0] == rolledDice[1]) {
            System.out.printf("%s Same dice! You're out of jail.%n%n", TAG);
        }
    }

    public void effectTo(Player player, Monopoly monopoly) {

        // Handle the user in jail
        if (player.isInJail()) {

            YesNo payQuestion = new YesNo(monopoly.scanner, "Do you want to pay $50 to get out of jail?");

            // Yes and have enough money
            if (payQuestion.ask() && player.getBalance() >= 50) {
                outOfJail(player, monopoly, true);
            } else if (payQuestion.ask() && player.getBalance() < 50) {
                System.out.printf("%s You don't have enough money, now roll the dice.%n%n", TAG);
                utils.delay(monopoly.SHORT_DELAY_TIME);
                rollDiceGetOut(player, dice);
            } else {
                rollDiceGetOut(player, dice);
            }

        } else {
            System.out.printf("%s You're just visiting.%n%n", TAG);
        }
    }
}
