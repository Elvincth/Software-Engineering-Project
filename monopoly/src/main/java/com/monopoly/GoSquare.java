package com.monopoly;

public class GoSquare extends Square implements EffectSquareAPI {

    GoSquare(String name, int position) {
        super(name, position);
    }

    public void effectTo(Player player, Monopoly monopoly) {
        // if (player.getCurrentRound() > 0) {
        // player.addBalance(1500);

        // System.out.println("[GO] Passed GO +1500! \n");
        // }

    } // when user pass through the go square

}
