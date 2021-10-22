import java.util.Scanner;

public class YesNo {
    private String message = "";
    private Scanner scanner = null;

    YesNo(Scanner scanner, String message) {
        this.scanner = scanner;
        this.message = message;
    }

    public boolean ask() {
        String userInput = "";

        System.out.printf("%s (y/n): ", message);

        while (scanner.hasNextLine()) {
            userInput = scanner.nextLine();

            if (userInput == "Y" | userInput == "y" || userInput == "N" || userInput == "n") {

                break;
            }

            System.out.println("Please enter (y/n) only");

        }

        if (userInput == "Y" || userInput == "y") {
            return true;
        }

        return false;
    }

}
