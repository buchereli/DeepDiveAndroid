package com.buchereli.deepdiveandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button passPlayButton = (Button) findViewById(R.id.passPlayButton);
        passPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                System.out.println("Pass and Play button clicked");
                startPassPlay();
            }
        });
    }

    public void startPassPlay() {
        Intent intent = new Intent(this, PassPlayActivity.class);
        startActivity(intent);
    }
}
