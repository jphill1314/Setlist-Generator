package com.appdev.jphil.practicesetlistgenerator.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Instrument.class, parentColumns = "instrumentId", childColumns = "instrumentId"))
public class Song {

    @PrimaryKey(autoGenerate = true)
    public int songID;

    public String title;
    public String tuning;

    public int instrumentId;

    public Song(String title, String tuning, int instrumentId){
        this.title = title;
        this.tuning = tuning;
        this.instrumentId = instrumentId;
    }

    public Song(int id, String title, String tuning, int instrumentId){
        this.songID = id;
        this.title = title;
        this.tuning = tuning;
        this.instrumentId = instrumentId;
    }
}
