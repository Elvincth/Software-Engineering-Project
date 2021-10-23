public class Dice implements DiceAPI {
    private int[][] dice = { { 4, 2, 3 }, { 4, 3, 1 }, { 3, 2, 1 }, { 4, 1, 2 } };
    private int[] rolledDice = { 1, 1 };

    //Roll the dice and get the rolled of it
    public int[] getRolled() {
        return rolledDice;
    };

    public int getTotal() {
        return rolledDice[0] + rolledDice[1];
    };
}