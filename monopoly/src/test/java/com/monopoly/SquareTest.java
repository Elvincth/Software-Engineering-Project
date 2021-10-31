package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SquareTest {
    private Monopoly monopoly = new Monopoly();
    private Square[] squares = monopoly.getSquares();

    // for checking square type
    @Test
    void testType() {
        assertTrue(monopoly.getSquares()[0] instanceof GoSquare);
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

        System.out.println("All square type correct.");

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

        System.out.println("All square price correct.");
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

        System.out.println("All square rent correct.");
    }
}
