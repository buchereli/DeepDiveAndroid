package com.buchereli.deepdiveandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.buchereli.deepdiveandroid.fragments.CardFragment;
import com.buchereli.deepdiveandroid.fragments.PlayerTab;
import com.buchereli.deepdiveandroid.fragments.popups.DrawCardPopup;
import com.buchereli.deepdiveandroid.fragments.popups.PopupFragment;
import com.buchereli.deepdiveandroid.fragments.popups.TurnPopup;
import com.buchereli.deepdiveandroid.util.Card;
import com.buchereli.deepdiveandroid.util.Game;
import com.buchereli.deepdiveandroid.util.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class PassPlayActivity extends FragmentActivity {

    private final HashMap<String, PlayerTab> playerTabs = new HashMap<>();
    private Game game;
    private PopupFragment popup;
    private ArrayList<CardFragment> table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passplay);

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(R.drawable.coin);
        AssetManager.load(this, ids);

        LinkedHashMap<String, Player> players = new LinkedHashMap<>();
        players.put("Eli", new Player("Eli"));
        players.put("Sam", new Player("Sam"));
        players.put("Tom", new Player("Tom"));
        players.put("Kevin", new Player("Kevin"));

        game = new Game(getAssets(), players);
        table = new ArrayList<>();

        for (Player player : players.values()) {
            PlayerTab playerTab = PlayerTab.newInstance(player.toString());
            playerTabs.put(player.toString(), playerTab);
            addFragment(R.id.players,
                    playerTab, "PLAYER TAB FRAGMENT " + player.toString());
        }

        checkCards();
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
            case R.id.popupFragment_button:
                popup.buttonPressed(this);
                break;
        }
    }

    public void checkCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(game.table());
        Collections.reverse(cards);

        if (cards.size() != table.size()) {
            Card card = cards.get(0);
            displayCardPopup(card.type().toString(), card.id());
            return;
        }

        if (!tableMatch(cards)) {
            updateTable();
        }

        displayTurnPopup();
    }

    public void updateTable() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(game.table());
        Collections.reverse(cards);

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

    private void displayCardPopup(String type, String id) {
        popup = DrawCardPopup.newInstance(type, id);
        addFragment(android.R.id.content, popup, "DRAW CARD FRAGMENT");
    }

    public void displayTurnPopup() {
        popup = TurnPopup.newInstance(game.turn());
        addFragment(android.R.id.content, popup, "TURN POPUP FRAGMENT");
    }

    public void clearPlayerTab() {
        ArrayList<String> activePlayers = game.getActivePlayers();
        for (PlayerTab playerTab : playerTabs.values()) {
            playerTab.updateCoins(-1);
            if (activePlayers.contains(playerTab.getName()))
                playerTab.setOverlay(PlayerTab.OverlayState.NONE);
            else
                playerTab.setOverlay(PlayerTab.OverlayState.GREY);
        }

    }

    public void updatePlayerTab() {
        String turn = game.turn();
        ArrayList<String> activePlayers = game.getActivePlayers();

        for (PlayerTab playerTab : playerTabs.values()) {
            if (turn.equals(playerTab.getName())) {
                playerTab.setOverlay(PlayerTab.OverlayState.BORDER);
                playerTab.updateCoins(game.getGems(playerTab.getName()));
            } else if (activePlayers.contains(playerTab.getName()))
                playerTab.setOverlay(PlayerTab.OverlayState.NONE);
            else
                playerTab.setOverlay(PlayerTab.OverlayState.GREY);

            playerTab.updateActiveCoins(game.getActiveGems(playerTab.getName()));
        }
    }

    public void addFragment(int id, Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(id, fragment, tag)
                .disallowAddToBackStack()
                .commit();
    }
}
