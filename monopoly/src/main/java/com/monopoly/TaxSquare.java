package com.monopoly;
public class TaxSquare extends Square implements EffectSquareAPI {
    private Utils utils = new Utils();
    private final String TAG = "[TAX]";

    TaxSquare(String name, int position) {
        super(name, position);
    }

    public void effectTo(Player player, Monopoly monopoly) {
        int tax = utils.roundDown(player.getBalance() * 0.1);

        System.out.printf("%s You have to pay HKD %d tax!\n", TAG, tax);
        player.deductBalance(tax);
        // TODO: check if the user still have money
    } // determine add amount or lose money
}
