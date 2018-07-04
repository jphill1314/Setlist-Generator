package com.appdev.jphil.practicesetlistgenerator.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AppDao {
    @Insert
    void insertSong(Song song);

    @Insert
    void insertInstrument(Instrument instrument);

    @Query("SELECT * FROM song")
    LiveData<List<Song>> loadAllSongs();

    @Query("SELECT * FROM instrument")
    LiveData<List<Instrument>> loadAllInstruments();
}
