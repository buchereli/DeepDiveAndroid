package com.buchereli.deepdiveandroid.util;

import java.util.ArrayList;

/**
 * Created by buche on 8/15/2017.
 */
class Choices {

    private ArrayList<Player> players;
    private ArrayList<Boolean> stay;
    private int leaveCount;
    private Table table;

    Choices(Table table) {
        this.table = table;
        this.players = table.getPlayers();
        this.stay = new ArrayList<Boolean>();
        this.leaveCount = 0;
    }

    Player currentPlayer() {
        return players.get(stay.size());
    }

    void stay(Boolean choice) {
        stay.add(choice);
        if (!choice)
            leaveCount++;
    }

    boolean complete() {
        if (stay.size() == players.size()) {
            table.update(stay, leaveCount);
            return true;
        }

        return false;
    }

}
