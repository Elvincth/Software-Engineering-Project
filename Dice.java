public class Dice implements DiceAPI {
    private int[][] dice = { { 4, 2, 3 }, { 4, 3, 1 }, { 3, 2, 1 }, { 4, 1, 2 } };
    private int[] rolledDice = { 1, 1 };

    private int rollDice() {
        int num1 = 0;
        int num2 = 0;
        num1 = (int) (Math.random() * 4);
        num2 = (int) (Math.random() * 3);
        return dice[num1][num2];
    }

    // Random the dice
    public void roll() {
        rolledDice[0] = rollDice();
        ;
        rolledDice[1] = rollDice();
        System.out.printf("The first dice is %d, the second dice is %d\nThe total number of is %d\n", rolledDice[0],
                rolledDice[1], getTotal());
    }

    // Roll the dice and get the rolled of it
    public int[] getRolled() {
        return rolledDice;
    };

    public int getTotal() {
        return rolledDice[0] + rolledDice[1];
    };
}