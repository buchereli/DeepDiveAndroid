package com.buchereli.deepdiveandroid.util;

import android.content.res.AssetManager;

import java.util.ArrayList;

/**
 * Created by buche on 8/15/2017.
 */

// This class manages the game
public class Game {

    private ArrayList<Player> players;
    private Artifacts artifacts;
    private Deck deck;
    private Table table;
    private Choices choices;
    private int round = 1;
    private String state;

    public Game(AssetManager assets, ArrayList<Player> players) {
        // Save all players in this game
        this.players = players;

        // Create the deck and artifacts from CSV files in assets
        this.artifacts = new Artifacts(assets);
        this.deck = new Deck(assets);

        // Start by adding 1 artifact to the deck
        this.deck.add(artifacts.draw());

        // Initialize the table
        this.table = new Table(players);

        // Track the choices as players make them
        this.choices = new Choices(table);

        // Track the state the game is in
        this.state = "NEW ROUND";
    }

    // If every player has made a choice draw a new card
    private void update() {
        // If every player has made a their choice then draw a new card
        while (choices.complete() && table.activeRound()) {
            // Draw card
            Card card = deck.draw();
            table.add(card);

            // If the round is over then update state
            if (!table.activeRound())
                state = "ROUND OVER";

            // Reset choices
            choices.reset();
        }
    }

    // Start a new round
    public void newRound() {
        // Reset the table and deck
        table.reset(deck, players);

        // Update round
        round++;

        // Add a new artifact to the deck
        deck.add(artifacts.draw());

        // Draw card
        Card card = deck.draw();
        table.add(card);

        // Update state
        state = "CHOICES";

        // Reset choices
        choices.reset();
    }

    public void stay() {
        choices.stay(true);
        update();
    }

    public void leave() {
        choices.stay(false);
        update();
    }

    public GameState getGameState() {
        return new GameState(state, table.getCards(), choices.currentPlayer(), players);
    }

    public void nextRound() {
        state = "NEW ROUND";
    }
}
