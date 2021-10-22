import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private String message = "";
    private String[] choices;
    private String[] choicesDescription;
    private Scanner scanner = null;
    private boolean listLayout = false;

    Menu(Scanner scanner, String message, String[] choices, String[] choicesDescription, Boolean listLayout) {
        this.scanner = scanner;
        this.message = message;
        this.choices = choices;
        this.choicesDescription = choicesDescription;

        if (choices.length != choicesDescription.length) {
            throw new IllegalArgumentException("Each choice must have a choicesDescription");
        }
    }

    Menu(Scanner scanner, String message, String[] choices, String[] choicesDescription) {
        this(scanner, message, choices, choicesDescription, false);
    }

    Menu(Scanner scanner, String message, ArrayList<String> choices, ArrayList<String> choicesDescription,
            Boolean listLayout) {
        this(scanner, message, choices.toArray(String[]::new), choicesDescription.toArray(String[]::new));
    }

    Menu(Scanner scanner, String message, ArrayList<String> choices, ArrayList<String> choicesDescription) {
        this(scanner, message, choices, choicesDescription, false);
    }

    // Check if the input work in choice
    private boolean inChoice(String word) {
        for (int i = 0; i < choices.length; i++) {
            if (word.equals(choices[i])) {
                return true;
            }
        }
        return false;
    }

    private void displayMenu() {
        for (int i = 0; i < choices.length; i++) {
            System.out.printf("[%s] %s \n", choices[i], choicesDescription[i]);
        }
    }

    public String ask() {
        String userInput = "";

        System.out.println(message);

        displayMenu();

        while (scanner.hasNextLine()) {
            userInput = scanner.nextLine();

            if (inChoice(userInput)) {

                break;
            }

            System.out.println("Wrong enter again");

        }

        return userInput;
    }

}
