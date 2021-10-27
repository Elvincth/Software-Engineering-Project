public class ChanceSquare extends Square {
    public String name = ""; // if random = 0 = dedect, if random = 1 = add

    ChanceSquare(String name, int position) {
        super(name, position);
        this.name = name;
    }

    public void effectTo(Player player) {
        int addOrDeduct = (int) (Math.random() * 2); // generate a random number between 0 to 1
        int randomMoney = 0;

        if (addOrDeduct == 0) {
            randomMoney = 10 * (int) (Math.random() * 31);
            player.deductBalance(randomMoney);


        } else {
            randomMoney = 10 * (int) (Math.random() * 21);
            player.addBalance(randomMoney);

        }
    } // determine add amount or lose money
}
