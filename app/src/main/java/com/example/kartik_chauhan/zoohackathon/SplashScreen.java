package com.example.kartik_chauhan.zoohackathon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kartik_chauhan.zoohackathon.Authentication.LoginActivity;
import com.example.kartik_chauhan.zoohackathon.Authentication.PursellerActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreen extends AppCompatActivity {


    private final int SPLASH_DISPLAY_LENGTH = 5000;
    private TextView tv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);


        tv = (TextView)findViewById(R.id.slogan);
        iv = (ImageView)findViewById(R.id.logo);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.transitions);
        tv.startAnimation(anim);
        iv.startAnimation(anim);
        final Intent i = new Intent(this, PursellerActivity.class);
        Thread t = new Thread(){
            public void run()
            {
                try {
                    sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        t.start();

    }

}
