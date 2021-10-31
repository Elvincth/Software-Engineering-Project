package com.monopoly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SquareTest {
    private Monopoly monopoly = new Monopoly();

    @Test
    void testType() {
        assertTrue(monopoly.getSquares()[0] instanceof GoSquare);
        assertTrue(monopoly.getSquares()[1] instanceof PropertySquare);
        assertTrue(monopoly.getSquares()[2] instanceof PropertySquare);

    }

    // Used to rent
    @Test
    void testRent() {

    //   monopoly.getSquares()[1].getRent();

        // assertTrue(();
    }
}
