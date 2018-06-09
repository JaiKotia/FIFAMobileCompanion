package com.mrkotia.fifamobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SplashScreen extends AppCompatActivity {

    static List<String> names = new ArrayList<String>();
    static List<String> lastnames = new ArrayList<String>();
    static ArrayList<String> idlist = new ArrayList<String>();
    static ArrayList<String> fullNames = new ArrayList<>();
    static List<Player> playerList = new ArrayList<>();

    static ArrayList<String> card_id_list = new ArrayList<String>();
    static ArrayList<String> player_id_list = new ArrayList<String>();
    static ArrayList<String> player_position_list = new ArrayList<String>();
    static ArrayList<String> player_tags_list = new ArrayList<String>();


    static List<Card> cardList = new ArrayList<>();

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



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
                        player_position_list.add(cd.getPosition());
                        player_tags_list.add(cd.getTags());


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

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);


                } catch (ParserConfigurationException | SAXException | IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }

}
