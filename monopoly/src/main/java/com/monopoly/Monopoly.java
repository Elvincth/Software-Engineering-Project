package com.monopoly;

import java.util.ArrayList;
import java.util.Arrays;
//https://github.com/dialex/JColor
//https://github.com/iNamik/java_text_tables
import java.util.Scanner;

import com.inamik.text.tables.SimpleTable;
import com.inamik.text.tables.grid.Border;
import com.inamik.text.tables.grid.Util;
import com.inamik.text.tables.Cell.Functions;
import com.inamik.text.tables.GridTable;

public class Monopoly {
    private int gameRound = 0;// TODO: save
    private Square[] squares = new Square[20];
    private ArrayList<Player> players = new ArrayList<Player>();
    private int currentPlayerIndex = 0; // Current player index TODO: save
    private Player currentPlayer;
    // Utils
    protected Scanner scanner = new Scanner(System.in);
    private Utils utils = new Utils();
    // Token commands
    private ArrayList<String> tokenChoices = new ArrayList<String>();
    private ArrayList<String> tokenChoicesInfo = new ArrayList<String>();
    // Settings
    final boolean DEBUG = false;
    final int SHORT_DELAY_TIME = DEBUG ? 10 : 900;
    // Dice
    private Dice dice = new Dice(DEBUG);
    private int roundCounter = 0;
    private int lostPlayer = 0;

    // Game data
    // private GameData gameData = new GameData(this);
    Monopoly() {
        squares[0] = new GoSquare("GO", 0);
        squares[1] = new PropertySquare("Central", 1, 800, 90, EColor.BLUE);
        squares[2] = new PropertySquare("Wan Chai", 2, 700, 65, EColor.BLUE);
        squares[3] = new TaxSquare("INCOME TAX", 3);
        squares[4] = new PropertySquare("Stanley", 4, 600, 60, EColor.BLUE);
        squares[5] = new JailSquare("JAIL", 5); // JAIL/JUST VISITING
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
        tokenChoices = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        tokenChoicesInfo = new ArrayList<String>(Arrays.asList("🐶", "🐱", "🚗", "🎩", "🍉", "🐴"));
    }

    // Start the game
    public void start() {
        utils.clearScreen();
        System.out.println("Welcome To Monopoly!");
        String[] commands = { "1", "2" };
        String[] choicesInfo = { "Start Game", "Load a game" };
        Menu startMenu = new Menu(scanner, "Enter a choice", commands, choicesInfo);
        String userChoice = startMenu.askChoice();

        if (userChoice.equals(choicesInfo[0])) {
            if (DEBUG) {
                players.add(new Player("TEST1", tokenChoicesInfo.get(0)));
                players.add(new Player("TEST2", tokenChoicesInfo.get(1)));
                // players.add(new Player("TEST3", tokenChoicesInfo.get(2)));
                // players.add(new Player("TEST4", tokenChoicesInfo.get(3)));
                // players.add(new Player("TEST5", tokenChoicesInfo.get(4)));
                // players.add(new Player("TEST6", tokenChoicesInfo.get(5)));
            } else {
                addPlayers();
            }

            nextTurn();
        }
    }

