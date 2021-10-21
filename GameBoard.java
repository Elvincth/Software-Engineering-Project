
import java.util.ArrayList;

public class GameBoard {
    private ArrayList<Player> players;
    private Square[] squares = new Square[20];

    GameBoard(ArrayList<Player> players, Square[] squares) {
        this.players = players;
        this.squares = squares;
    }

    public void display() {
    };
}