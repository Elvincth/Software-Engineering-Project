import java.util.ArrayList;

import javax.management.ValueExp;

import com.diogonunes.jcolor.AnsiFormat;
import com.diogonunes.jcolor.Attribute;
import com.inamik.text.tables.SimpleTable;
import com.inamik.text.tables.grid.Border;
import com.inamik.text.tables.grid.Util;
import com.inamik.text.tables.Cell;
import com.inamik.text.tables.GridTable;

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
        squares[5] = new JailSquare("JAIL/JUST VISITING", 5);
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
    }

    public int getCurrentRound() {
        return currentRound;
    }

    // For display the game board
    public void display() {
        // for (int i = 0; i < squares.length; i++) {
        // int price = -1;
        // String priceDisplay = "";

        // if (squares[i] instanceof PropertySquare) {
        // price = ((PropertySquare) squares[i]).getPrice();
        // priceDisplay = " HKD:" + price;
        // }

        // AnsiFormat fNormal = new AnsiFormat(Attribute.MAGENTA_BACK(),
        // Attribute.YELLOW_TEXT());

        // System.out.println(fNormal.format(squares[i].getName() + priceDisplay));

        // }
        // NOTE: Apply vertical alignment FIRST !
        //

        int height = 10;
        int width = 20;

        SimpleTable s = SimpleTable.of().nextRow().nextCell().addLine("Left").addLine("Top")
                .applyToCell(Cell.Functions.TOP_ALIGN.withHeight(height))
                .applyToCell(Cell.Functions.LEFT_ALIGN.withWidth(width).withChar('^')).nextCell().addLine("Center")
                .addLine("Top").applyToCell(Cell.Functions.TOP_ALIGN.withHeight(height))
                .applyToCell(Cell.Functions.HORIZONTAL_CENTER.withWidth(width)).nextCell().addLine("Right")
                .addLine("Top").applyToCell(Cell.Functions.TOP_ALIGN.withHeight(height))
                .applyToCell(Cell.Functions.RIGHT_ALIGN.withWidth(width)).nextRow().nextCell().addLine("Left")
                .addLine("Center").applyToCell(Cell.Functions.VERTICAL_CENTER.withHeight(height))
                .applyToCell(Cell.Functions.LEFT_ALIGN.withWidth(width)).nextCell().addLine("Center").addLine("Center")
                .applyToCell(Cell.Functions.VERTICAL_CENTER.withHeight(height))
                .applyToCell(Cell.Functions.HORIZONTAL_CENTER.withWidth(width).withChar('.')).nextCell()
                .addLine("Right").addLine("Center").applyToCell(Cell.Functions.VERTICAL_CENTER.withHeight(height))
                .applyToCell(Cell.Functions.RIGHT_ALIGN.withWidth(width)).nextRow().nextCell().addLine("Left")
                .addLine("Bottom").applyToCell(Cell.Functions.BOTTOM_ALIGN.withHeight(height))
                .applyToCell(Cell.Functions.LEFT_ALIGN.withWidth(width)).nextCell().addLine("Center").addLine("Bottom")
                .applyToCell(Cell.Functions.BOTTOM_ALIGN.withHeight(height))
                .applyToCell(Cell.Functions.HORIZONTAL_CENTER.withWidth(width)).nextCell().addLine("Right")
                .addLine("Bottom").applyToCell(Cell.Functions.BOTTOM_ALIGN.withHeight(height))
                .applyToCell(Cell.Functions.RIGHT_ALIGN.withWidth(width).withChar('_'));

        //
        // NOTE: SimpleTable makes creating the table easy, but you will need to convert
        // it
        // into a GridTable in order to perform further operations
        // (like adding a border or printing)
        //

        // Convert to grid
        //
        GridTable g = s.toGrid();

        // Add simple border
        //
        g = Border.of(Border.Chars.of('+', '-', '|')).apply(g);

        // Print the table to System.out
        //

        System.out.println( Util.asString(g));
    };
}