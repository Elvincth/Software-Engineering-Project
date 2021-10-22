import java.util.Scanner;

public class Menu {
    String message = "";
    String[] choices;
    String[] choicesDescription;

    Menu(String message, String[] choices, String[] choicesDescription) {
        this.message = message;
        this.choices = choices;
        this.choicesDescription = choicesDescription;

        if (choices.length != choicesDescription.length) {
            throw new IllegalArgumentException("Each choice must have a choicesDescription");
        }
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
        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        String userInput = "";

        System.out.println(message);

        displayMenu();

        while (scanner.hasNextLine()) {
            userInput = scanner.nextLine();

            if (inChoice(userInput)) {
                scanner.close();
                break;
            }

            System.out.println("Wrong enter again");

        }

        return userInput;
    }

}
