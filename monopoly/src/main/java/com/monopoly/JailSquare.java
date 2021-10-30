package com.monopoly;

public class JailSquare extends Square implements EffectSquareAPI {
    private final String TAG = "[JAIL]";

    JailSquare(String name, int position) {
        super(name, position);
    }

    public void effectTo(Player player, Monopoly monopoly) {
        int[] rolledDice = monopoly.getDice().getRolled();

        // Handle the user in jail
        if (player.isInJail()) {
            System.out.println("HI JAIL");
            // If same the user is free to go
            if (rolledDice[0] == rolledDice[1]) {
                System.out.printf("%s Same dice! You're out of jail.\n", TAG);
            }

        } else {
            System.out.printf("%s You're just visiting.\n", TAG);
        }

        // if (player.getIsInJail() == true) {
        // // TODO add to ask player in jail to pay HKD150 to leave
        // Dice dice = monopoly.getDice();
        // dice.roll();
        // int dice1 = dice.getRolled()[0];
        // int dice2 = dice.getRolled()[1];
        // player.addJailRound();
        // if (player.getJailRound() < 3) {
        // if (dice1 == dice2) {
        // player.isOutJail();
        // dice.roll();
        // player.setPosition(dice.getTotal(), monopoly);
        // player.getJailRound();
        // }
        // } else {
        // System.out.println("[Jail] You have payed HKD150 to leave jail \n");
        // player.deductBalance(150);
        // player.setPosition(dice.getTotal(), monopoly);
        // }
        // }
    }
}
