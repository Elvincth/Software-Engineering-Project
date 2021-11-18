package com.monopoly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
//https://github.com/dialex/JColor
//https://github.com/iNamik/java_text_tables
import java.util.Scanner;

import com.inamik.text.tables.SimpleTable;
import com.inamik.text.tables.grid.Border;
import com.inamik.text.tables.grid.Util;

import org.json.simple.parser.ParseException;

import com.inamik.text.tables.Cell.Functions;
import com.inamik.text.tables.GridTable;

public class Monopoly {
    private int gameRound = 0;
    private Square[] squares = new Square[20];
    private ArrayList<Player> players = new ArrayList<Player>();
    private int currentPlayerIndex = 0; // Current player index
    private Player currentPlayer;
    // Utils
    protected Scanner scanner = new Scanner(System.in);
    private Utils utils = new Utils();
    // Token commands
    private ArrayList<String> tokenChoices = new ArrayList<String>();
    private ArrayList<String> tokenChoicesInfo = new ArrayList<String>();
    // Settings
    private final boolean DEBUG = false;
    private boolean TEST = false; // is in testing mode, will skip display and next round
    protected final int SHORT_DELAY_TIME = DEBUG ? 10 : 1000;
    // Current round
    private int roundCounter = 0;
    // Lost player
    private int numLostPlayer = 0; // Number of lost players
    ArrayList<Player> winnerPlayerList = new ArrayList<Player>();// Runtime no need to save

    // Dice
    private Dice dice = new Dice(DEBUG);

    // Game data
    private GameData gameData = new GameData(this);

    Monopoly(boolean TEST) {
        this.TEST = TEST;
        reset(); // Init all
    }

    @Generated
    Monopoly() {
        this(false);
    }

    // Reset game, set all var to init
    public void reset() {
        // Reset all
        gameRound = 0;
        players = new ArrayList<Player>();
        currentPlayerIndex = 0;
        currentPlayer = null;
        tokenChoices = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        tokenChoicesInfo = new ArrayList<String>(Arrays.asList("🐶", "🐱", "🚗", "🎩", "🍉", "🐴"));
        roundCounter = 0;
        numLostPlayer = 0;
        winnerPlayerList = new ArrayList<Player>();
        squares[0] = new Square("GO", 0);
        squares[1] = new PropertySquare("Central", 1, 800, 90, EColor.BLUE);
        squares[2] = new PropertySquare("Wan Chai", 2, 700, 65, EColor.BLUE);
        squares[3] = new TaxSquare("INCOME TAX", 3);
        squares[4] = new PropertySquare("Stanley", 4, 600, 60, EColor.BLUE);
        squares[5] = new JailSquare("JAIL / VISIT", 5); // JAIL/JUST VISITING
        squares[6] = new PropertySquare("Shek O", 6, 400, 10, EColor.RED);
        squares[7] = new PropertySquare("Mong Kok", 7, 500, 40, EColor.RED);
        squares[8] = new ChanceSquare("Chance", 8);
        squares[9] = new PropertySquare("Tsing Yi", 9, 400, 15, EColor.RED);
        squares[10] = new Square("FREE PARKING", 10);
        squares[11] = new PropertySquare("Shatin", 11, 700, 75, EColor.DARK_BLUE);
        squares[12] = new ChanceSquare("Chance", 12);
        squares[13] = new PropertySquare("Tuen Mun", 13, 400, 20, EColor.DARK_BLUE);
        squares[14] = new PropertySquare("Tai Po", 14, 500, 25, EColor.DARK_BLUE);
        squares[15] = new GoJailSquare("GO TO JAIL", 15);
        squares[16] = new PropertySquare("Sai Kung", 16, 400, 10, EColor.YELLOW);
        squares[17] = new PropertySquare("Yuen Long", 17, 400, 25, EColor.YELLOW);
        squares[18] = new ChanceSquare("Chance", 18);
        squares[19] = new PropertySquare("Tai O", 19, 600, 25, EColor.YELLOW);
    }

