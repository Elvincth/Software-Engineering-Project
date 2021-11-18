package com.monopoly;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends Utils {
    private String message = "";
    private String[] commands;
    private String[] choices;
    private Scanner scanner = null;
    private boolean listLayout = false;

    @Generated
    Menu(Scanner scanner, String message, String[] commands, String[] choices, Boolean listLayout) {
        this.scanner = scanner;
        this.message = message;
        this.commands = commands;
        this.choices = choices;
        this.listLayout = listLayout;

        if (commands.length != choices.length) {
            throw new IllegalArgumentException("Each command must have a choice");
        }
    }

    @Generated
    Menu(Scanner scanner, String message, String[] commands, String[] choices) {
        this(scanner, message, commands, choices, true);
    }

    @Generated
    Menu(Scanner scanner, String message, ArrayList<String> commands, ArrayList<String> choices, Boolean listLayout) {
        this(scanner, message, commands.toArray(new String[0]), choices.toArray(new String[0]));
    }

    @Generated
    Menu(Scanner scanner, String message, ArrayList<String> commands, ArrayList<String> choices) {
        this(scanner, message, commands, choices, true);
    }

    // Check if the input work in choice
    @Generated
    private boolean inChoice(String word) {
        for (int i = 0; i < commands.length; i++) {
            if (word.equals(commands[i])) {
                return true;
            }
        }
        return false;
    }

    @Generated
    private String commandToChoice(String word) {
        for (int i = 0; i < commands.length; i++) {
            if (word.equals(commands[i])) {
                return choices[i];
            }
        }
        return "";
    }

    @Generated
    private void displayMenu() {
        String separator = listLayout ? "\n" : " ";
        for (int i = 0; i < commands.length; i++) {
            System.out.printf(ANSI_YELLOW + "[%s]" + ANSI_RESET + " %s %s", commands[i], choices[i], separator);
        }
    }

    @Generated
    private String askHandler(Boolean returnCommand) {
        String userInput = "";

        System.out.println(ANSI_YELLOW + message + ANSI_RESET);

        displayMenu();

        while (scanner.hasNextLine()) {
            userInput = scanner.nextLine();

            if (inChoice(userInput)) {

                break;
            }

            System.out.println("Please enter " + String.join(", ", commands) + " only");

        }

        clearScreen();

        return returnCommand ? userInput : commandToChoice(userInput);
    }

    // Will return the user command
    @Generated
    public String ask() {
        return askHandler(true);
    }

    // Will return the user picked choice
    @Generated
    public String askChoice() {
        return askHandler(false);
    }

}
