package com.buchereli.deepdiveandroid.util;

import java.util.ArrayList;

/**
 * Created by buche on 8/15/2017.
 */
class Choices {

    private int currentPlayer;
    private ArrayList<Player> players;
    private ArrayList<String> leave;
    private Table table;

    Choices(Table table) {
        this.table = table;
        this.players = new ArrayList<>();
        this.leave = new ArrayList<>();
        this.currentPlayer = 0;
    }

    void reset() {
        this.players = table.getPlayers();
        this.leave = new ArrayList<>();
        this.currentPlayer = 0;
    }

    Player currentPlayer() {
        return players.get(currentPlayer);
    }

    void stay(Boolean choice) {
        if (!choice)
            leave.add(players.get(currentPlayer).toString());
        currentPlayer++;
    }

    boolean complete() {
        if (currentPlayer < players.size())
            return false;

        table.update(leave);
        return true;
    }

}
