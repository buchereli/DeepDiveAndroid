package com.buchereli.deepdiveandroid;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlayerTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlayerTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayerTab extends Fragment {
    private static final String PLAYER_NAME = "name";

    private String playerName;

    private View tab;
    private OnFragmentInteractionListener mListener;

    public PlayerTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Players name
     * @return A new instance of fragment PlayerTab.
     */
    public static PlayerTab newInstance(String name) {
        PlayerTab fragment = new PlayerTab();
        Bundle args = new Bundle();
        args.putString(PLAYER_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            playerName = getArguments().getString(PLAYER_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_player_tab, container, false);

        TextView name = (TextView) v.findViewById(R.id.playerName);
        name.setText(playerName);

        tab = v.findViewById(R.id.layout);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    public void removeBorder() {
        tab.setBackgroundColor(Color.WHITE);
    }

    public void addBorder() {
        tab.setBackgroundResource(R.drawable.playertab_border);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
