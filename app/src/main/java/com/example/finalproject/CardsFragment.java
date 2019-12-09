package com.example.finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CardsFragment extends Fragment {
    // make it accept arguments
    // clicking on any of the rarities will change the argument
    // all rarities lead to this fragment
    // make method to sort json into list of only rarity selected
    private String cardType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        wireWidgets(rootView);
        cardType = getArguments().getString(MainActivity.EXTRA_CARDTYPE);

        return rootView;
    }

    private void wireWidgets(View rootView) {

    }
}
