package com.monopoly;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws IOException, ParseException {
        Monopoly game = new Monopoly();
        game.start();
    }
}
