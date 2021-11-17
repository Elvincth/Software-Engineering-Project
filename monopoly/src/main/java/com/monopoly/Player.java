package com.monopoly;

import java.util.ArrayList;

public class Player implements PlayerAPI {
    private int balance = 500; // User current bank balance
    private int position = 0; // Current position of the user
    private int inJailRound = 0; // Will reset after the user out of jail, start counting when the user is in
    private int currentRound = 0;
    private boolean inJail = false;
    private String name = "";
    private String token = "";
    private boolean lost = false;
    private ArrayList<PropertySquare> ownedProperty = new ArrayList<PropertySquare>();
    // sth like this)
    // private Utils utils = new Utils();

    Player(String name, String token) {
        this.name = name;
        this.token = token;
    }

    // Player(String name, String token, int position) {
    // this.name = name;
    // this.token = token;
    // this.position = position;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public int getPosition() {
        return position;
    }

    // Warning: Used to ADD pos not really set
    public void setPosition(int pos) {
        int nextPosition = position + pos;
        if (nextPosition > 19) {
            position = nextPosition - 20;
            // Passed go or at go, add current round counter
            currentRound = currentRound + 1;
            balance += 1500;
        } else {
            position += pos;
        }
    }

    public void setBalance(int num) {
        balance = num;
    }

    public int getBalance() {
        return balance;
    }

    public int addBalance(int num) {
        balance = balance + num;
        return balance;
    }

    public int deductBalance(int num) {
        balance = balance - num;
        return balance;
    }

    public ArrayList<PropertySquare> getProperty() {
        return ownedProperty;
    }

    public void addProperty(PropertySquare property) {
        ownedProperty.add(property);
    }

    public void goToJail() {
        // Send the player to jail
        position = 5;
        setInJail(true);
        setJailRound(1);
    }

    public void outOfJail() {
        setInJail(false);
        setJailRound(0);
    }

    public void setJailRound(int num) {
        inJailRound = num;
    }

    public void setInJail(boolean myInJail) {
        inJail = myInJail;
    }

    public boolean getInJail() {
        return inJail;
    }

    public int getInJailRound() {
        return inJailRound;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int round) {
        currentRound = round;
    }

    public void setToLost() {
        lost = true;
        balance = 0;
        ownedProperty = new ArrayList<PropertySquare>();
    }

    public boolean getLost() {
        return lost;
    }

    // For game data

}
