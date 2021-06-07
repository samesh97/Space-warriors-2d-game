package com.sba.spacewarriors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


    }
    public void SelectPlayMode(View view)
    {
        if(view.getId() == R.id.easy)
        {
            Intent intent = new Intent(getApplicationContext(), MainGameActivity.class);
            intent.putExtra("GameMode","Easy");
            startActivity(intent);
        }
        if(view.getId() == R.id.medium)
        {
            Intent intent = new Intent(getApplicationContext(), MainGameActivity.class);
            intent.putExtra("GameMode","Medium");
            startActivity(intent);
        }
        if(view.getId() == R.id.hard)
        {
            Intent intent = new Intent(getApplicationContext(), MainGameActivity.class);
            intent.putExtra("GameMode","Hard");
            startActivity(intent);
        }
    }
}
