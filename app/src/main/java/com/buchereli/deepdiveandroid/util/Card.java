package com.buchereli.deepdiveandroid.util;

/**
 * Created by buche on 8/15/2017.
 */

class Card {

    private CardType type;
    private String id;
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
    }

    CardType type() {
        return type;
    }

    public String id() {
        return id;
    }

    @Override
    public String toString() {
        return type + " - " + id;
    }

    enum CardType {GEM, HAZARD, ARTIFACT}

}
