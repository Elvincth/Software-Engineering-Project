import java.util.ArrayList;

public class Monopoly extends GameData {
    private int currentRound = 0;
    private Square[] squares = new Square[20];
    private ArrayList<Player> players;
    private String playerTokenTurn = ""; // Used to store current turn player's token //**? */

    Monopoly() {
        squares[0] = new GoSquare("GO", 0);
        squares[1] = new PropertySquare("Central", 1, 800, 100, EColor.BLUE);
        squares[2] = new PropertySquare("Wan Chai", 2, 700, 65, EColor.BLUE);
        squares[3] = new TaxSquare("INCOME TAX", 3);
    }

    // Start the game
    public void start() {
        display();
    }

    public int getCurrentRound() {
        return currentRound;
    }

    // For display the game board
    public void display() {
        System.out.print(String.format("%-5s%-5d|", "|", 7));
        System.out.print(String.format("%-5s%-5d|", "|", 7));
        System.out.print(String.format("%-5s%-5d|", "|", 7));
    };
}