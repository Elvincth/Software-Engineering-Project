public class PropertySquare extends Square implements EffectSquareAPI {
    private String name = "";
    private int price = 0;
    private int rent = 0;
    private Player owner = null;
    private EColor color = EColor.BLUE;
    private final String TAG = "[PROPERTY]";

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

    // Display name for our game board
    // * = have owner
    public String getDisplayName() {
        return String.format("%s %s %s", colorToEmoji(color), name, haveOwner() ? "*" : "");
    }

    private String colorToEmoji(EColor color) {
        if (EColor.BLUE == color) {
            return "🟦";
        }

        if (EColor.DARK_BLUE == color) {
            return "🔵";
        }

        if (EColor.RED == color) {
            return "🔴";
        }

        if (EColor.YELLOW == color) {
            return "🟡";
        }

        return "";
    }

    public int getPrice() {
        return price;
    } // return the price of the property

    public int getRent() {
        return rent;
    } // return the rent of the property

    public Player getOwner() {
        return owner;
    }// return the owner of the property

    public EColor getColor() { // return the color of the property
        return color;
    }

    // set the owner of the property
    private void buy(Player player) {
        if (!haveOwner()) {
            owner = player;
            // buy property
            player.deductBalance(price);
            player.addProperty(this);
            System.out.printf("%s You have bought %s\n", TAG, name);
        }
    }

    // check whether the property have owner or not
    public boolean haveOwner() {
        return owner != null;
    }

    public void effectTo(Player player, Monopoly monopoly) {
        // If no owner we ask if the player want to buy
        if (!haveOwner()) {
            YesNo buyProperty = new YesNo(monopoly.scanner,
                    String.format("%s Do you want to buy %s with HKD%d?", TAG, name, price));

            // Ask if the user want to buy if yes buy it
            if (buyProperty.ask()) {
                buy(player);
            }

            monopoly.display();
        }

        // Have owner and player is not owner
        // We will get rent and pass it to the owner
        if (haveOwner() && owner.getToken() != player.getToken()) {
            System.out.printf("%s You have to pay rent (HKX%d) to %s\n", TAG, rent, owner.getName());
            // PAY RENT
            player.deductBalance(rent);
            owner.addBalance(rent);
        }
    }

}
