package com.buchereli.deepdiveandroid.util;

import android.content.res.AssetManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by buche on 8/15/2017.
 */

public class Game {

    private LinkedHashMap<String, Player> players;
    private Artifacts artifacts;
    private Deck deck;
    private Table table;
    private Choices choices;
    private int round = 1;

    public Game(AssetManager assets, LinkedHashMap<String, Player> players) {
        this.players = players;

        artifacts = new Artifacts(assets);
        deck = new Deck(assets);
        deck.add(artifacts.draw());
        table = new Table(players);
        choices = new Choices(table);
        update();
    }

    private void update() {
        while (choices.complete()) {
            if (!table.activeRound()) {
                table.reset(deck, players);
                round++;
                deck.add(artifacts.draw());
            }

            Card card = deck.draw();
            table.add(card);

            choices.reset();

//            System.out.println();
//            System.out.println(deck.size());
//            System.out.println("ROUND: " + round);
            System.out.println("DRAW: " + card);
//            System.out.println(table);
//            for (Player player : players)
//                System.out.println(player + "(" + player.gems() + ")");
//            System.out.println();
        }
    }

    public void stay() {
        choices.stay(true);
        update();
    }

    public void leave() {
        choices.stay(false);
        update();
    }

    public ArrayList<Card> table() {
        return table.getCards();
    }

    public String state() {
        return "It is currently " + choices.currentPlayer() + " turn";
    }

    public String turn() {
        return choices.currentPlayer().toString();
    }

    public ArrayList<String> getActivePlayers() {
        return table.getActivePlayers();
    }

    public int getGems(String player) {
        return players.get(player).gems();
    }

    public int getActiveGems(String player) {
        return players.get(player).activeGems();
    }
}
