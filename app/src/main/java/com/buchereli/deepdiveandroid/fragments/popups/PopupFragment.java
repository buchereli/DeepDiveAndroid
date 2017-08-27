package com.buchereli.deepdiveandroid.fragments.popups;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.buchereli.deepdiveandroid.PassPlayActivity;

/**
 * Created by buche on 8/26/2017.
 */

public abstract class PopupFragment extends Fragment {

    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container,
                                      Bundle savedInstanceState);

    public abstract boolean buttonPressed(PassPlayActivity gameActivity);

    public void remove() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
