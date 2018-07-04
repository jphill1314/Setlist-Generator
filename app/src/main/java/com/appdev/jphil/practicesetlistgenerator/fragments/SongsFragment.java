package com.appdev.jphil.practicesetlistgenerator.fragments;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.appdev.jphil.practicesetlistgenerator.R;
import com.appdev.jphil.practicesetlistgenerator.adapters.SongAdapter;
import com.appdev.jphil.practicesetlistgenerator.database.Instrument;
import com.appdev.jphil.practicesetlistgenerator.database.Song;
import com.appdev.jphil.practicesetlistgenerator.viewmodels.ViewModel;

import java.util.List;

public class SongsFragment extends Fragment {

    public SongsFragment() {
        // Required empty public constructor
    }

    private ViewModel viewModel;
    private FloatingActionButton fab;

    private String[] instruments;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_songs, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        final SongAdapter adapter = new SongAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getSongs().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(@Nullable List<Song> songs) {
                adapter.setSongs(songs);
            }
        });

        viewModel.getInstruments().observe(this, new Observer<List<Instrument>>() {
            @Override
            public void onChanged(@Nullable List<Instrument> instruments) {
                adapter.setInstruments(instruments);
                getInstrumentsAsArray(instruments);
            }
        });

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getString(R.string.add_song));

                View dialogView = inflater.inflate(R.layout.dialog_song, null);
                final Spinner instrumentSpinner = dialogView.findViewById(R.id.instrument_spinner);
                final ArrayAdapter<String> instAdapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, instruments);
                instrumentSpinner.setAdapter(instAdapter);

                final Spinner tuningSpinner = dialogView.findViewById(R.id.tuning);
                ArrayAdapter<CharSequence> tuneAdapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.tunings, android.R.layout.simple_spinner_item);
                tuningSpinner.setAdapter(tuneAdapter);

                final EditText titleEdit = dialogView.findViewById(R.id.title);

                builder.setView(dialogView);

                builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = titleEdit.getText().toString();
                        String tuning = tuningSpinner.getSelectedItem().toString();
                        int instrument = instrumentSpinner.getSelectedItemPosition() + 1; // ids start at 1 not 0!

                        if(title.length() > 0){
                            Log.d("Test", "Instrument: " + instrument);
                            AddSong(title, instrument, tuning);
                        }
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });

        return view;
    }

    private void AddSong(String title, int instrument, String tuning){
        viewModel.insertSong(new Song(title, tuning, instrument));
    }

    private void getInstrumentsAsArray(List<Instrument> instruments){
        if(instruments == null || instruments.size() == 0){
            this.instruments = new String[] {"No instruments"};
        }
        else{
            this.instruments = new String[instruments.size()];
            for(int x = 0; x < this.instruments.length; x++){
                Log.d("Test", "Name: " + instruments.get(x).name + " id: " + instruments.get(x).instrumentId);
                this.instruments[x] = instruments.get(x).name;
            }
        }
    }
}
