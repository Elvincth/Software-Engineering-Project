public class PropertySquare extends Square implements EffectSquareAPI {
    private String name = "";
    private int price = 0;
    private int rent = 0;
    private Player owner = null; // ** */
    private EColor color = EColor.BLUE;

    PropertySquare(String name, int position, int price, int rent, EColor color) {
        super(name, position);
        this.name = name;
        this.price = price;
        this.rent = rent;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return 0;
    } // return the price of the property

    public int getRent() {
        return 0;
    } // return the rent of the property

    public Player getOwner() {
        return owner; /** */
    }// return the owner of the property

    public EColor getColor() { // return the color of the property
        return color;
    }

    private void setOwner(Player owner) {

    } // set the owner of the property

    private boolean haveOwner() {
        return owner != null;
    }// check whether the property has owner or not

    public void effectTo(Player player) {
    }
}
