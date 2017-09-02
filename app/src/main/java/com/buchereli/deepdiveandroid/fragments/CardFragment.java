package com.buchereli.deepdiveandroid.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.buchereli.deepdiveandroid.AssetManager;
import com.buchereli.deepdiveandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardFragment extends Fragment {
    private static final String CARD_TYPE = "type";
    private static final String CARD_ID = "id";
    private static final String CARD_X = "x";

    private String type;
    private String id;
    private boolean x;

    private OnFragmentInteractionListener mListener;

    public CardFragment() {
        // Required empty public constructor
    }

    /**
     * @param type The type of card
     * @param id   The id for that type of card
     * @return A new instance of fragment CardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardFragment newInstance(String type, String id, boolean x) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putString(CARD_TYPE, type);
        args.putString(CARD_ID, id);
        args.putBoolean(CARD_X, x);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(CARD_TYPE);
            id = getArguments().getString(CARD_ID);
            x = getArguments().getBoolean(CARD_X);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_card, container, false);

        if (!x) {
            View xView = v.findViewById(R.id.cardFragment_x);
            ((ViewGroup) xView.getParent()).removeView(xView);
        }

        View cardBackground = v.findViewById(R.id.cardBackground);
        ImageView icon = (ImageView) v.findViewById(R.id.icon);
        TextView count;
        switch (type) {
            case "GEM":
                count = (TextView) v.findViewById(R.id.countTop);
                count.setText(id);
                count = (TextView) v.findViewById(R.id.countBottom);
                count.setText(id);
                cardBackground.setBackgroundResource(R.drawable.radial_gradient_artifact);
                icon.setImageBitmap(AssetManager.get(R.drawable.coin));
                break;
            case "HAZARD":
                count = (TextView) v.findViewById(R.id.countTop);
                count.setText(id);
                count = (TextView) v.findViewById(R.id.countBottom);
                count.setText(id);
                cardBackground.setBackgroundResource(R.drawable.radial_gradient_hazard);
                break;
            default:
                cardBackground.setBackgroundResource(R.drawable.radial_gradient_artifact);
                break;
        }
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public String toString() {
        return type + " - " + id;
    }

    public void remove() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
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
