package com.buchereli.deepdiveandroid.util;

import java.util.ArrayList;

/**
 * Created by buche on 8/31/2017.
 */

public class GameState {

    private String state;
    private ArrayList<Card> table;
    private String turn;
    private ArrayList<Player> players;

    GameState(String state, ArrayList<Card> table, String turn, ArrayList<Player> players) {
        this.state = state;
        this.table = table;
        this.turn = turn;
        this.players = players;
    }

    public ArrayList<Card> getTable() {
        return table;
    }

    public String getTurn() {
        return turn;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<String> getActivePlayers(){
        ArrayList<String> activePlayers = new ArrayList<>();
        for (Player player: players)
            if(player.isActive())
                activePlayers.add(player.toString());

        return activePlayers;
    }

    public String getState() {
        return state;
    }

}
