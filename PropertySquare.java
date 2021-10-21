public class PropertySquare extends Square {
    public String name = "";
    public int price = 0;
    public int rent = 0;
    public String owner = "";
    public EColor color = EColor.BLUE;

    PropertySquare(String name, int price, int rent, EColor color){
        super(name);
		this.name = name;
        this.price = price;
        this.rent = rent;
        this.color = color;
    }

    public String getPropertyName();

    public int getPrice();

    public int getRent();

    public String getOwner();

    public EColor getColor(){
        return color;
    }

    public void setOwner(Player owner);

    public boolean haveOwner();
}  
