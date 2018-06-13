package com.mrkotia.fifamobile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src", src);
            java.net.URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
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
        final PlayerSearchObject player = (PlayerSearchObject) getIntent().getSerializableExtra("PlayerSearchObject");

        setContentView(R.layout.activity_edit_player);
        final ImageView playerCardImage = findViewById(R.id.playerCardImage);
        final TextView playerCardName = findViewById(R.id.playerCardName);
        final TextView playerCardOVR = findViewById(R.id.playerCardOVR);
        final TextView playerCardPosition = findViewById(R.id.playerCardPosition);


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
    }
}