    // Start the game - Edit for test
    @Generated
    public void start() throws IOException, ParseException {
        reset(); // Reset the game
        utils.clearScreen();
        System.out.println(
                utils.ANSI_YELLOW_BACKGROUND + utils.ANSI_BLACK + "\n Welcome To Monopoly!" + utils.ANSI_RESET);
        System.out.println(utils.ANSI_YELLOW + "  ____");
        System.out.println(" /\\' .\\    _____");
        System.out.println("/: \\___\\  / .  /\\");
        System.out.println("\\' / . / /____/..\\");
        System.out.println(" \\/___/  \\'  '\\  /");
        System.out.println("          \\'__'\\/ \n" + utils.ANSI_RESET);
        String[] commands = { "1", "2", "3" };
        String[] choicesInfo = { "Start Game", "Load game", "Exit" };
        Menu startMenu = new Menu(scanner, "Enter a choice", commands, choicesInfo);

        String userChoice = startMenu.askChoice();

        if (userChoice.equals(choicesInfo[0]) && !TEST) {
            if (DEBUG) {
                players.add(new Player("TEST1", tokenChoicesInfo.get(0)));
                players.add(new Player("TEST2", tokenChoicesInfo.get(1)));
                players.add(new Player("TEST3", tokenChoicesInfo.get(2)));
            } else {
                addPlayers();
            }

            nextTurn(false);
        }

        // Load a game data
        if (userChoice.equals(choicesInfo[1])) {
            gameData.load();
        }

        if (userChoice.equals(choicesInfo[2])) {
            exit();
        }
    }
    // // Add a fake player for test
    // if (TEST) {1
    // players.add(new Player("TEST1", tokenChoicesInfo.get(0)));
    // }

    // Handle what the user will do in the turn
    // IS it loading back the data or not?
    // If yes skip all the dice and things
    // Just display the menu
    @Generated
    public void nextTurn(boolean isLoad) {
        int nextPosition = 0;
        String selectedChoice = "1";
        String[] commands = { "1", "2" };
        String[] choicesInfo = { "End my turn", "Back to menu" };
        Menu turnMenu = new Menu(scanner, "Enter a choice", commands, choicesInfo);
        // For players
        currentPlayer = players.get(currentPlayerIndex);
        Square landedSquare = squares[0];// Store user landed square

        if (!isLoad) {
            if (!currentPlayer.getLost()) {
                System.out.printf("It is your turn %s !\n", currentPlayer.getName());

                // Player not in jail, we let user roll the dice

                utils.delay(SHORT_DELAY_TIME);

                if (!currentPlayer.getInJail()) {
                    dice.roll(); // Roll the dice

                    nextPosition = dice.getTotal() + currentPlayer.getPosition();// Get next position for detecting
                                                                                 // passed
                                                                                 // go
                                                                                 // square
                    currentPlayer.movePosition(dice.getTotal()); // Set the position as the rolled dice number
                }

                landedSquare = getLandedSquare();// Set user landed square

                if (!currentPlayer.getInJail()) {
                    displayLanded();
                }

                display(); // Display the game board

                if (nextPosition > 19 && !currentPlayer.getInJail()) {
                    // Tell the player he got 1500 at GO or passed it
                    System.out.printf(utils.ANSI_GREEN + "[GO]" + utils.ANSI_RESET + " %s Passed GO +$1500! \n\n",
                            currentPlayer.getName());
                }

                // Check is the square is a effect square
                if (landedSquare instanceof EffectSquareAPI) {
                    ((EffectSquareAPI) landedSquare).effectTo(currentPlayer, this); // If yes execute effect to
                }

                if (currentPlayer.getJustOutJail() == true) {

                    utils.delay(SHORT_DELAY_TIME);

                    if (!currentPlayer.getIsJailThreeRoundOut()) {
                        dice.roll(); // Roll the dice
                    }

                    nextPosition = dice.getTotal() + currentPlayer.getPosition();// Get next position for detecting
                                                                                 // passed

                    currentPlayer.setPosition(dice.getTotal()); // Set the position as the rolled dice number

                    displayLanded();

                    landedSquare = getLandedSquare();// Set user landed square

                    display(); // Display the game board

                    if (landedSquare instanceof EffectSquareAPI) {
                        ((EffectSquareAPI) landedSquare).effectTo(currentPlayer, this); // If yes execute effect to
                    }
                }

                // Ask for next turn or save the game
                selectedChoice = turnMenu.ask();

                checkPlayerLost();

                currentPlayer.clearJustOutJail();
            }

            // check game round
            checkGameRound();
        } else {
            // is load game skip to the turn menu part
            display();
            // Ask for next turn or save the game
            selectedChoice = turnMenu.ask();
        }

        // User selected end my turn option
        if (selectedChoice.equals(commands[0])) {
            // Pass to next player
            nextPlayer();
        }

        // User selected Back to menu
        if (selectedChoice.equals(commands[1])) {
            // Ask if the user save the game before exit
            askSave();
        }
    }

