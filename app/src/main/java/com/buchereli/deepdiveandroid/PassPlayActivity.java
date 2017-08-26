package com.buchereli.deepdiveandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.buchereli.deepdiveandroid.fragments.CardFragment;
import com.buchereli.deepdiveandroid.fragments.DrawCardFragment;
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
    private DrawCardFragment drawCardFragment;
    private ArrayList<CardFragment> table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passplay);

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(R.drawable.coin);
        AssetManager.load(this, ids);

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
            addFragment(R.id.players,
                    playerTab, "PLAYER TAB FRAGMENT " + i);
        }

        displayCardPopup();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stayButton:
                game.stay();
                clearPlayerTab();
                checkCards();
                break;
            case R.id.leaveButton:
                game.leave();
                clearPlayerTab();
                checkCards();
                break;
            case R.id.continueButton:
                turnPopup.remove();
                updatePlayerTab();
                break;
            case R.id.drawCardButton:
                if (drawCardFragment.buttonPressed(this)) {
                    ArrayList<Card> cards = new ArrayList<>();
                    cards.addAll(game.table());
                    Collections.reverse(cards);
                    updateTable(cards);
                    displayTurnPopup();
                }
                break;
        }
    }

    public void checkCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(game.table());
        Collections.reverse(cards);

        if (cards.size() != table.size()) {
            clearPlayerTab();
            displayCardPopup();
            return;
        }

        if (!tableMatch(cards)) {
            updateTable(cards);
        }

        displayTurnPopup();
    }

    public void updateTable(ArrayList<Card> cards) {
        for (CardFragment card : table)
            card.remove();
        table = new ArrayList<>();

        for (Card card : cards) {
            CardFragment cardFragment = CardFragment.newInstance(card.type().toString(), card.id());
            table.add(cardFragment);
            addFragment(R.id.table, cardFragment, "CARD FRAGMENT");
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

    private void displayCardPopup() {
        drawCardFragment = DrawCardFragment.newInstance();
        addFragment(android.R.id.content, drawCardFragment, "DRAW CARD FRAGMENT");
    }

    public void displayTurnPopup() {
        turnPopup = TurnPopup.newInstance(game.turn());
        addFragment(android.R.id.content, turnPopup, "TURN POPUP FRAGMENT");
    }

    public void clearPlayerTab() {
        ArrayList<String> activePlayers = game.getActivePlayers();
        for (PlayerTab playerTab : playerTabs.values()) {
            if (activePlayers.contains(playerTab.getName()))
                playerTab.setOverlay(PlayerTab.OverlayState.NONE);
            else
                playerTab.setOverlay(PlayerTab.OverlayState.GREY);
        }

    }

    public void updatePlayerTab() {
        String turn = game.turn();
        ArrayList<String> activePlayers = game.getActivePlayers();

        for (PlayerTab playerTab : playerTabs.values())
            if (turn.equals(playerTab.getName()))
                playerTab.setOverlay(PlayerTab.OverlayState.BORDER);
            else if (activePlayers.contains(playerTab.getName()))
                playerTab.setOverlay(PlayerTab.OverlayState.NONE);
            else
                playerTab.setOverlay(PlayerTab.OverlayState.GREY);
    }

    public void addFragment(int id, Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(id, fragment, tag)
                .disallowAddToBackStack()
                .commit();
    }
}
