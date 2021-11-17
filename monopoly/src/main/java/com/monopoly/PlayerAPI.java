package com.monopoly;

import java.util.ArrayList;

public interface PlayerAPI {

    public void setName(String name);

    public String getName();

    public void setToken(String token);

    public String getToken();

    public int getPosition(); // Return player position

    public void movePosition(int pos); // move to a square position

    public int getBalance(); // Return player balance

    public int addBalance(int num); // Use to add player balance **

    public int deductBalance(int num); // Will deduct the balance of the player **

    public ArrayList<PropertySquare> getOwnedProperty(); // get property belong to the user

    public void addProperty(PropertySquare property); // add the property to user after they bought

    public boolean getInJail(); // Check is user in jail or not

    public int getInJailRound(); // Get current user in jail round (IF any)

}
