
import java.util.ArrayList;

public class Player implements PlayerAPI {

    private int balance = 0; // User current bank balance
    private int position = 0; // Current position of the user
    private int inJailRound = 0; // Will reset after the user out of jail, start counting when the user is in
    private String name = "";
    private String token = "";
    private ArrayList<PropertySquare> ownedProperty; /**/ 
    

    Player(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {

        return null;
    }

    public void setName(String name) {
    }

    public void setToken(String token) {

    }

    public String getToken() {

        return null;
    }

    public int getPosition() {

        return 0;
    }

    public void setPosition(int pos) {

    }

    public int getBalance() {

        return 0;
    }

    public int addBalance(int num) {

        return 0;
    }

    public int deductBalance(int num) {

        return 0;
    }

    public ArrayList<PropertySquare> getProperty() {
        return ownedProperty;
    }

    public void addProperty(PropertySquare property) {

    }

    public boolean isInJail() {

        return false;
    }

    public int getJailRound() {

        return 0;
    }

}
