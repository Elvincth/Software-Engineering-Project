package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class SquareTest {
    private Monopoly monopoly = new Monopoly();
    private Square[] squares = monopoly.getSquares();

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
        assertTrue(squares[0] instanceof GoSquare);
        assertTrue(squares[1] instanceof PropertySquare);
        assertTrue(squares[2] instanceof PropertySquare);
    }

    // Used to rent
    @Test
    void testRent() {

        ((PropertySquare)squares[1]).getRent();

        // assertTrue(();
    }
}
