package com.buchereli.deepdiveandroid.fragments;

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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DrawCardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DrawCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrawCardFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private boolean cardDrawn;
    private TextView text;
    private TextView buttonText;

    public DrawCardFragment() {
        // Required empty public constructor
        cardDrawn = false;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DrawCardFragment.
     */
    public static DrawCardFragment newInstance() {
        DrawCardFragment fragment = new DrawCardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    // Returns true if fragment is removed
    // Otherwise returns false
    public boolean buttonPressed(PassPlayActivity gameActivity) {
        if (!cardDrawn) {
            // TODO remove TextView
            text.setText("");
            buttonText.setText("CONTINUE");
            cardDrawn = true;
            gameActivity.addFragment(R.id.drawCard_cardFragment, CardFragment.newInstance("GEM", "2"), "CARD FRAGMENT");
            return false;
        }
        remove();
        return true;
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
