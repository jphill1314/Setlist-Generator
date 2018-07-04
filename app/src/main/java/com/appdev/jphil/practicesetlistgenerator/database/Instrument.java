package com.appdev.jphil.practicesetlistgenerator.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Instrument {

    @PrimaryKey(autoGenerate = true)
    public int instrumentId;

    public String name;

    public Instrument(String name){
        this.name = name;
    }

    public Instrument(int id, String name){
        this.instrumentId = id;
        this.name = name;
    }
}
