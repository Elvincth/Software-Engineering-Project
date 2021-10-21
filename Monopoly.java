import java.util.ArrayList;

public class Monopoly extends GameData {
    private int currentRound = 0;
    private Square[] squares = new Square[20];
    private ArrayList<Player> players;
    private String currentPlayerID = ""; // Used to store current turn player's id

    public int getCurrentRound() {
        return currentRound;
    }
}