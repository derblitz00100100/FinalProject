package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CardDetailActivity extends AppCompatActivity {
    private Button goBackButton;
    private TextView name;
    private TextView elixir;
    private TextView cardType;
    private TextView arena;
    private TextView description;
    private ImageView cardProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        wireWidgets();
        setListeners();
        Intent lastIntent = getIntent();
        Card cardSelected = lastIntent.getParcelableExtra(CardsFragment.EXTRA_CARD);

        name.setText(cardSelected.getName());
        description.setText(cardSelected.getDescription());
        elixir.setText(cardSelected.getElixir() + "");
        cardType.setText(cardSelected.getType() + "");
        arena.setText((cardSelected.getArena() + ""));
        int resourceImage = getResources().getIdentifier(cardSelected.getImage(), "drawable", getPackageName());
        cardProfile.setImageDrawable(getResources().getDrawable(resourceImage));

    }

    private void setListeners() {
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent targetIntent = new Intent(CardDetailActivity.this, CardsFragment.class);
                startActivity(targetIntent);
                finish();
            }
        });
    }

    private void wireWidgets() {
        goBackButton = findViewById(R.id.button_cardDetail_goBack);
        name = findViewById(R.id.textView_cardDetail_detail);
        elixir = findViewById(R.id.textView_cardDetail_elixir);
        cardType = findViewById(R.id.textView_cardDetail_type);
        arena = findViewById(R.id.textView_cardDetail_arena);
        description = findViewById(R.id.textView_cardDetail_detail);
        cardProfile = findViewById(R.id.imageView_cardDetail_image);
    }
}


