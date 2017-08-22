package com.buchereli.deepdiveandroid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.buchereli.deepdiveandroid.util.Game;
import com.buchereli.deepdiveandroid.util.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PassPlayActivity extends FragmentActivity {

    private final HashMap<String, PlayerTab> playerTabs = new HashMap<>();
    private Game game;
    private TurnPopup turnPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passplay);

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Eli"));
        players.add(new Player("Sam"));
        players.add(new Player("Tom"));

        game = new Game(getAssets(), players);

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
                displayTurnPopup();
                updatePlayerTab();
                break;
            case R.id.leaveButton:
                game.leave();
                displayTurnPopup();
                updatePlayerTab();
                break;
            case R.id.continueButton:
                turnPopup.remove();
                break;
        }
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
