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
import com.buchereli.deepdiveandroid.util.GameState;
import com.buchereli.deepdiveandroid.util.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PassPlayActivity extends FragmentActivity {

    private final HashMap<String, PlayerTab> playerTabs = new HashMap<>();
    private GameState gameState;
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

        // TODO this information should be passed from the create game window
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Eli"));
        players.add(new Player("Sam"));
        players.add(new Player("Tom"));
        players.add(new Player("Kevin"));

        game = new Game(getAssets(), players);
        table = new ArrayList<>();

        for (Player player : players) {
            PlayerTab playerTab = PlayerTab.newInstance(player.toString());
            playerTabs.put(player.toString(), playerTab);
            addFragment(R.id.players,
                    playerTab, "PLAYER TAB FRAGMENT " + player.toString());
        }

        update();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stayButton:
                game.stay();
                clearPlayerTab();
                update();
                break;
            case R.id.leaveButton:
                game.leave();
                clearPlayerTab();
                update();
                break;
            case R.id.popupFragment_button:
                popup.buttonPressed(this);
                break;
        }
    }

    public void update() {
        gameState = game.getGameState();
        System.out.println(gameState.getState());

        if (gameState.getState().equals("CHOICES")) {
            checkCards();
        } else if (gameState.getState().equals("NEW ROUND")) {
            System.out.println("NEW ROUND POPUP!");
            game.newRound();
            update();
        } else if (gameState.getState().equals("ROUND OVER")) {
            System.out.println("ROUND OVER POPUP!");
            game.nextRound();
            update();
        }
    }

    // Check if table has same cards as gameState
    public void checkCards() {
        // Get cards from gameState
        ArrayList<Card> cards = gameState.getTable();

        // If table does not have the same cards, display card popup
        if (!tableMatch()) {
            Card card = cards.get(0);
            displayCardPopup(card.type().toString(), card.id());
            return;
        }

        // If table does match then display turn popup
        displayTurnPopup();
    }

    // Update table to match gameState
    public void updateTable() {
        ArrayList<Card> cards = gameState.getTable();

        for (CardFragment card : table)
            card.remove();
        table = new ArrayList<>();

        for (Card card : cards) {
            CardFragment cardFragment = CardFragment.newInstance(card.type().toString(), card.id(), card.x());
            table.add(cardFragment);
            addFragment(R.id.table, cardFragment, "CARD FRAGMENT");
        }
    }

    public boolean tableMatch() {
        ArrayList<Card> cards = gameState.getTable();

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
        popup = TurnPopup.newInstance(gameState.getTurn());
        addFragment(android.R.id.content, popup, "TURN POPUP FRAGMENT");
    }

    // Clears the player tab to hide saved coins and remove border
    public void clearPlayerTab() {
        ArrayList<Player> players = gameState.getPlayers();

        for (Player player : players) {
            String name = player.toString();
            playerTabs.get(name).updateCoins(-1);
            if (player.isActive())
                playerTabs.get(name).setOverlay(PlayerTab.OverlayState.NONE);
            else
                playerTabs.get(name).setOverlay(PlayerTab.OverlayState.GREY);
        }

    }

    // Updates the player tab
    public void updatePlayerTab() {
        String turn = gameState.getTurn();
        ArrayList<Player> players = gameState.getPlayers();

        for (Player player : players) {
            String name = player.toString();
            if (turn.equals(name)) {
                playerTabs.get(name).setOverlay(PlayerTab.OverlayState.BORDER);
                playerTabs.get(name).updateCoins(player.getGems());
            } else if (player.isActive())
                playerTabs.get(name).setOverlay(PlayerTab.OverlayState.NONE);
            else
                playerTabs.get(name).setOverlay(PlayerTab.OverlayState.GREY);

            // TODO move this so it only gets called on round update
            playerTabs.get(name).updateActiveCoins(player.getActiveGems());
        }
    }

    // Method to make adding fragments easier
    public void addFragment(int id, Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(id, fragment, tag)
                .disallowAddToBackStack()
                .commit();
    }
}
