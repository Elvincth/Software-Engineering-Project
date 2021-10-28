package com.monopoly;

public interface DiceAPI {

    public void roll();

    public int[] getRolled(); // Get the rolled number from the two dice

    public int getTotal(); // plus two rolled number

}
