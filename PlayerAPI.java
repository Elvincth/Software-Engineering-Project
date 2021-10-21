import java.util.ArrayList;

public interface PlayerAPI {
    private int position = 0;
    private int currentRound = 0;
    private int inJailRound = 0; //Will reset after the user out of jail, start counting when the user is in jail

    public String getName();

    public String setName();

    private String generateID() {
        return "";
    } // generate an unique id for player

    public String getID(); // Return player ID

    public int getPosition(); // Return player position  

    public void moveTo(int pos); // move to a square position

    public int getBalance(); // Return player balance

    public int addBalance(); // Use to add player balance

    public int deductBalance(); // Will deduct the balance of the player

    public ArrayList<Property> getProperty(); //

    public void addProperty (Property property);

    public boolean isInJail(); //Check is user in jail or not

    public int getCurrentRound(); //Return the user current round



}