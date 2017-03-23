package com.example.a2edwap71.webcommunication;

/**
 * Created by 2edwap71 on 23/03/2017.
 */
public class Song {
    private String title;
    private String artist;
    private int year;

    public Song (String title,String artist,int year){

        this.title = title;
        this.artist= artist;
        this.year=year;


    }
    public String getTitle(){
        return title;
    }
    public String getArtist(){
        return artist;
    }
    public int getYear(){
        return year;
    }

}
