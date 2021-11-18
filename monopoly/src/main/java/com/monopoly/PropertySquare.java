package com.monopoly;

public class PropertySquare extends Square implements EffectSquareAPI {
    private Utils utils = new Utils();
    private String name = "";
    private int price = 0;
    private int rent = 0;
    private Player owner = null;
    private EColor color = EColor.BLUE;
    private final String TAG = utils.ANSI_CYAN + "[PROPERTY]" + utils.ANSI_RESET;
    // private Utils utils = new Utils();

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
    @Generated
    public String getDisplayName() {
        String ownerName = haveOwner() ? "\nOwner: " + owner.getToken() : "";

        return String.format("%s %s\n$%s %s", colorToEmoji(color), name, price, ownerName);
    }

    @Generated
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

    // New - add for restore
    public void setOwner(Player player) {
        owner = player;
    }

    public Player getOwner() {
        return owner;
    }// return the owner of the property

    public EColor getColor() { // return the color of the property
        return color;
    }

    @Generated
    public void removeOwner() {
        owner = null;
    }

    // set the owner of the property
    public void buy(Player player, Monopoly monopoly) {
        if (player.getBalance() >= this.price) {
            // check weather the player have enough balance for buying the property
            owner = player;
            // buy property
            player.deductBalance(price);
            player.addProperty(this);
            monopoly.display();
            System.out.printf("%s You have bought %s%n%n", TAG, name);
        } else {
            monopoly.display();
            System.out.printf("%s You have no enough money to buy %s!%n%n", TAG, name);
        }
    }

    // check whether the property have owner or not
    public boolean haveOwner() {
        return owner != null;
    }

    @Generated
    public void effectTo(Player player, Monopoly monopoly) {
        if (!monopoly.isTest()) {
            // If no owner we ask if the player want to buy
            if (!haveOwner()) {
                YesNo buyProperty = new YesNo(monopoly.scanner,
                        String.format("%s Do you want to buy %s with $%d?", TAG, name, price));

                // Ask if the user want to buy if yes buy it
                if (buyProperty.ask()) {
                    buy(player, monopoly);
                } else {
                    monopoly.display();
                }

            }

            // Have owner and player is not owner
            // We will get rent and pass it to the owner
            if (haveOwner() && owner.getToken() != player.getToken()) {
                System.out.printf("%s You have paid rent $%d to %s%n%n", TAG, rent, owner.getName());
                // PAY RENT
                player.deductBalance(rent);
                owner.addBalance(rent);
            }
        }
    }
}
