package com.merrix.libs;

import android.media.MediaMetadataRetriever;

import java.io.Serializable;

public class Music implements Serializable{

    private int id;
    private String musicName;
    private String musicArtist;
    private String musicLocation;
    private String musicDuration;
    private String albumName;
    private String albumArtist;
    private int albumID;
    private byte[] byteImage = null;



    public Music() {}
    public Music(int id, String musicName, String musicArtist, String musicLocation, String musicDuration, String album, int albumID){
        this.id = id;
        this.musicName = musicName;
        this.musicArtist = musicArtist;
        this.musicLocation = musicLocation;
        this.musicDuration = musicDuration;
        this.albumName = album;
        this.albumID = albumID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicArtist() {
        return musicArtist;
    }

    public void setMusicArtist(String musicArtist) {
        this.musicArtist = musicArtist;
    }

    public String getMusicLocation() {
        return musicLocation;
    }

    public void setMusicLocation(String musicLocation) {
        this.musicLocation = musicLocation;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public byte[] getAlbumArt() {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(musicLocation);
        byte[] art = null;
        try{
            art = retriever.getEmbeddedPicture();
        }catch (Exception e)
        {
            return null;
        }

        retriever.release();
        byteImage = art;
        return art;
    }

    public byte[] getByteImage() {
        return byteImage;
    }

    public String getMusicDuration() {
        return musicDuration;
    }

    public void setMusicDuration(String musicDuration) {
        this.musicDuration = musicDuration;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

}
