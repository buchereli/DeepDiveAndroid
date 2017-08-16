package com.buchereli.deepdiveandroid;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.buchereli.deepdiveandroid.util.Game;
import com.buchereli.deepdiveandroid.util.Player;

import java.util.ArrayList;

public class PassPlayActivity extends FragmentActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passplay);

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Eli"));
        players.add(new Player("Sam"));
        players.add(new Player("Tom"));

        game = new Game(getAssets(), players);
//
//        Button stayButton = (Button) findViewById(R.id.stayButton);
//        stayButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                System.out.println("Stay button clicked");
//                game.stay();
//            }
//        });
//
//        Button leaveButton = (Button) findViewById(R.id.leaveButton);
//        leaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                System.out.println("Stay button clicked");
//                game.leave();
//            }
//        });
    }
}
