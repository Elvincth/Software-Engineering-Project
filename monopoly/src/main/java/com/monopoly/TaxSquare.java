package com.monopoly;

public class TaxSquare extends Square implements EffectSquareAPI {
    private Utils utils = new Utils();
    private final String TAG = utils.ANSI_CYAN + "[TAX]" + utils.ANSI_RESET;

    TaxSquare(String name, int position) {
        super(name, position);
    }

    public void effectTo(Player player, Monopoly monopoly) {
        int tax = utils.roundDown(player.getBalance() * 0.1);
        player.deductBalance(tax);

        monopoly.display();

        System.out.printf("%s You have paid $%d tax!%n%n", TAG, tax);

    } // determine add amount or lose money
}
