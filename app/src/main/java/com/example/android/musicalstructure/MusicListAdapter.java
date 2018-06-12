package com.example.android.musicalstructure;

/**
 * Created by RAJAT on 09-06-2018.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*
* {@link MusicListAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link MusicList} objects.
* */
public class MusicListAdapter extends ArrayAdapter<MusicList> {

    private static final String LOG_TAG = MusicListAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context   The current context. Used to inflate the layout file.
     * @param musicList A List of MusicLIst objects to display in a list
     */
    public MusicListAdapter(Activity context, ArrayList<MusicList> musicList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, musicList);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link MusicList} object located at this position in the list
        MusicList currentMusic = getItem(position);

        // Find the TextView in the list_item.xml layout with the Song Name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.song_name);
        // Get the Song name from the current MusicList object and
        // set this text on the name TextView
        nameTextView.setText(currentMusic.getSongName());

        // Find the TextView in the list_item.xml layout with the Artist Name
        TextView artistTextView = (TextView) listItemView.findViewById(R.id.artist_name);
        // Get the Artist Name from the current MusicList object and
        // set this text on the number TextView
        artistTextView.setText(currentMusic.getArtistName());

        // Find the TextView in the list_item.xml layout with the Album Name
        TextView albumTextView = (TextView) listItemView.findViewById(R.id.album_name);
        // Get the Album Name from the current MusicList object and
        // set this text on the number TextView
        albumTextView.setText(currentMusic.getAlbumName());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        // Get the image resource ID from the current MusicList object and
        // set the image to iconView
        iconView.setImageResource(currentMusic.getImageResourceId());
        iconView.setTag(currentMusic.getSongName());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}