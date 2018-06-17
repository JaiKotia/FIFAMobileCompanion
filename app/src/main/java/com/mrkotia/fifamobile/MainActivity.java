package com.mrkotia.fifamobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout st = findViewById(R.id.st);
        TextView mainCardText = findViewById(R.id.mainCardText);
        ImageView stIMG = findViewById(R.id.mainPlayerImage);
        TextView mainCardOVR = findViewById(R.id.mainCardOVR);
        TextView mainCardPos = findViewById(R.id.mainCardPos);
        ImageView mainCardBG = findViewById(R.id.mainCardBG);

        PlayerSearchObject player  = (PlayerSearchObject) getIntent().getSerializableExtra("PlayerObject");
        Bitmap playerIMG = (Bitmap) getIntent().getParcelableExtra("playerIMG");


        if(player!=null) {
            if(playerIMG!=null){
                stIMG.setImageBitmap(playerIMG);
            }
            st.setBackground(null);
            mainCardText.setText(player.getPlayerName());
            mainCardOVR.setText(player.getBaseOVR());
            mainCardPos.setText(player.getPosition());
            mainCardBG.setImageDrawable(getResources().getDrawable(R.drawable.card_bg));
        }

        String pos=getIntent().getStringExtra("pos");

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



        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayerSelect.class);
                intent.putExtra("pos", "ST");
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