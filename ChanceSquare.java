import java.util.Random;

public class ChanceSquare extends Square{
    public String name = ""; // if random = 0 = dedect, if random = 1 = add
    
    ChanceSquare(String name, int position){
        super(name, position);
        this.name = name;
    }

    public void effectTo(Player player){
        int addOrDeduct = (int)(Math.random() * 2); //generate a random number between 0 to 1
        if (addOrDeduct == 0){
            int randomMoney = 10 * (int)(Math.random() * 31);
            player.deductBalance(randomMoney);
            System.out.println("you have been deducted HKD" + randomMoney);

        }
        else{
            int randomMoney = 10 * (int)(Math.random() * 21);
            player.addBalance(randomMoney);
            System.out.println("you have gained HKD" + randomMoney);
        }
    } //determine add amount or lose money
}
