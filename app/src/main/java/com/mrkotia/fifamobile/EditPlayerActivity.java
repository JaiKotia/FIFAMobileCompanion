package com.mrkotia.fifamobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EditPlayerActivity extends AppCompatActivity {


    private String startURL = "https://eaassets-a.akamaihd.net/fifa/u/f/fm18/prod2/s/static/players/players_18/p";
    private String endURL = ".png";
    private String ImageID = null;
    private PlayerSearchObject player;
    private static Bitmap myBitmap;

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src", src);
            java.net.URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        player  = (PlayerSearchObject) getIntent().getSerializableExtra("PlayerSearchObject");


        setContentView(R.layout.activity_edit_player);
        final ImageView playerCardImage = findViewById(R.id.playerCardImage);
        final TextView playerCardName = findViewById(R.id.playerCardName);
        final TextView playerCardOVR = findViewById(R.id.playerCardOVR);
        final TextView playerCardPosition = findViewById(R.id.playerCardPosition);
        final Button btn_show_all_stats = findViewById(R.id.btn_showAllStats);
        final Button btn_train_player = findViewById(R.id.btn_trainPlayer);
        final Button btn_add_to_team = findViewById(R.id.btn_addToTeam);

        final DatabaseHandler db = new DatabaseHandler(this);

        switch (player.getTags()) {
            case "Base":
                ImageID = player.getPlayerID();
                break;
            case "TOTY":
                ImageID = player.getPlayerID() + "_TOTY";
                break;
            case "TOTS":
                ImageID = player.getPlayerID() + "_TOTS";
                break;
            case "TOTW":
                ImageID = player.getPlayerID() + "_TOTW";
                break;
            case "EURO Star":
                ImageID = player.getPlayerID() + "_EURO";
                break;
            default:
                ImageID = ImageID = player.getPlayerID();
                break;
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                playerCardName.setText(player.getPlayerName());
                playerCardOVR.setText(player.getBaseOVR());
                playerCardPosition.setText(player.getPosition());

                String finalURL = startURL + ImageID + endURL;
                playerCardImage.setImageBitmap(getBitmapFromURL(finalURL));

            }

        });

        btn_show_all_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditPlayerActivity.this, PlayerStatsActivity.class);
                intent.putExtra("PlayerStats", player);
                startActivity(intent);
            }
        });



        btn_add_to_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToTeam();
            }
        });
    }

    public void addToTeam() {
//        String cardID=player.getCardID();
//        String query = "SELECT * FROM card_table WHEREE id ='" + cardID + "'";
        Intent intent = new Intent(EditPlayerActivity.this, MainActivity.class);
        intent.putExtra("pos", getIntent().getStringExtra("pos"));
        intent.putExtra("PlayerObject", player);
        intent.putExtra("playerIMG", myBitmap);
        startActivity(intent);

    }

}

