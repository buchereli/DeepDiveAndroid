package com.buchereli.deepdiveandroid.util;

import android.content.res.AssetManager;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by buche on 8/15/2017.
 */

class Deck {

    private ArrayList<Card> deck;

    Deck(AssetManager assets) {
        try {
            deck = new ArrayList<Card>();
            // TYPE, ID, COUNT
            CSVReader reader = new CSVReader(new InputStreamReader(assets.open("hazards.csv")));
            for (String[] line : reader.readAll()) {
                for (int i = 0; i < Integer.parseInt(line[2]); i++) {
                    deck.add(new Card(line[0], line[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int size() {
        return deck.size();
    }

    void add(Card card) {
        deck.add(card);
    }

    void add(ArrayList<Card> cards) {
        deck.addAll(cards);
    }

    Card draw() {
        int index = (int) (Math.random() * deck.size());
        Card card = deck.get(index);
        deck.remove(index);
        return card;
    }

}
