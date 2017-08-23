package com.buchereli.deepdiveandroid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.buchereli.deepdiveandroid.fragments.CardFragment;
import com.buchereli.deepdiveandroid.fragments.PlayerTab;
import com.buchereli.deepdiveandroid.fragments.TurnPopup;
import com.buchereli.deepdiveandroid.util.Card;
import com.buchereli.deepdiveandroid.util.Game;
import com.buchereli.deepdiveandroid.util.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PassPlayActivity extends FragmentActivity {

    private final HashMap<String, PlayerTab> playerTabs = new HashMap<>();
    private Game game;
    private TurnPopup turnPopup;
    private ArrayList<CardFragment> table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passplay);

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Eli"));
        players.add(new Player("Sam"));
        players.add(new Player("Tom"));
        players.add(new Player("Kevin"));

        game = new Game(getAssets(), players);
        table = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            String player = players.get(i).toString();
            PlayerTab playerTab = PlayerTab.newInstance(player);
            playerTabs.put(player, playerTab);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.players,
                            playerTab, "PLAYER TAB FRAGMENT " + i)
                    .disallowAddToBackStack()
                    .commit();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stayButton:
                game.stay();
                choiceUpdate();
                break;
            case R.id.leaveButton:
                game.leave();
                choiceUpdate();
                break;
            case R.id.continueButton:
                turnPopup.remove();
                break;
        }
    }

    public void choiceUpdate() {
        displayTurnPopup();
        updatePlayerTab();
        checkCards();
    }

    public void checkCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(game.table());
        Collections.reverse(cards);
        if (!tableMatch(cards)) {
            for (CardFragment card : table)
                card.remove();
            table = new ArrayList<>();

            for (Card card : cards) {
                CardFragment cardFragment = CardFragment.newInstance(card.type().toString(), card.id());
                table.add(cardFragment);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.table,
                                cardFragment, "CARD FRAGMENT")
                        .disallowAddToBackStack()
                        .commit();
            }
        }
    }

    public boolean tableMatch(ArrayList<Card> cards) {
        if (cards.size() != table.size())
            return false;

        for (int i = 0; i < cards.size(); i++)
            if (!cards.get(i).toString().equals(table.get(i).toString()))
                return false;

        return true;
    }

    public void displayTurnPopup() {
        turnPopup = TurnPopup.newInstance(game.turn());

        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content,
                        turnPopup, "TURN POPUP FRAGMENT")
                .disallowAddToBackStack()
                .commit();
    }

    public void updatePlayerTab() {
        for (PlayerTab playerTab : playerTabs.values())
            playerTab.removeBorder();

        playerTabs.get(game.turn()).addBorder();
    }
}
