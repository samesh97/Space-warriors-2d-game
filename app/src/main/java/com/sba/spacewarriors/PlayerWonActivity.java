package com.sba.spacewarriors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PlayerWonActivity extends AppCompatActivity {

    ImageView playAgain;
    String gameMode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_won);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Intent intent = getIntent();
        gameMode = intent.getStringExtra("GameMode");

        playAgain = (ImageView) findViewById(R.id.playAgain);

        playAgain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(gameMode.equals("Easy"))
                {
                    Intent intent = new Intent(getApplicationContext(), MainGameActivity.class);
                    intent.putExtra("GameMode","Easy");
                    startActivity(intent);
                    finish();
                }
                if(gameMode.equals("Medium"))
                {
                    Intent intent = new Intent(getApplicationContext(), MainGameActivity.class);
                    intent.putExtra("GameMode","Medium");
                    startActivity(intent);
                    finish();
                }
                if(gameMode.equals("Hard"))
                {
                    Intent intent = new Intent(getApplicationContext(), MainGameActivity.class);
                    intent.putExtra("GameMode","Hard");
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
