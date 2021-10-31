package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SquareTest extends TestUtils{
    private Monopoly monopoly = new Monopoly();
    private Square[] squares = monopoly.getSquares();

    // the position of square will start at 0 since we are using array for storing each square
    // therefore the position of square will between 0 to 19 (20 square)

    // for checking square type
    @Test
    void testType() {
        assertTrue(monopoly.getSquares()[0] instanceof Square);
        assertTrue(monopoly.getSquares()[1] instanceof PropertySquare);
        assertTrue(monopoly.getSquares()[2] instanceof PropertySquare);
        assertTrue(monopoly.getSquares()[3] instanceof TaxSquare);
        assertTrue(monopoly.getSquares()[4] instanceof PropertySquare);
        assertTrue(monopoly.getSquares()[5] instanceof JailSquare);
        assertTrue(monopoly.getSquares()[6] instanceof PropertySquare);
        assertTrue(monopoly.getSquares()[7] instanceof PropertySquare);
        assertTrue(monopoly.getSquares()[8] instanceof ChanceSquare);
        assertTrue(monopoly.getSquares()[9] instanceof PropertySquare);
        assertTrue(monopoly.getSquares()[10] instanceof Square);
        assertTrue(monopoly.getSquares()[11] instanceof PropertySquare);
        assertTrue(monopoly.getSquares()[12] instanceof ChanceSquare);
        assertTrue(monopoly.getSquares()[13] instanceof PropertySquare);
        assertTrue(monopoly.getSquares()[14] instanceof PropertySquare);
        assertTrue(monopoly.getSquares()[15] instanceof GoJailSquare);
        assertTrue(monopoly.getSquares()[16] instanceof PropertySquare);
        assertTrue(monopoly.getSquares()[17] instanceof PropertySquare);
        assertTrue(monopoly.getSquares()[18] instanceof ChanceSquare);
        assertTrue(monopoly.getSquares()[19] instanceof PropertySquare);

        passed("Rent of Square");

    }

    // for checking price of each property square
    @Test
    void testPrice() {
        assertEquals(((PropertySquare) squares[1]).getPrice(), 800);
        assertEquals(((PropertySquare) squares[2]).getPrice(), 700);
        assertEquals(((PropertySquare) squares[4]).getPrice(), 600);
        assertEquals(((PropertySquare) squares[6]).getPrice(), 400);
        assertEquals(((PropertySquare) squares[7]).getPrice(), 500);
        assertEquals(((PropertySquare) squares[9]).getPrice(), 400);
        assertEquals(((PropertySquare) squares[11]).getPrice(), 700);
        assertEquals(((PropertySquare) squares[13]).getPrice(), 400);
        assertEquals(((PropertySquare) squares[14]).getPrice(), 500);
        assertEquals(((PropertySquare) squares[16]).getPrice(), 400);
        assertEquals(((PropertySquare) squares[17]).getPrice(), 400);
        assertEquals(((PropertySquare) squares[19]).getPrice(), 600);

        passed("Price of Square");
    }

    // for checking rent of each property square
    @Test
    void testRent() {
        assertEquals(((PropertySquare) squares[1]).getRent(), 90);
        assertEquals(((PropertySquare) squares[2]).getRent(), 65);
        assertEquals(((PropertySquare) squares[4]).getRent(), 60);
        assertEquals(((PropertySquare) squares[6]).getRent(), 10);
        assertEquals(((PropertySquare) squares[7]).getRent(), 40);
        assertEquals(((PropertySquare) squares[9]).getRent(), 15);
        assertEquals(((PropertySquare) squares[11]).getRent(), 75);
        assertEquals(((PropertySquare) squares[13]).getRent(), 20);
        assertEquals(((PropertySquare) squares[14]).getRent(), 25);
        assertEquals(((PropertySquare) squares[16]).getRent(), 10);
        assertEquals(((PropertySquare) squares[17]).getRent(), 25);
        assertEquals(((PropertySquare) squares[19]).getRent(), 25);

        passed("Price of Square");
    }

    // for checking position of each property square
    @Test
    void testPostion() {
        assertEquals(monopoly.getSquares()[0].getPosition(), 0);
        assertEquals(monopoly.getSquares()[1].getPosition(), 1);
        assertEquals(monopoly.getSquares()[2].getPosition(), 2);
        assertEquals(monopoly.getSquares()[3].getPosition(), 3);
        assertEquals(monopoly.getSquares()[4].getPosition(), 4);
        assertEquals(monopoly.getSquares()[5].getPosition(), 5);
        assertEquals(monopoly.getSquares()[6].getPosition(), 6);
        assertEquals(monopoly.getSquares()[7].getPosition(), 7);
        assertEquals(monopoly.getSquares()[8].getPosition(), 8);
        assertEquals(monopoly.getSquares()[9].getPosition(), 9);
        assertEquals(monopoly.getSquares()[10].getPosition(), 10);
        assertEquals(monopoly.getSquares()[11].getPosition(), 11);
        assertEquals(monopoly.getSquares()[12].getPosition(), 12);
        assertEquals(monopoly.getSquares()[13].getPosition(), 13);
        assertEquals(monopoly.getSquares()[14].getPosition(), 14);
        assertEquals(monopoly.getSquares()[15].getPosition(), 15);
        assertEquals(monopoly.getSquares()[16].getPosition(), 16);
        assertEquals(monopoly.getSquares()[17].getPosition(), 17);
        assertEquals(monopoly.getSquares()[18].getPosition(), 18);
        assertEquals(monopoly.getSquares()[19].getPosition(), 19);

        passed("Position of Square");
    }

    @Test
    void testColor(){
        assertEquals(((PropertySquare) squares[1]).getColor(), EColor.BLUE);
        assertEquals(((PropertySquare) squares[2]).getColor(), EColor.BLUE);
        assertEquals(((PropertySquare) squares[4]).getColor(), EColor.BLUE);
        assertEquals(((PropertySquare) squares[6]).getColor(), EColor.RED);
        assertEquals(((PropertySquare) squares[7]).getColor(), EColor.RED);
        assertEquals(((PropertySquare) squares[9]).getColor(), EColor.RED);
        assertEquals(((PropertySquare) squares[11]).getColor(), EColor.DARK_BLUE);
        assertEquals(((PropertySquare) squares[13]).getColor(), EColor.DARK_BLUE);
        assertEquals(((PropertySquare) squares[14]).getColor(), EColor.DARK_BLUE);
        assertEquals(((PropertySquare) squares[16]).getColor(), EColor.YELLOW);
        assertEquals(((PropertySquare) squares[17]).getColor(), EColor.YELLOW);
        assertEquals(((PropertySquare) squares[19]).getColor(), EColor.YELLOW);

        passed("Color of Square");
    }
}
