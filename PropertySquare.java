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

    public int getPrice(); //return the price of the property

    public int getRent(); //return the rent of the property

    public String getOwner();  // return the owner of the property

    public EColor getColor(){ // return the color of the property
        return color;
    }

    public void setOwner(Player owner); // set the owner of the property

    public boolean haveOwner(); //check whether the property has owner or not
}  
