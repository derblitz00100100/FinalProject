package com.example.finalproject;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CardsFragment extends Fragment {
    // make it accept arguments
    // clicking on any of the rarities will change the argument
    // all rarities lead to this fragment
    // make method to sort json into list of only rarity selected
    private String cardType;
    private ListView cardListView;
    private CardAdapter cardAdapter;

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

    private class CardAdapter extends ArrayAdapter<Card> {
        // make an instance variable to keep track of the hero list
        private List<Card> cardsList;

        public CardAdapter(List<Card> cardsList) {
            super(CardsFragment.this, -1, cardsList);
            this.cardsList = cardsList;

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_hero, parent, false);
            }

            TextView textViewName = convertView.findViewById(R.id.textview_itemcard_name);
            TextView textViewElixir = convertView.findViewById(R.id.textview_itemcard_elixir);
            TextView textViewType = convertView.findViewById(R.id.textview_itemcard_type);

            textViewName.setText(cardsList.get(position).getName());
            textViewElixir.setText(cardsList.get(position).getElixir());
            textViewType.setText(String.valueOf(cardsList.get(position).getType()));

            return convertView;
        }
    }

    // this is for the

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_heroeslist_sorting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_heroeslist_sort_by_name:
                sortByName();
                return true;
            case R.id.action_heroeslist_sort_by_rank:
                sortByRank();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
