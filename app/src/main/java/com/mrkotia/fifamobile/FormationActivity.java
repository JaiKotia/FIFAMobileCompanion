package com.mrkotia.fifamobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class FormationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formation);

        Button btn_f433f = findViewById(R.id.btn_f433f);
        Button btn_f433a = findViewById(R.id.btn_f433a);

        btn_f433f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formation_selected="f433f";
                Intent intent = new Intent(FormationActivity.this, MainActivity.class);
                intent.putExtra("formation_selected", formation_selected);
                startActivity(intent);

            }
        });

        btn_f433a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formation_selected="f433a";
                Intent intent = new Intent(FormationActivity.this, MainActivity.class);
                intent.putExtra("formation_selected", formation_selected);
                startActivity(intent);

            }
        });
    }
}
