package com.programmer.seratic.androidtest;

public class Song extends MusicLibrary
{
    private String title;
    private String artist;
    private String year;
    private int tagImage;

    public Song() {
    }

    public Song(String title, String artist, String year, int tagImage) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.tagImage = tagImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getTagImage() {
        return tagImage;
    }

    public void setTagImage(int tagImage) {
        this.tagImage = tagImage;
    }

    @Override
    public int getType() {
        return TYPE_SONG;
    }
}
