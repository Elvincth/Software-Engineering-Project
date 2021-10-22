import java.util.ArrayList;

public class Monopoly extends GameData {
    private int currentRound = 0;
    private Square[] squares = new Square[20];
    private ArrayList<Player> players;
    private String playerTokenTurn = ""; // Used to store current turn player's token //**? */

    // Start the game
    private void start() {

    }

    public int getCurrentRound() {
        return currentRound;
    }

    //For display the game board
    public void display() {
    };
}