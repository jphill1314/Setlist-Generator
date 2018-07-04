package com.appdev.jphil.practicesetlistgenerator.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appdev.jphil.practicesetlistgenerator.R;
import com.appdev.jphil.practicesetlistgenerator.database.Instrument;
import com.appdev.jphil.practicesetlistgenerator.database.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    class SongViewHolder extends RecyclerView.ViewHolder{
        private final TextView songTitle, instrument, tuning;

        private SongViewHolder(View view){
            super(view);

            songTitle = view.findViewById(R.id.title);
            instrument = view.findViewById(R.id.instrument);
            tuning = view.findViewById(R.id.tuning);
        }
    }

    private final LayoutInflater inflater;
    private List<Song> songs;
    private List<Instrument> instruments;

    public SongAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.list_item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int pos){
        if(songs != null && instruments != null){
            holder.songTitle.setText(songs.get(pos).title);
            holder.tuning.setText(songs.get(pos).tuning);
            holder.instrument.setText(instruments.get(songs.get(pos).instrumentId - 1).name); // ids start at 1 not 0!
        }
    }

    public void setSongs(List<Song> songs){
        this.songs = songs;
        notifyDataSetChanged();
    }

    public void setInstruments(List<Instrument> instruments){
        this.instruments = instruments;
    }

    @Override
    public int getItemCount(){
        if(songs != null){
            return songs.size();
        }
        else{
            return 0;
        }
    }
}
