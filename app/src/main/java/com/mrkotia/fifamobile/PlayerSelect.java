package com.mrkotia.fifamobile;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static com.mrkotia.fifamobile.SplashScreen.fullNames;
import static com.mrkotia.fifamobile.SplashScreen.idlist;
import static com.mrkotia.fifamobile.SplashScreen.names;
import static com.mrkotia.fifamobile.SplashScreen.player_id_list;
import static com.mrkotia.fifamobile.SplashScreen.player_position_list;
import static com.mrkotia.fifamobile.SplashScreen.player_tags_list;


public class PlayerSelect extends AppCompatActivity {

    private TextView txtResponse;

    private String startURL = "https://eaassets-a.akamaihd.net/fifa/u/f/fm18/prod2/s/static/players/players_18/p";
    private String endURL = ".png";

    private String searchID;

    private PlayerSearchAdapter adapter;

    private String add;

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
        setContentView(R.layout.activity_player_select);


        txtResponse = findViewById(R.id.check);
        txtResponse.setMovementMethod(new ScrollingMovementMethod());

        Button selectPlayer = findViewById(R.id.select_player);

        final ImageView playerImage = findViewById(R.id.playerImage);

        final ListView playerListView = findViewById(R.id.player_list_view);

        adapter = new PlayerSearchAdapter(this, fullNames);
        playerListView.setAdapter(adapter);

        final EditText search = findViewById(R.id.player_search_editbox);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                PlayerSelect.this.adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        selectPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {


                        String event = "LLR";
                        for (int i = 0; i < player_tags_list.size(); i++) {
                            if (player_tags_list.get(i).contains(event)) {
                                String sid = player_id_list.get(i);
                                for (int j = 0; j < idlist.size(); j++) {
                                    if (sid.equals(idlist.get(j))) {
                                        add+= fullNames.get(j);
                                    }
                                }

                            }
                        }

                    }
                });

                txtResponse.setText(add);

            }
        });


        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = playerListView.getItemAtPosition(i).toString();

                for (int n = 0; n < fullNames.size(); n++) {
                    if (fullNames.get(n).equals(s)) {
                        searchID = idlist.get(n);
                        txtResponse.setText(player_position_lit.get(n));
                        String finalURL = startURL + searchID + endURL;
                        playerImage.setImageBitmap(getBitmapFromURL(finalURL));
                    }
                }
            }
        });

    }

}
