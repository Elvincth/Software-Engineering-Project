package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class SquareTest {
    private Monopoly monopoly = new Monopoly();
    private Square[] squares = monopoly.getSquares();

    @Test
    void testType() {
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
