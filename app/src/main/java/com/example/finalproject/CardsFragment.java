package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CardsFragment extends Fragment {
    // make it accept arguments
    // clicking on any of the rarities will change the argument
    // all rarities lead to this fragment
    // make method to sort json into list of only rarity selected
    private String cardRarity;
    private ListView cardListView;
    private List<Card> cardList;
    private CardAdapter cardAdapter;

    public static final String EXTRA_CARD = "card selected";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        wireWidgets(rootView);

        cardRarity = getArguments().getString(MainActivity.EXTRA_CARDRARITY);

        InputStream JsonFileInputStream = getResources().openRawResource(R.raw.cards);
        String jsonString = readTextFile(JsonFileInputStream);
        Gson gson = new Gson();
        Card[] cards = gson.fromJson(jsonString, Card[].class);
        cardList = new LinkedList<Card>(Arrays.asList(cards));
        Log.d("from card adapter", "onCreate: " + cardList.toString());

        sortCardByType(cardRarity);

        cardAdapter = new CardAdapter(cardList);
        cardListView.setAdapter(cardAdapter);

        cardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent targetIntent = new Intent(CardsFragment.this.getActivity(), CardDetailActivity.class);
                targetIntent.putExtra(EXTRA_CARD, cardList.get(position));
                startActivity(targetIntent);
            }
        });
        return rootView;
    }

    private void wireWidgets(View rootView) {
        cardListView = rootView.findViewById(R.id.listview_card_cardlistview);
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

    public void sortCardByType(String cardType) {
        for (int i = this.cardList.size() - 1; i >= 0; i--) {
            if(!(this.cardList.get(i).getRarity().equals(cardRarity))) {
                cardList.remove(i);
            }
        }
    }

    private class CardAdapter extends ArrayAdapter<Card> {
        // make an instance variable to keep track of the hero list
        private List<Card> cardsList;

        public CardAdapter(List<Card> cardsList) {
            super(CardsFragment.this.getActivity(), -1, cardsList);
            this.cardsList = cardsList;

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_card, parent, false);
            }

            TextView textViewName = convertView.findViewById(R.id.textview_itemcard_name);
            TextView textViewElixir = convertView.findViewById(R.id.textview_itemcard_elixir);
            TextView textViewType = convertView.findViewById(R.id.textview_itemcard_type);

            textViewName.setText(cardsList.get(position).getName());
            textViewType.setText(cardsList.get(position).getType());
            textViewElixir.setText(String.valueOf(cardsList.get(position).getElixir()));

            return convertView;
        }
    }
}
