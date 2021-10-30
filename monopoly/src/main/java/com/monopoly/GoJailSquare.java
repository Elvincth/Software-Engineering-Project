package com.monopoly;

public class GoJailSquare extends Square implements EffectSquareAPI {
    private final String TAG = "[JAIL]";

    GoJailSquare(String name, int position) {
        super(name, position);
    }

    public void effectTo(Player player, Monopoly monopoly) {
        // Send the player in jail
        player.setPosition(5);

        // player.sendJail();
        // monopoly.display();
        // player.isInJail();
        // player.addJailRound();
    }
}
