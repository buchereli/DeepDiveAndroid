package com.buchereli.deepdiveandroid.util;

/**
 * Created by buche on 8/15/2017.
 */

public class Player {

    private String id;
    private int gems, activeGems;

    public Player(String id) {
        this.id = id;
        this.gems = 0;
        this.activeGems = 0;
    }

    void removeActiveGems() {
        activeGems = 0;
    }

    void addActiveGems(int count) {
        activeGems += count;
    }

    void addGems() {
        gems += activeGems;
    }

    int gems() {
        return gems;
    }

    int activeGems() {
        return activeGems;
    }

    @Override
    public String toString() {
        return id;
    }
}
