package com.example.kartik_chauhan.zoohackathon.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kartik_chauhan.zoohackathon.R;

public class PursellerActivity extends AppCompatActivity {
    private Button seller,purchaser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purseller);

        seller = (Button)findViewById(R.id.seller);
        purchaser = (Button)findViewById(R.id.purchaser);

        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PursellerActivity.this,LoginActivity.class));
                finish();
            }
        });

        purchaser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
