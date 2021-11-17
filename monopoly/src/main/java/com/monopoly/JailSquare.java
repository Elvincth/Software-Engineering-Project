package com.monopoly;

public class JailSquare extends Square implements EffectSquareAPI {
    private Utils utils = new Utils();
    private final String TAG = utils.ANSI_CYAN + "[JAIL]" + utils.ANSI_RESET;
    private Dice dice = new Dice(false);
    private Monopoly monopoly;
    private Player player;
    private final int OUT_JAIL_PRICE = 150;

    JailSquare(String name, int position) {
        super(name, position);
    }

    // isPay is the payment method
    // isPay = the user is pay to get out
    // if false means the user use same dice get out
    public void outOfJail(Boolean isPay) {
        // Set player out of jail
        player.outOfJail();

        player.movePosition(dice.getTotal());

        monopoly.display();

        System.out.printf("%s %s You're out of jail.%n%n", TAG, isPay ? "Paid $150!" : "Same dice!");

        monopoly.displayLanded();

    }

    private void rollDiceGetOut() {
        dice.roll();
        dice.display();
        System.out.println();
        checkRound(dice.getRolled());
    }

    public void checkRound(int[] rolledDice) {
        // If same the user is free to go
        if (rolledDice[0] == rolledDice[1]) {
            monopoly.display();
            System.out.printf("%s Same dice! You're out of jail.%n%n", TAG);

            outOfJail(false);
        } else {
            if (player.getInJailRound() < 3) {
                monopoly.display();
                System.out.printf("%s Uh oh! Dice not the same, cannot get out of jail.%n%n", TAG);
                player.setJailRound(player.getInJailRound() + 1);
            } else {
                payToOut();
            }
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
            if (player.getInJail()) {
                player.setJailRound(player.getInJailRound() + 1); // Add jail round

                YesNo payQuestion = new YesNo(monopoly.scanner, "Do you want to pay $150 to get out of jail?");
                Boolean answer = payQuestion.ask();

                // Yes and have enough money
                if (answer && player.getBalance() >= OUT_JAIL_PRICE) {
                    payToOut();
                    // The player have a chance to move and roll the dice
                    dice.roll();

                } else if (answer && player.getBalance() < OUT_JAIL_PRICE) {
                    System.out.printf("%s You don't have enough money, now roll the dice.%n%n", TAG);
                    utils.delay(2000); // TODO: monopoly.SHORT_DELAY_TIME
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
