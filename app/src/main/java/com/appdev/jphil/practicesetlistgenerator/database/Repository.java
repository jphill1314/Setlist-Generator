package com.appdev.jphil.practicesetlistgenerator.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class Repository {

    private AppDao appDao;
    private LiveData<List<Song>> songs;
    private LiveData<List<Instrument>> instruments;

    public Repository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        appDao = db.appDao();
        songs = appDao.loadAllSongs();
        instruments = appDao.loadAllInstruments();
    }

    public LiveData<List<Song>> getSongs() {
        return songs;
    }

    public LiveData<List<Instrument>> getInstruments() {
        return instruments;
    }

    public void insertSong(Song song){
        new insertSongAsync(appDao).execute(song);
    }

    public void insertInstrument(Instrument instrument){
        new insertInstrumentAsync(appDao).execute(instrument);
    }

    private static class insertSongAsync extends AsyncTask<Song, Void, Void>{
        private AppDao asyncAppDao;

        insertSongAsync(AppDao dao){
            asyncAppDao = dao;
        }

        @Override
        protected Void doInBackground(final Song... params){
            asyncAppDao.insertSong(params[0]);
            return null;
        }
    }

    private static class insertInstrumentAsync extends AsyncTask<Instrument, Void, Void>{
        private AppDao asyncAppDao;

        insertInstrumentAsync(AppDao appDao){
            asyncAppDao = appDao;
        }

        @Override
        protected Void doInBackground(final Instrument... params){
            asyncAppDao.insertInstrument(params[0]);
            return null;
        }
    }
}
