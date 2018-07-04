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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.appdev.jphil.practicesetlistgenerator.R;
import com.appdev.jphil.practicesetlistgenerator.adapters.InstrumentAdapter;
import com.appdev.jphil.practicesetlistgenerator.database.Instrument;
import com.appdev.jphil.practicesetlistgenerator.viewmodels.ViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstrumentsFragment extends Fragment {


    public InstrumentsFragment() {
        // Required empty public constructor
    }

    private ViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_instruments, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        final InstrumentAdapter adapter = new InstrumentAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getInstruments().observe(this, new Observer<List<Instrument>>() {
            @Override
            public void onChanged(@Nullable List<Instrument> instruments) {
                adapter.setInstruments(instruments);
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getString(R.string.add_instrument));
                builder.setView(R.layout.dialog_instrument);

                builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editText = ((AlertDialog)dialogInterface).findViewById(R.id.name);

                        String name = editText.getText().toString();
                        if(name.length() > 0){
                            viewModel.insertInstrument(new Instrument(name));
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

}