    // Handle what the user will do in the turn
    private void nextTurn() {
        int nextPosition = 0;
        String[] commands = { "1" };
        String[] choicesInfo = { "End my turn" };
        Menu turnMenu = new Menu(scanner, "Enter a choice", commands, choicesInfo);
        // For players
        currentPlayer = players.get(currentPlayerIndex);
        Square landedSquare = squares[0];// Store user landed square

        if (!currentPlayer.getLost()) {
            System.out.printf("It is your turn %s !\n", currentPlayer.getName());

            // Player not in jail, we let user roll the dice

            utils.delay(SHORT_DELAY_TIME);

            dice.roll(); // Roll the dice

            if (!currentPlayer.isInJail()) {
                nextPosition = dice.getTotal() + currentPlayer.getPosition();// Get next position for detecting passed
                                                                             // go
                                                                             // square
                currentPlayer.setPosition(dice.getTotal()); // Set the position as the rolled dice number
            }

            landedSquare = squares[currentPlayer.getPosition()];// Set user landed square

            // display(); // Display the game board

            dice.display(); // Tell user what he rolled

            utils.delay(SHORT_DELAY_TIME);

            if (!currentPlayer.isInJail()) {
                System.out.printf("You landed on %s\n", landedSquare.getName()); // Tell where did the user landed

                utils.delay(SHORT_DELAY_TIME);
            }

            utils.delay(SHORT_DELAY_TIME);

            if (nextPosition > 19 && !currentPlayer.isInJail()) {
                // Tell the player he got 1500 at GO or passed it
                System.out.printf("[GO] %s Passed GO +1500! \n", currentPlayer.getName());
            }

            // TODO: handle passed add money
            if (nextPosition > 19) {
                // Tell the player he got 1500 at GO or passed it
                System.out.printf("[GO] %s Passed GO +1500! \n", currentPlayer.getName());
            }

            // Check is the square is a effect square
            if (landedSquare instanceof EffectSquareAPI) {
                ((EffectSquareAPI) landedSquare).effectTo(currentPlayer, this); // If yes execute effect to
            }

            // Ask for next turn
            turnMenu.ask();
        }
        //check any player was Bankrupt
        checkPlayerLose();

        // check game round
        checkGameRound();

        // Pass to next player
        nextPlayer();
    }

    // Set the player index as next player (Pass turn to next player)
    private void nextPlayer() {
        // Set to the next player
        if (currentPlayerIndex == players.size() - 1) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex += 1;
        }
        if (!endGameCheck()) {
            nextTurn();
        } else {
            utils.clearScreen();
            System.out.println("The game is End \n");
            System.out.printf("The winner is %s \n", checkGameWinner());
            // TODO add menu
        }

    }

    public Dice getDice() {
        return dice;
    }

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
    private void removeToken(String token) {
        for (int i = 0; i < tokenChoices.size(); i++) {
            if (token.equals(tokenChoicesInfo.get(i))) {
                tokenChoices.remove(i);
                tokenChoicesInfo.remove(i);
            }
        }
    }

    private int checkGameRound() {// count the game round
        if (roundCounter >= players.size() + 2) {
            gameRound++;
            roundCounter = 0;
        } else {
            roundCounter++;
        }
        ;
        return gameRound;
    }

    private String checkGameWinner() {
        String playerName = "";
        int higherBalance = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getBalance() > higherBalance) {
                higherBalance = players.get(i).getBalance();
                playerName = players.get(i).getName();
            }
        }
        return playerName;
    }

    private boolean endGameCheck() {// check wether the game is end or not
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getLost()) {
                lostPlayer++;
            }
        }
        if (gameRound == 100) {
            return true;
        } else if (lostPlayer == players.size() - 1) {
            return true;
        } else {
            lostPlayer = 0;
            return false;
        }
    }

    private void checkPlayerLose() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getBalance() < 0) {
                Player lostPlayer = players.get(i);
                ArrayList<PropertySquare> lostProperty = lostPlayer.getProperty();

                System.out.printf("%s is Bankruptcy\n", lostPlayer.getName());
                utils.delay(2000);
                lostPlayer.setToLost();

                // Remove owner of lost properties
                for (int j = 0; j < lostProperty.size(); j++) {
                    lostProperty.get(i).removeOwner();
                }

            }
        }
    }

    // Get user tokens by position
    private String getTokensByPos(int pos) {
        String display = "";

        for (int i = 0; i < players.size(); i++) {
            // Print player that not in jail
            if (players.get(i).getPosition() == pos && !players.get(i).isInJail()) {
                display = players.get(i).getToken() + " " + display;
            }
        }

        return display;
    }

    // Get user token that is in jail
    private ArrayList<String> getJailedToken() {
        ArrayList<String> tokens = new ArrayList<String>();

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isInJail()) {
                tokens.add(players.get(i).getToken());
            }
        }

        return tokens;
    }

    // For display the game board
    public void display() {
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

        System.out.printf("Current Player: %s, Token: %s, Balance: $%d, Number of property: %d\n",
                currentPlayer.getName(), currentPlayer.getToken(), currentPlayer.getBalance(),
                currentPlayer.getProperty().size());

        if (DEBUG) {
            System.out.printf("[DEBUG] Dice total: %s, Player round: %s, Game round: %s\n\n", dice.getTotal(),
                    currentPlayer.getCurrentRound(), checkGameRound());
        }
    };
}
