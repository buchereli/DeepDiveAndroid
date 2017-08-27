package com.buchereli.deepdiveandroid.fragments.popups;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buchereli.deepdiveandroid.PassPlayActivity;
import com.buchereli.deepdiveandroid.R;

public class TurnPopup extends PopupFragment {
    private static final String CURRENT_TURN = "turn";
    private String turn;

    public TurnPopup() {
    }

    /**
     * @param turn Name of player whose turn it is currently.
     * @return A new instance of fragment TurnPopup.
     */
    public static TurnPopup newInstance(String turn) {
        TurnPopup fragment = new TurnPopup();
        Bundle args = new Bundle();
        args.putString(CURRENT_TURN, turn);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            turn = getArguments().getString(CURRENT_TURN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_turn_popup, container, false);
        TextView playerName = (TextView) v.findViewById(R.id.playerName);
        playerName.setText(turn);
        return v;
    }

    @Override
    public boolean buttonPressed(PassPlayActivity gameActivity) {
        return false;
    }
}
