package com.appdev.jphil.practicesetlistgenerator.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.appdev.jphil.practicesetlistgenerator.database.Instrument;
import com.appdev.jphil.practicesetlistgenerator.database.Repository;
import com.appdev.jphil.practicesetlistgenerator.database.Song;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Song>> songs;
    private LiveData<List<Instrument>> instruments;

    public ViewModel(Application application){
        super(application);
        repository = new Repository(application);
        songs = repository.getSongs();
        instruments = repository.getInstruments();
    }

    public LiveData<List<Song>> getSongs() {
        return songs;
    }

    public LiveData<List<Instrument>> getInstruments() {
        return instruments;
    }

    public void insertSong(Song song){
        repository.insertSong(song);
    }

    public void insertInstrument(Instrument instrument){
        repository.insertInstrument(instrument);
    }
}
