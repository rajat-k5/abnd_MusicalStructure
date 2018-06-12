package com.example.android.musicalstructure;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        // toggle = (Switch) findViewById(R.id.switch1);
//        toggle.setChecked(useDarkTheme);

//        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//           public void onCheckedChanged(CompoundButton view, boolean isChecked) {
//               toggleTheme(isChecked);
//           }});
        Button bt = findViewById(R.id.artist_btn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Info.class);
                intent.putExtra("Decider", 1);
                startActivity(intent);

            }
        });

        Button bt2 = findViewById(R.id.playlist_btn);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Info.class);
                intent.putExtra("Decider", 2);
                startActivity(intent);

            }
        });


        // Create an ArrayList of MusicList objects
        final ArrayList<MusicList> musicPlaylist = new ArrayList<MusicList>();
        musicPlaylist.add(new MusicList(getResources().getString(R.string.song1), getResources().getString(R.string.song1_Artist), getResources().getString(R.string.song1_Album), R.drawable.faded));
        musicPlaylist.add(new MusicList(getResources().getString(R.string.song2), getResources().getString(R.string.song2_Artist), getResources().getString(R.string.song2_Album), R.drawable.djturnitup));
        musicPlaylist.add(new MusicList(getResources().getString(R.string.song3), getResources().getString(R.string.song3_Artist), getResources().getString(R.string.song3_Album), R.drawable.ruleta));
        musicPlaylist.add(new MusicList(getResources().getString(R.string.song4), getResources().getString(R.string.song4_Artist), getResources().getString(R.string.song4_Album), R.drawable.buzz));
        musicPlaylist.add(new MusicList(getResources().getString(R.string.song5), getResources().getString(R.string.song5_Artist), getResources().getString(R.string.song5_Album), R.drawable.letmeloveyou));
        musicPlaylist.add(new MusicList(getResources().getString(R.string.song6), getResources().getString(R.string.song6_Artist), getResources().getString(R.string.song6_Album), R.drawable.despacito));
        musicPlaylist.add(new MusicList(getResources().getString(R.string.song7), getResources().getString(R.string.song7_Artist), getResources().getString(R.string.song7_Album), R.drawable.aatosahi));
        musicPlaylist.add(new MusicList(getResources().getString(R.string.song8), getResources().getString(R.string.song8_Artist), getResources().getString(R.string.song8_Album), R.drawable.cocacolatu));
        musicPlaylist.add(new MusicList(getResources().getString(R.string.song9), getResources().getString(R.string.song9_Artist), getResources().getString(R.string.song9_Album), R.drawable.trip));
        musicPlaylist.add(new MusicList(getResources().getString(R.string.song10), getResources().getString(R.string.song10_Artist), getResources().getString(R.string.song10_Album), R.drawable.friends));

        // Create an {@link MusicListAdapter}, whose data source is a list of
        // {@link MusicList}s. The adapter knows how to create list item views for each item
        // in the list.
        MusicListAdapter musicAdapter = new MusicListAdapter(this, musicPlaylist);

        // Get a reference to the ListView, and attach the adapter to the listView.
        final ListView listView = (ListView) findViewById(R.id.list_music);
        listView.setAdapter(musicAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                TextView tv = view.findViewById(R.id.song_name);
                String sn = tv.getText().toString();

                tv = view.findViewById(R.id.artist_name);
                String an = tv.getText().toString();

                ImageView iv = view.findViewById(R.id.list_item_icon);
                //int im = Integer.parseInt(iv.getTag().toString());
                String img = String.valueOf(iv.getTag());
                img = img.replaceAll("\\s", "");
                img = img.toLowerCase();

                //  Toast.makeText(MainActivity.this, img, Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(MainActivity.this, Nowplaying.class);
                myIntent.putExtra("songName", sn);
                myIntent.putExtra("artistName", an);
                myIntent.putExtra("songImage", img);
                startActivity(myIntent);
            }
        });
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
