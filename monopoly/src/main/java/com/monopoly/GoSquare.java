package com.monopoly;

public class GoSquare extends Square implements EffectSquareAPI {

    GoSquare(String name, int position) {
        super(name, position);
    }

    public void effectTo(Player player, Monopoly monopoly) {
        if (player.getCurrentRound() > 0) {
            player.addBalance(1500);
        }

        // Add current round counter
        player.setCurrentRound(player.getCurrentRound() + 1);
    } // when user pass through the go square

}
