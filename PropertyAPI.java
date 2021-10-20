enum Color {
    DARK_BLUE, BLUE, YELLOW, RED
}

public interface PropertyAPI {

    public String propertyName = "";
    public int price = 0;
    public int rent = 0;
    public String owner = "";
    public Color color = Color.BLUE;

    public String getPropertyName();

    public int getPrice();

    public int setRent();

    public int getRent();

    public String getOwner();

    public boolean haveOwner();

}
