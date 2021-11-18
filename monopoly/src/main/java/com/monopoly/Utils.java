package com.monopoly;

public class Utils {
    // Colors
    public final String ANSI_RESET = "\u001B[0m";
    public final String ANSI_BLACK = "\u001B[30m";;
    public final String ANSI_YELLOW = "\u001B[33m";
    public final String ANSI_GREEN = "\u001B[32m";
    public final String ANSI_CYAN = "\u001B[36m";
    public final String ANSI_RED = "\u001B[31m";
    public final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Generated
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
