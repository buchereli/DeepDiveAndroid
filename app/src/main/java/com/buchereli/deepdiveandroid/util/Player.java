package com.buchereli.deepdiveandroid.util;

/**
 * Created by buche on 8/15/2017.
 */

public class Player {

    private String id;
    private int gems;

    public Player(String id) {
        this.id = id;
        gems = 0;
    }

    void addGems(int count) {
        gems += count;
    }

    int gems() {
        return gems;
    }

    @Override
    public String toString() {
        return id;
    }

}
