package com.mrkotia.fifamobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

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

                try {

                    DatabaseHandler mydb = new DatabaseHandler(getApplicationContext());
                    mydb.initializeAdapter();

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

}
