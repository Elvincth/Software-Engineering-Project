package com.monopoly;

import java.util.Scanner;

public class YesNo extends Utils {
    private String message = "";
    private Scanner scanner = null;

    @Generated
    YesNo(Scanner scanner, String message) {
        this.scanner = scanner;
        this.message = message;
    }

    @Generated
    public boolean ask(boolean clearScreen) {

        String userInput = "";

        System.out.printf("%s" + ANSI_YELLOW + " (Y/N)" + ANSI_RESET + ": ", message);

        while (scanner.hasNextLine()) {
            userInput = scanner.nextLine();

            if (userInput.equals("Y") || userInput.equals("y") || userInput.equals("N") || userInput.equals("n")) {
                break;
            }

            System.out.println("Please enter (y/n) or (Y/N) only.");

        }

        if (clearScreen) {
            clearScreen();
        }

        if (userInput.equals("Y") || userInput.equals("y")) {
            return true;
        }

        return false;
    }

    // Ask with clear screen as default
    @Generated
    public boolean ask() {
        return ask(true);
    }
}
