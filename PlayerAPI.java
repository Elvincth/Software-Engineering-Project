import java.util.ArrayList;

public interface PlayerAPI {
   // private int balance = 0; //User current bank balance
   // private int position = 0; //Current position of the user
   // private int inJailRound = 0; //Will reset after the user out of jail, start counting when the user is in jail

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

    public ArrayList<PropertySquare> getProperty(); // get property belong to the user

    public void addProperty (PropertySquare property); // add the property to user after they bought 

    public boolean isInJail(); //Check is user in jail or not

    public int getJailRound(); //Get current user jail round

}