package com.example.android.musicalstructure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Info extends AppCompatActivity {
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
        setContentView(R.layout.info);

        int dec = getIntent().getIntExtra("Decider", 0);

        // Create an ArrayList of MusicList objects
        // ArtistList
        final ArrayList<MusicList> artistList = new ArrayList<MusicList>();
        artistList.add(new MusicList(getResources().getString(R.string.artist1), "", "", R.drawable.alanwalker));
        artistList.add(new MusicList(getResources().getString(R.string.artist2), "", "", R.drawable.aasthagill));
        artistList.add(new MusicList(getResources().getString(R.string.artist3), "", "", R.drawable.annemarie));
        artistList.add(new MusicList(getResources().getString(R.string.artist4), "", "", R.drawable.badal));
        artistList.add(new MusicList(getResources().getString(R.string.artist5), "", "", R.drawable.baadshah));
        artistList.add(new MusicList(getResources().getString(R.string.artist6), "", "", R.drawable.daddyyankee));
        artistList.add(new MusicList(getResources().getString(R.string.artist7), "", "", R.drawable.inna));
        artistList.add(new MusicList(getResources().getString(R.string.artist8), "", "", R.drawable.justinbieber));
        artistList.add(new MusicList(getResources().getString(R.string.artist9), "", "", R.drawable.luisfonsi));
        artistList.add(new MusicList(getResources().getString(R.string.artist10), "", "", R.drawable.marshmello));
        artistList.add(new MusicList(getResources().getString(R.string.artist11), "", "", R.drawable.meetbros));
        artistList.add(new MusicList(getResources().getString(R.string.artist12), "", "", R.drawable.nehakakkar));
        artistList.add(new MusicList(getResources().getString(R.string.artist13), "", "", R.drawable.tonykakkar));
        artistList.add(new MusicList(getResources().getString(R.string.artist14), "", "", R.drawable.yellowclaw));
        artistList.add(new MusicList(getResources().getString(R.string.artist15), "", "", R.drawable.youngdesi));

        MusicListAdapter artistAdapter = new MusicListAdapter(this, artistList);


        // PlayList
        final ArrayList<MusicList> playList = new ArrayList<MusicList>();
        playList.add(new MusicList("Party", "", "", R.drawable.party));
        playList.add(new MusicList("Dance", "", "", R.drawable.dance));
        playList.add(new MusicList("Romantic", "", "", R.drawable.romance));
        playList.add(new MusicList("Travelling", "", "", R.drawable.travel));

        MusicListAdapter playListAdapter = new MusicListAdapter(this, playList);

        // Get a reference to the ListView, and attach the adapter to the listView.
        final ListView lv = (ListView) findViewById(R.id.list_info);
        if (dec == 1)
            lv.setAdapter(artistAdapter);
        else if (dec == 2)
            lv.setAdapter(playListAdapter);
        else {
            Toast.makeText(this, "Not Worked", Toast.LENGTH_SHORT).show();
            lv.setAdapter(artistAdapter);
        }
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