
import java.util.ArrayList;

public interface PlayerAPI {

  public void setName(String name);

  public String getName();

  public void setToken(String token);

  public String getToken();

  // private String generateID();// generate an unique id for player

  public String getID(); // Return player ID

  public int getPosition(); // Return player position

  public void setPosition(int pos); // move to a square position

  public int getBalance(); // Return player balance

  public int addBalance(int num); // Use to add player balance **

  public int deductBalance(int num); // Will deduct the balance of the player **

  public ArrayList<PropertySquare> getProperty(); // get property belong to the user

  public void addProperty(PropertySquare property); // add the property to user after they bought

  public boolean isInJail(); // Check is user in jail or not

  public int getJailRound(); // Get current user in jail round (IF any)

}