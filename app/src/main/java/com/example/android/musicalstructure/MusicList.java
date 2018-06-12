package com.example.android.musicalstructure;

/**
 * Created by RAJAT on 09-06-2018.
 */

public class MusicList {

    // Name of the Song
    private String mSongName;

    // Artist
    private String mArtistName;

    // Album
    private String mAlbumName;

    // Drawable resource ID
    private int mImageResourceId;

    /*
    * Create a new MusicList object.
    *
    * @param vName is the name of the Android version (e.g. Gingerbread)
    * @param vNumber is the corresponding Android version number (e.g. 2.3-2.7)
    * @param image is drawable reference ID that corresponds to the Android version
    * */
    public MusicList(String vName, String vArtist, String vAlbum, int imageResourceId)
    {
        mSongName = vName;
        mArtistName = vArtist;
        mAlbumName = vAlbum;
        mImageResourceId = imageResourceId;
    }

    /**
     * Get the name of the Song
     */
    public String getSongName() {
        return mSongName;
    }

    /**
     * Get the Album Name
     */


    public String getAlbumName() {
        return mAlbumName;
    }
    /**
     * Get the Artist Name
     */
    public String getArtistName() {
        return mArtistName;
    }

    /**
     * Get the image resource ID
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }


}

