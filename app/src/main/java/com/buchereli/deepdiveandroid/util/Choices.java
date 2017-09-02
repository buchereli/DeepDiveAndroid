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
        reset();
    }

    void reset() {
        this.players = table.getPlayers();
        for (Player player : this.players)
            player.setActive(true);
        this.leave = new ArrayList<>();
        this.currentPlayer = 0;
    }

    String currentPlayer() {
        return players.isEmpty() ? null : players.get(currentPlayer).toString();
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
