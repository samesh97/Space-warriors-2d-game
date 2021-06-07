package com.sba.spacewarriors;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainGameActivity extends AppCompatActivity {

    EasyGameView easyGameView;
    MediumGameView mediumGameView;
    HardGameView hardGameView;

    private Handler handler = new Handler();
    private static final long Interval = 30;
    int backPressedTimes = 0;
    String gameMode = null;

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        Intent intent = getIntent();
        gameMode = intent.getStringExtra("GameMode");



        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        if(gameMode.equals("Easy"))
        {
            easyGameView = new EasyGameView(MainGameActivity.this);
            setContentView(easyGameView);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            easyGameView.invalidate();
                        }
                    });
                }
            },0,Interval);
        }
        if(gameMode.equals("Medium"))
        {
            mediumGameView = new MediumGameView(MainGameActivity.this);
            setContentView(mediumGameView);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            mediumGameView.invalidate();
                        }
                    });
                }
            },0,Interval);
        }
        if(gameMode.equals("Hard"))
        {
            hardGameView = new HardGameView(MainGameActivity.this);
            setContentView(hardGameView);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            hardGameView.invalidate();
                        }
                    });
                }
            },0,Interval);
        }


    }
}
