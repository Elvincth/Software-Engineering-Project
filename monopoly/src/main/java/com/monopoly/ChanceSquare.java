package com.monopoly;

public class ChanceSquare extends Square implements EffectSquareAPI {
    public String name = ""; // if random = 0 = deduct, if random = 1 = add
    private final String TAG = "[CHANCE]";
    private int randomMoney = 0;

    ChanceSquare(String name, int position) {
        super(name, position);
        this.name = name;
    }

    public void effectTo(Player player, Monopoly monopoly) {
        int addOrDeduct = (int) (Math.random() * 2); // generate a random number between 0 to 1
        // int randomMoney = 0;

        if (addOrDeduct == 0) {
            randomMoney = 10 * (int) (1 + Math.random() * 30);
            player.deductBalance(randomMoney);
        } else {
            randomMoney = 10 * (int) (1 + Math.random() * 20);
            player.addBalance(randomMoney);
        }

        monopoly.display();

        if (addOrDeduct == 0) {
            System.out.printf("%s Oh no! You have been deducted $%d%n%n", TAG, randomMoney);
        } else {
            System.out.printf("%s Congratulations! You have gained $%d%n%n", TAG, randomMoney);
        }

        // // for testing
        // if(monopoly.isTest()){
        //     if(player.getBalance() >= 4700){
        //         System.out.println("Balance After Deducted: " + "$" + player.getBalance());
        //         System.out.println("Which is larger or equal to $4700");
        //     }else if (player.getBalance() <= 5200){
        //         System.out.println("Balance After Added: " + "$" + player.getBalance());
        //         System.out.println("Which is smaller or equal to $5200");
        //     }

        //     System.out.println(randomMoney);
        // }
    } // determine add amount or lose money

    public int getRandomMoney(){
        return randomMoney;     
    }

}
