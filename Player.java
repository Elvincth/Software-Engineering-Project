
import java.util.ArrayList;

public class Player implements PlayerAPI {
    private int balance = 0; // User current bank balance
    private int position = 0; // Current position of the user
    private int inJailRound = 0; // Will reset after the user out of jail, start counting when the user is in
    private int currentUserRound = 0;
    private boolean inJail = false;
    private String name = "";
    private String token = "";
    private ArrayList<PropertySquare> ownedProperty = new ArrayList<PropertySquare>();

    Player(String name, String token) {
        this.name = name;
        this.token = token;
    }

    // Player(String name, String token, int position) {
    // this.name = name;
    // this.token = token;
    // this.position = position;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int pos) {
        position = pos;
    }

    public int getBalance() {
        return balance;
    }

    public int addBalance(int num) {
        balance = balance + num;
        System.out.println("[BANK] You have gained HKD" + num);
        return balance;
    }

    public int deductBalance(int num) {
        balance = balance - num;
        System.out.println("[BANK] You have been deducted HKD" + num);
        return balance;
    }

    public ArrayList<PropertySquare> getProperty() {
        return ownedProperty;
    }

    public void addProperty(PropertySquare property) {
        ownedProperty.add(property);
    }

    public void setIsInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public boolean isInJail() {
        return inJail;
    }

    public int getJailRound() {
        return inJailRound;
    }

    public int getCurrentRound() {
        return currentUserRound;
    }

    public void addUserCurrentRound(int round) {
        currentUserRound += 1;
    }
}