    // Used to ask will the user save the game
    @Generated
    private void askSave() {
        YesNo saveQuestion = new YesNo(scanner, "Do you want to save the game, before you back to main menu?");
        boolean saveGame = false;
        gameData.printArt(utils.ANSI_YELLOW);
        System.out.println();
        saveGame = saveQuestion.ask();
        if (saveGame) {
            gameData.save();
            try {
                start(); // Back to main menu
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Generated
    public Square getLandedSquare() {
        return squares[currentPlayer.getPosition()];
    }

    @Generated
    public void displayLanded() {
        if (!currentPlayer.getIsJailThreeRoundOut()) {
            dice.display(); // Tell user what he rolled
        }

        utils.delay(SHORT_DELAY_TIME);

        utils.clearScreen();

        System.out.printf("You landed on %s\n", getLandedSquare().getName()); // Tell where did the user landed

        utils.delay(SHORT_DELAY_TIME);

        utils.clearScreen();
    }

    // Set the player index as next player (Pass turn to next player)
    @Generated
    private void nextPlayer() {
        // Set to the next player
        if (currentPlayerIndex == players.size() - 1) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex += 1;
        }
        if (!endGameCheck(gameRound)) {
            nextTurn(false);
        } else {
            // The game is end
            utils.clearScreen();
            endGame();
        }

    }

    public Dice getDice() {
        return dice;
    }

    @Generated
    private void addPlayers() {
        String name = "";
        String token = "";
        Boolean addOneMore = false;
        Menu tokenMenu = new Menu(scanner, "Choose a token", tokenChoices, tokenChoicesInfo);
        YesNo addPlayerQuestion = new YesNo(scanner, "Add one more player?");

        System.out.printf("Enter player%s name: ", players.size() + 1);

        // Enter until name is not empty
        while (scanner.hasNextLine()) {
            name = scanner.nextLine();

            if (!name.trim().isEmpty()) {
                break;
            }

            System.out.println("Name cannot be empty");
        }

        name = name.trim();

        utils.clearScreen();

        token = tokenMenu.askChoice();

        // add the player to the array list
        players.add(new Player(name, token));

        // Remove the selected token in the list
        removeToken(token);

        // If the current number of player is less then one we MUST add another player
        if (players.size() <= 1) {
            addPlayers();
            return;
        }

        // Ask if the user want to add one more user
        // only ask if player number not larger then 6
        if (players.size() < 6) {
            addOneMore = addPlayerQuestion.ask();
            if (addOneMore) {
                addPlayers();
            }
        }

    }

    // Remove the selected token from commands since it have been picked by other
    // users
    // Edited for test
    public boolean removeToken(String token) {
        for (int i = 0; i < tokenChoices.size(); i++) {
            if (token.equals(tokenChoicesInfo.get(i))) {
                tokenChoices.remove(i);
                tokenChoicesInfo.remove(i);
                return true;
            }
        }
        return false;
    }

    @Generated
    private void checkGameRound() {// count the game round
        if (roundCounter >= players.size() - 1) {
            gameRound++;
            roundCounter = 0;
        } else {
            roundCounter++;
        }
    }

    @Generated
    private void printSettlementTable() {
        System.out.printf("%-11s %-10s %-10s\n", "Player Name", "Balance", "Lose");
        System.out.printf("--------------------------------\n");
        for (int i = 0; i < players.size(); i++) {
            System.out.printf("%-11s %-10s %-10s\n", players.get(i).getName(), "$" + players.get(i).getBalance(),
                    players.get(i).getLost() ? "Yes" : "No");
        }
        System.out.printf("--------------------------------\n");
    }

    // Exit - New
    // Edited for test
    public boolean exit() {
        System.out.println(utils.ANSI_YELLOW + "  ______     ________   ______     ________ ");
        System.out.println(" |  _ \\ \\   / /  ____| |  _ \\ \\   / /  ____|");
        System.out.println(" | |_) \\ \\_/ /| |__    | |_) \\ \\_/ /| |__   ");
        System.out.println(" |  _ < \\   / |  __|   |  _ < \\   / |  __|  ");
        System.out.println(" | |_) | | |  | |____  | |_) | | |  | |____");
        System.out.println(" |____/  |_|  |______| |____/  |_|  |______|\n");
        System.out.println("                  ██████                ");
        System.out.println("                 ████████ █             ");
        System.out.println("             ██████████████             ");
        System.out.println("             █████████████              ");
        System.out.println("              ███████████               ");
        System.out.println("               ██████████               ");
        System.out.println("                ████████                ");
        System.out.println("                ▐██████                 ");
        System.out.println("                ▐██████                 ");
        System.out.println("                 ▌     ▌                ");
        System.out.println("                ███ █████               ");
        System.out.println("            ████████████████            ");
        System.out.println("          ████████████████████          ");
        System.out.println("        ████████████     ███████        ");
        System.out.println("      ███████████         ███████       ");
        System.out.println("     ████████████   ██ ███████████      ");
        System.out.println("    ██████████████          ████████    ");
        System.out.println("   ████████████████     █   █████████   ");
        System.out.println("  █████████████████████ ██   █████████  ");
        System.out.println("  █████████████████████  ██  ██████████ ");
        System.out.println(" ███████████████████████ ██   ██████████");
        System.out.println("████████████████████████      ██████████");
        System.out.println("███████████████████          ███████████");
        System.out.println(" ██████████████████       ██████████████");
        System.out.println(" ███████████████████████  █████████████ ");
        System.out.println("  █████████████████████████████████████ ");
        System.out.println("   ██████████████████████████████████   ");
        System.out.println("       ██████████████████████████████   ");
        System.out.println("       ██████████████████████████       ");
        System.out.println("             ███████████████            \n" + utils.ANSI_RESET);
        return true;
    }

    @Generated
    public void endGame() {
        String[] commands = { "1", "2" };
        String[] choicesInfo = { "Back to main menu", "Exit" };
        Menu endMenu = new Menu(scanner, "Enter a choice", commands, choicesInfo); // End game menu

        System.out.println(utils.ANSI_YELLOW + "   _____          __  __ ______    ______      ________ _____  ");
        System.out.println("  / ____|   /\\   |  \\/  |  ____|  / __ \\ \\    / /  ____|  __ \\ ");
        System.out.println(" | |  __   /  \\  | \\  / | |__    | |  | \\ \\  / /| |__  | |__) |");
        System.out.println(" | | |_ | / /\\ \\ | |\\/| |  __|   | |  | |\\ \\/ / |  __| |  _  / ");
        System.out.println(" | |__| |/ ____ \\| |  | | |____  | |__| | \\  /  | |____| | \\ \\ ");
        System.out.println(
                "  \\_____/_/    \\_\\_|  |_|______|  \\____/   \\/   |______|_|  \\_\\ \n\n" + utils.ANSI_RESET);

        int higherBalance = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getBalance() > higherBalance) {
                higherBalance = players.get(i).getBalance();
            }
        }
        for (int j = 0; j < players.size(); j++) {
            if (players.get(j).getBalance() == higherBalance) {
                winnerPlayerList.add(players.get(j));
            }
        }
        printSettlementTable();
        System.out.println("Game over!");
        System.out.print("The winner is ");
        for (int k = 0; k < winnerPlayerList.size(); k++) {
            if (k == winnerPlayerList.size() - 1) {
                System.out.printf("%S ", winnerPlayerList.get(k).getName());
            } else {
                System.out.printf("%s,", winnerPlayerList.get(k).getName());
            }
        }

        System.out.println("\n");

        String userChoice = endMenu.ask();

        // Back to main menu choice
        if (userChoice.equals(commands[0])) {
            try {
                start(); // Start the game
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Generated
    public boolean endGameCheck(int gameRound) {// check wether the game is end or not
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getLost()) {
                numLostPlayer++;
            }
        }
        if (gameRound >= 100) {
            return true;
        } else if (numLostPlayer == players.size() - 1) {
            return true;
        } else {
            numLostPlayer = 0;
            return false;
        }
    }

    // Used to check if any player have lost the game
    @Generated
    private void checkPlayerLost() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getBalance() < 0) {
                Player numLostPlayer = players.get(i);
                ArrayList<PropertySquare> lostProperty = numLostPlayer.getOwnedProperty();

                System.out.printf("%s is Bankruptcy\n", numLostPlayer.getName());
                utils.delay(SHORT_DELAY_TIME);
                numLostPlayer.setToLost();

                // Remove owner of lost properties
                for (int j = 0; j < lostProperty.size(); j++) {
                    lostProperty.get(j).removeOwner();
                }

            }
        }
    }

    // Get user tokens by position
    @Generated
    private String getTokensByPos(int pos) {
        String display = "";

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            // Print player that not in jail
            if (player.getPosition() == pos && !player.getInJail() && !player.getLost()) {
                display = players.get(i).getToken() + " " + display;
            }
        }

        return display;
    }

    // Get user token that is in jail
    @Generated
    private ArrayList<String> getJailedToken() {
        ArrayList<String> tokens = new ArrayList<String>();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            if (player.getInJail() && !player.getLost()) {
                tokens.add(players.get(i).getToken());
            }
        }

        return tokens;
    }

    // For display the game board
    @Generated
    public void display() {
        if (!TEST) {
            int height = 5;
            int width = 20;

            Square[][] boardSquare = { { squares[10], squares[11], squares[12], squares[13], squares[14], squares[15] },
                    { squares[9], null, null, null, null, squares[16] },
                    { squares[8], null, null, null, null, squares[17] },
                    { squares[7], null, null, null, null, squares[18] },
                    { squares[6], null, null, null, null, squares[19] },
                    { squares[5], squares[4], squares[3], squares[2], squares[1], squares[0] } };

            SimpleTable table = SimpleTable.of();

            table.nextRow();

            for (int i = 0; i < boardSquare.length; ++i) {

                for (int j = 0; j < boardSquare[i].length; ++j) {
                    Square square = boardSquare[i][j]; // Current square
                    boolean isSquare = square != null;

                    table.nextCell();

                    // Split the name into parts by \n
                    // So we can use addLine
                    if (isSquare) {
                        String[] splitString;

                        splitString = square.getDisplayName().split("\n");

                        for (String s : splitString) {
                            table.addLine(s);
                        }
                    }

                    // Add jailed token to jail square if any
                    if (square instanceof JailSquare) {
                        ArrayList<String> jailedTokens = getJailedToken();
                        if (jailedTokens.size() > 0) {
                            table.addLine(String.format("|%s|", String.join(" ", jailedTokens)));
                        }
                    }

                    // Add the player tokens that currently on that square
                    if (isSquare) {
                        // If have user token
                        table.addLine(getTokensByPos(square.getPosition()));
                    }

                    table.applyToCell(Functions.VERTICAL_CENTER.withHeight(height))
                            .applyToCell(Functions.HORIZONTAL_CENTER.withWidth(width));
                }

                if (i != 5) { // Skip the last row, no need to insert
                    table.nextRow();
                }

            }

            GridTable gridTable = table.toGrid();

            gridTable = Border.DOUBLE_LINE.apply(gridTable);

            utils.clearScreen();

            System.out.println(Util.asString(gridTable)); // Print out the table

            System.out.printf(
                    utils.ANSI_GREEN
                            + "Current Player: %s, Token: %s, Balance: $%d, Number of property: %d, Game round: %s \n"
                            + utils.ANSI_RESET,
                    currentPlayer.getName(), currentPlayer.getToken(), currentPlayer.getBalance(),
                    currentPlayer.getOwnedProperty().size(), gameRound);

            if (DEBUG) {
                System.out.printf("[DEBUG] Dice total: %s, Player round: %s, Jail Counter: %d%n%n", dice.getTotal(),
                        currentPlayer.getCurrentRound(), currentPlayer.getInJailRound());
            }
        }
    };

    // Get all squares
    public Square[] getSquares() {
        return squares;
    }

    // Get is testing1
    public boolean isTest() {
        return TEST;
    }

    // New - for save
    public int getGameRound() {
        return gameRound;
    }

    public void setGameRound(int round) {
        gameRound = round;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> arr) {
        players = arr;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int index) {
        currentPlayerIndex = index;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    // Set the current player
    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public void setRoundCounter(int num) {
        roundCounter = num;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void setNumLostPlayer(int num) {
        numLostPlayer = num;
    }

    public int getNumLostPlayer() {
        return numLostPlayer;
    }

}
