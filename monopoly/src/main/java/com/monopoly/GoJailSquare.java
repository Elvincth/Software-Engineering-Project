package com.monopoly;

public class GoJailSquare extends Square implements EffectSquareAPI {
    private Utils utils = new Utils();
    private final String TAG = utils.ANSI_CYAN + "[JAIL]" + utils.ANSI_RESET;

    GoJailSquare(String name, int position) {
        super(name, position);
    }

    public void effectTo(Player player, Monopoly monopoly) {

        player.goToJail();

        monopoly.display();

        System.out.printf("%s Oh no! You have been send to jail!%n%n", TAG);
    }

}
