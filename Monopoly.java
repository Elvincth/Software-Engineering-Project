import java.util.ArrayList;
//https://github.com/dialex/JColor
//https://github.com/iNamik/java_text_tables
import javax.management.ValueExp;

import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;
import com.inamik.text.tables.SimpleTable;
import com.inamik.text.tables.grid.Border;
import com.inamik.text.tables.grid.Util;
import com.inamik.text.tables.Cell.Functions;
import com.inamik.text.tables.GridTable;
import com.diogonunes.jcolor.Attribute;

public class Monopoly extends GameData {
    private int currentRound = 0;
    private Square[] squares = new Square[20];
    private ArrayList<Player> players;
    private String playerTokenTurn = ""; // Used to store current turn player's token //**? */

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
    }

    // Start the game
    public void start() {
        display();
        players.add(new Player("x", "x"));
    }

    public int getCurrentRound() {
        return currentRound;
    }

    // For display the game board
    public void display() {
        int height = 4;
        int width = 18;

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
                Square square = boardSquare[i][j];
                boolean isSquare = square != null;

                table.nextCell();

                // Split the name into parts by \n
                // So we can use addLine
                if (isSquare) {
                    String[] splitString;

                    if (square instanceof PropertySquare) {
                        splitString = ((PropertySquare) square).getEmojiName().split("\n");
                    } else {
                        splitString = square.getName().split("\n");
                    }

                    for (String s : splitString) {
                        table.addLine(s);
                    }
                }

                // Add price label if it is PropertySquare
                if (isSquare && square instanceof PropertySquare) {
                    table.addLine("HKD " + ((PropertySquare) square).getPrice());
                }

                table.applyToCell(Functions.VERTICAL_CENTER.withHeight(height))
                        .applyToCell(Functions.HORIZONTAL_CENTER.withWidth(width));
            }

            if (i != 5) { // Skip the last row, no need to insert
                table.nextRow();
            }

        }

        GridTable gridTable = table.toGrid();

        gridTable = Border.SINGLE_LINE.apply(gridTable);

        System.out.println(Util.asString(gridTable));
    };
}