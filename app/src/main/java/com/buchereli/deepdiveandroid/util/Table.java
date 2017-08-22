package com.buchereli.deepdiveandroid.util;

import java.util.ArrayList;

/**
 * Created by buche on 8/15/2017.
 */
class Table {

    private ArrayList<Card> cards;
    private ArrayList<Player> players;
    private int tableGems, playerGems;
    private boolean activeRound;

    Table(ArrayList<Player> players) {
        init(players);
    }

    void reset(Deck deck, ArrayList<Player> players) {
        deck.add(cards);
        init(players);
    }

    private void init(ArrayList<Player> players) {
        this.players = new ArrayList<Player>();
        this.players.addAll(players);
        cards = new ArrayList<Card>();
        tableGems = 0;
        playerGems = 0;
        activeRound = true;
    }

    void update(ArrayList<Boolean> stay, int leaveCount) {
        if (leaveCount != 0) {
            for (int i = stay.size() - 1; i >= 0; i--) {
                if (!stay.get(i)) {
                    players.get(i).addGems(playerGems + tableGems / leaveCount);
                    players.remove(i);
                }
            }

            tableGems %= leaveCount;
        }
    }

    void add(Card addCard) {
        if (addCard.type() == Card.CardType.HAZARD) {
            for (Card card : cards)
                if (card.type() == Card.CardType.HAZARD)
                    if (card.id().equals(addCard.id()))
                        activeRound = false;
        } else if (addCard.type() == Card.CardType.GEM) {
            if (players.size() != 0) {
                playerGems += Integer.parseInt(addCard.id()) / players.size();
                tableGems += Integer.parseInt(addCard.id()) % players.size();
            }
        }

        if (activeRound)
            cards.add(addCard);
    }

    boolean activeRound() {
        return activeRound;
    }

    ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        String table = "";
        for (Card card : cards)
            table += card + ", ";
        for (Player player : players)
            table += player + "(" + playerGems + "), ";

        return "TABLE: " + table + " TABLE GEMS - " + tableGems;
    }
}
