package com.mrkotia.fifamobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

import org.xml.sax.SAXException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {


                    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                    try {
                        SAXParser saxParser = saxParserFactory.newSAXParser();
                        SAXCardHandler saxHandler = new SAXCardHandler();

                        AssetManager assetManager = getAssets();
                        InputStream inputStream = assetManager.open("card_database.xml");

                        saxParser.parse(inputStream, saxHandler);

                        List<Card> cardList = saxHandler.getCardList();
                        for (Card cd : cardList) {
                            card_id_list.add(cd.getId());
                            player_id_list.add(cd.getPlayerId());
                            dri.add(cd.getDRI());
                        }


                    } catch (ParserConfigurationException | SAXException | IOException e) {
                        e.printStackTrace();
                    }

                }
            });


            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {


                    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                    try {
                        SAXParser saxParser = saxParserFactory.newSAXParser();
                        SAXPlayerHandler saxHandler = new SAXPlayerHandler();

                        AssetManager assetManager = getAssets();
                        InputStream in = assetManager.open("player_database.xml");

                        saxParser.parse(in, saxHandler);

                        List<Player> playerList = saxHandler.getPlayerList();
                        for (Player pl : playerList) {
                            names.add(pl.getFirst());
                            lastnames.add(pl.getLast());
                            idlist.add(pl.getID());
                            String fullName = pl.getFirst() + " " + pl.getLast();
                            fullNames.add(fullName);
                        }


                    } catch (ParserConfigurationException | SAXException | IOException e) {
                        e.printStackTrace();
                    }

                }
            });


            // mark first time has runned.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }

        **/

        ViewFlipper viewFlipper = findViewById(R.id.view_flipper);
        String formation_selected = null;

        if ((formation_selected = getIntent().getStringExtra("formation_selected")) != null) {
            Log.w("Formation", formation_selected);
            switch (formation_selected) {
                case "f433f":
                    viewFlipper.setDisplayedChild(0);
                    break;
                case "f433a":
                    viewFlipper.setDisplayedChild(1);
                    break;
            }
        }


        Button st = findViewById(R.id.st);
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayerSelect.class);
                startActivity(intent);
            }
        });


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button formation = findViewById(R.id.formation);

        formation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormationActivity.class);
                startActivity(intent);
            }
        });

    }

}