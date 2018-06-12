package com.mrkotia.fifamobile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PlayerSelect extends AppCompatActivity {

    private TextView txtResponse;

    private String startURL = "https://eaassets-a.akamaihd.net/fifa/u/f/fm18/prod2/s/static/players/players_18/p";
    private String endURL = ".png";

    private String searchID;

    private SearchAdapter oldAdapter;
    private ArrayList<String> searchResults = new ArrayList<String>();
    private String add;
    private PlayerSearchAdapter adapter;
    private ArrayList<PlayerSearchObject> playerSearchObjects = new ArrayList<PlayerSearchObject>();

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

        final DatabaseHandler db = new DatabaseHandler(this);

        /**txtResponse = findViewById(R.id.check);
        txtResponse.setMovementMethod(new ScrollingMovementMethod());

        Button selectPlayer = findViewById(R.id.select_player);

        final ImageView playerImage = findViewById(R.id.playerImage);
        **/

        final ListView playerListView = findViewById(R.id.player_list_view);

        playerListView.setVisibility(View.INVISIBLE);

        //oldAdapter = new SearchAdapter(this, searchResults);
        adapter = new PlayerSearchAdapter(this, playerSearchObjects);
        playerListView.setAdapter(adapter);

        final EditText search = findViewById(R.id.player_search_editbox);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                /**PlayerSelect.this.adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
                playerListView.setVisibility(View.VISIBLE);**/
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>2) {
                    /**searchResults = db.getPlayerSearchResult(s.toString());
                    playerListView.setAdapter(new SearchAdapter(getApplicationContext(), searchResults));
                    **/

                    playerSearchObjects = db.getPlayerSearchResult(s.toString());
                    playerListView.setAdapter(new PlayerSearchAdapter(getApplicationContext(), playerSearchObjects));
                    adapter.notifyDataSetChanged();
                    playerListView.setVisibility(View.VISIBLE);


                }
            }
        });

        /**
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
                        txtResponse.setText(player_position_list.get(n));
                        String finalURL = startURL + searchID + endURL;
                        playerImage.setImageBitmap(getBitmapFromURL(finalURL));
                    }
                }
            }
        });
         **/
    }

}
