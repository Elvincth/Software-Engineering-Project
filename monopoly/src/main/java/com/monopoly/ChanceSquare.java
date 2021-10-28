package com.monopoly;

public class ChanceSquare extends Square implements EffectSquareAPI {
    public String name = ""; // if random = 0 = dedect, if random = 1 = add

    ChanceSquare(String name, int position) {
        super(name, position);
        this.name = name;
    }

    public void effectTo(Player player, Monopoly monopoly) {
        int addOrDeduct = (int) (Math.random() * 2); // generate a random number between 0 to 1
        int randomMoney = 0;

        if (addOrDeduct == 0) {
            randomMoney = 10 * (int) (1 + Math.random() * 30);
            player.deductBalance(randomMoney);
        } else {
            randomMoney = 10 * (int) (1 + Math.random() * 20);
            player.addBalance(randomMoney);
        }
    } // determine add amount or lose money
}
