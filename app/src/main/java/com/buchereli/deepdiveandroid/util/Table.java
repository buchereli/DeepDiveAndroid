package com.buchereli.deepdiveandroid.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 * Created by buche on 8/15/2017.
 */
class Table {

    private ArrayList<Card> removedCards;
    private ArrayList<Card> cards;
    private LinkedHashMap<String, Player> players;
    private int tableGems;
    private boolean activeRound;

    Table(ArrayList<Player> players) {
        removedCards = new ArrayList<>();
        init(players);
    }

    void reset(Deck deck, ArrayList<Player> players) {
        if (!cards.isEmpty()) {
            Card card = cards.remove(cards.size() - 1);
            removedCards.add(card.setX(true));
            deck.add(cards);
        }
        init(players);
    }

    private void init(ArrayList<Player> players) {
        this.players = new LinkedHashMap<>();
        for (Player player : players)
            this.players.put(player.toString(), player);
        cards = new ArrayList<>();
        tableGems = 0;
        activeRound = true;
        clearGems();
    }

    void update(ArrayList<String> leave) {
        int leaveCount = leave.size();
        if (leaveCount != 0) {
            for (String player : leave) {
                players.get(player).addActiveGems(tableGems / leaveCount);
                players.get(player).addGems();
                players.get(player).setActive(false);
                players.remove(player);
            }

            tableGems %= leaveCount;
        }
    }

    void add(Card addCard) {
        if (addCard.type() == Card.CardType.HAZARD) {
            for (Card card : cards)
                if (card.type() == Card.CardType.HAZARD)
                    if (card.id().equals(addCard.id())) {
                        activeRound = false;
                    }
        } else if (addCard.type() == Card.CardType.GEM) {
            if (players.size() != 0) {
                addGems(Integer.parseInt(addCard.id()) / players.size());
                tableGems += Integer.parseInt(addCard.id()) % players.size();
            }
        }

        cards.add(addCard);
    }

    ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.addAll(this.players.values());
        return players;
    }

    private void addGems(int count) {
        for (Player player : players.values())
            player.addActiveGems(count);
    }

    private void clearGems() {
        for (Player player : players.values())
            player.removeActiveGems();
    }

    boolean activeRound() {
        return activeRound;
    }

    @Override
    public String toString() {
        String table = "";
        for (Card card : cards)
            table += card + ", ";
        for (Player player : players.values())
            table += player + "(" + "xx" + "), ";

        return "TABLE: " + table + " TABLE GEMS - " + tableGems;
    }

    ArrayList<Card> getCards() {
        ArrayList<Card> table = new ArrayList<>();
        table.addAll(removedCards);
        table.addAll(cards);
        Collections.reverse(table);
        return table;
    }
}
