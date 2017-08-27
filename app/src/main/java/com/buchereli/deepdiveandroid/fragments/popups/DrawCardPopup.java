package com.buchereli.deepdiveandroid.fragments.popups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buchereli.deepdiveandroid.PassPlayActivity;
import com.buchereli.deepdiveandroid.R;
import com.buchereli.deepdiveandroid.fragments.CardFragment;

public class DrawCardPopup extends PopupFragment {
    private static final String CARD_TYPE = "type";
    private static final String CARD_ID = "id";

    private String type;
    private String id;

    private boolean cardDrawn;
    private TextView text;
    private TextView buttonText;

    public DrawCardPopup() {
        cardDrawn = false;
    }

    /**
     * @param type The type of card
     * @param id   The id for that type of card
     * @return A new instance of fragment DrawCardPopup.
     */
    public static DrawCardPopup newInstance(String type, String id) {
        DrawCardPopup fragment = new DrawCardPopup();
        Bundle args = new Bundle();
        args.putString(CARD_TYPE, type);
        args.putString(CARD_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(CARD_TYPE);
            id = getArguments().getString(CARD_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_draw_card, container, false);
        text = (TextView) v.findViewById(R.id.textView);
        buttonText = (TextView) v.findViewById(R.id.drawCardButton);
        return v;
    }

    // Returns true if fragment is removed
    // Otherwise returns false
    public boolean buttonPressed(PassPlayActivity gameActivity) {
        if (!cardDrawn) {
            ((ViewGroup) text.getParent()).removeView(text);
            buttonText.setText("CONTINUE");
            cardDrawn = true;
            gameActivity.addFragment(R.id.drawCard_cardFragment, CardFragment.newInstance(type, id),
                    "CARD FRAGMENT");
            return false;
        }
        remove();
        return true;
    }
}
