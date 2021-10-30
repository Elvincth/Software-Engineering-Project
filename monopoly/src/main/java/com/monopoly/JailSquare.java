package com.monopoly;

public class JailSquare extends Square implements EffectSquareAPI {

    JailSquare(String name, int position) {
        super(name, position);
    }

    public void effectTo(Player player, Monopoly monopoly) {
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
