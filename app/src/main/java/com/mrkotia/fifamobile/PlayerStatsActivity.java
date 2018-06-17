package com.mrkotia.fifamobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player__stats_);
        final DatabaseHandler db = new DatabaseHandler(this);

        TextView bac = findViewById(R.id.BAC);
        TextView tac = findViewById(R.id.TAC);
        TextView agg = findViewById(R.id.AGG);
        TextView lsa = findViewById(R.id.LSA);
        TextView spa = findViewById(R.id.SPA);


        PlayerSearchObject playerSearchObject = (PlayerSearchObject) getIntent().getSerializableExtra("PlayerStats");

        String cardID=playerSearchObject.getCardID();

        ArrayList<String> stats = db.getPlayerStats(cardID);

        if (stats!=null) {

            bac.setText(stats.get(0));
            tac.setText(stats.get(1));
            lsa.setText(stats.get(2));
            spa.setText(stats.get(3));
            agg.setText(stats.get(4));
        }

    }
}
