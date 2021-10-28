package com.monopoly;
public class Utils {
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    // rounded down to a multiple of n
    public int roundDown(double value) {
        int rem = (int) value;
        int n = 10;

        return (int) (Math.floor((rem / n) * n));
    }
}
