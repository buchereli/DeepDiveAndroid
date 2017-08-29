package com.buchereli.deepdiveandroid.util;

/**
 * Created by buche on 8/15/2017.
 */

public class Card {

    private CardType type;
    private String id;
    private boolean x;

    Card(String type, String id) {
        switch (type) {
            case "gem":
                this.type = CardType.GEM;
                break;
            case "hazard":
                this.type = CardType.HAZARD;
                break;
            case "artifact":
                this.type = CardType.ARTIFACT;
                break;
            default:
                System.err.println("INVALID CARD TYPE: " + type);
                break;
        }

        this.id = id;
        this.x = false;
    }

    public CardType type() {
        return type;
    }

    public String id() {
        return id;
    }

    @Override
    public String toString() {
        return type + " - " + id;
    }

    public enum CardType {GEM, HAZARD, ARTIFACT}

    public boolean x() {
        return x;
    }

    public Card setX(boolean x) {
        this.x = x;
        return this;
    }

}
