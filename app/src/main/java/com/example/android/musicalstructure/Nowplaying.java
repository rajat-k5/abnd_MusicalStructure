package com.example.android.musicalstructure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


public class Nowplaying extends AppCompatActivity {

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    Switch toggle;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        toggle = (Switch) menu.findItem(R.id.myswitch).getActionView().findViewById(R.id.switchForActionBar);

        toggle.setChecked(useDarkTheme);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                toggleTheme(isChecked);
            }
        });

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // Use the chosen theme
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if (useDarkTheme) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.now_playing);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        String resSong = extras.getString("songName");
        String resArtist = extras.getString("artistName");
        String resImage = extras.getString("songImage");


        TextView tv = findViewById(R.id.tv_song);
        tv.setText(resSong);

        TextView tv2 = findViewById(R.id.tv_artist);
        tv2.setText(resArtist);


        ImageView iv = findViewById(R.id.now_pic);
        Context context = iv.getContext();
        int id = context.getResources().getIdentifier(resImage, "drawable", context.getPackageName());
        iv.setImageResource(id);

        // setResult(0,new Intent());
        //finish();
    }

    private void toggleTheme(boolean darkTheme) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_DARK_THEME, darkTheme);
        editor.apply();

        Intent intent = getIntent();
        finish();

        startActivity(intent);
    }


}
