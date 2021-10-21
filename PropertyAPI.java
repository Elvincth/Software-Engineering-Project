public interface PropertyAPI {
    public String name = "";
    public int price = 0;
    public int rent = 0;
    public String owner = "";
    public Color color = Color.BLUE;

    public String getPropertyName();

    public int getPrice();

    public int getRent();

    public int setRent(int rent); //User need to pay how much rent

    public String getOwner();

    public void setOwner(Player owner);

    public boolean haveOwner();

}
