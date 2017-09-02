package com.buchereli.deepdiveandroid.util;

/**
 * Created by buche on 8/15/2017.
 */

public class Player {

    private String id;
    private int gems, activeGems;
    private boolean active;

    public Player(String id) {
        this.id = id;
        this.gems = 0;
        this.activeGems = 0;
        this.active = true;
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

    public int getGems() {
        return gems;
    }

    public int getActiveGems() {
        return activeGems;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return id;
    }
}
