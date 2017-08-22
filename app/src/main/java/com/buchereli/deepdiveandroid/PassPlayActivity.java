package com.buchereli.deepdiveandroid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.buchereli.deepdiveandroid.util.Game;
import com.buchereli.deepdiveandroid.util.Player;

import java.util.ArrayList;

public class PassPlayActivity extends FragmentActivity {

    private Game game;
    private View layout;
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
        layout = findViewById(R.id.layout);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.stayButton:
                game.stay();
                displayTurnPopup(layout);
                break;
            case R.id.leaveButton:
                game.leave();
                displayTurnPopup(layout);
                break;
            case R.id.continueButton:
                turnPopup.remove();
                break;
        }

//        someFragment.myClickMethod(v);
    }

    public void displayTurnPopup(View view) {
        System.out.println("displaying turn popup onClick");

        turnPopup = TurnPopup.newInstance(game.turn());

        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content,
                        turnPopup, "TURN POPUP FRAGMENT")
                .disallowAddToBackStack()
                .commit();


//        View popupView = getLayoutInflater().inflate(R.layout.fragment_turn_popup, this);
//
//        PopupWindow popupWindow = new PopupWindow(popupView,
//                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
//
//        // Example: If you have a TextView inside `popup_layout.xml`
//        TextView tv = (TextView) popupView.findViewById(R.id.playerName);
//
//        String name = "ELI";
//
//        tv.setText(name);
//
//        // If the PopupWindow should be focusable
//        popupWindow.setFocusable(true);
//
//        // If you need the PopupWindow to dismiss when when touched outside
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.CYAN));
//
//        // Using location, the PopupWindow will be displayed right under anchorView
//        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
//                0, 0);

    }
}
