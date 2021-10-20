import java.util.ArrayList;

public interface PlayerAPI {
    // private int position;

    public String getName();

    public String setName();

    private String generateID() {
        return "";
    } // generate an unique id for player

    public String getID();

    public int getPosition();

    public void moveTo(int pos); // move to a square position

    public int getBalance(); // Reutrn player balance

    public int addBalance(); 

    public int deductBalance();

    public ArrayList<Property> getProperty();

    public void addProperty (Property property);

    public boolean isInJail();

    public int currentRound(); //Return the user current round


}

const squares = []