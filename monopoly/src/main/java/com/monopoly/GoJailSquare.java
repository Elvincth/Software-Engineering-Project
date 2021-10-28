package com.monopoly;

public class GoJailSquare extends Square implements EffectSquareAPI  {

    GoJailSquare(String name, int position) {
        super(name, position);
    }

    public void effectTo(Player player, Monopoly monopoly) {
        player.setPosition(5);
    }
}