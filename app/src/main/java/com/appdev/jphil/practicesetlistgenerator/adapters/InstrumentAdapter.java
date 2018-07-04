package com.appdev.jphil.practicesetlistgenerator.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appdev.jphil.practicesetlistgenerator.R;
import com.appdev.jphil.practicesetlistgenerator.database.Instrument;

import java.util.List;

public class InstrumentAdapter extends RecyclerView.Adapter<InstrumentAdapter.InstrumentViewHolder>{

    class InstrumentViewHolder extends RecyclerView.ViewHolder{
        private final TextView name;

        private InstrumentViewHolder(View view){
            super(view);

            name = view.findViewById(R.id.name);
        }
    }

    private List<Instrument> instruments;

    public void setInstruments(List<Instrument> instruments){
        this.instruments = instruments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InstrumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_instrument, parent, false);
        return new InstrumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstrumentViewHolder holder, int position) {
        if(instruments != null){
            holder.name.setText(instruments.get(position).name);
        }
    }

    @Override
    public int getItemCount() {
        return (instruments != null) ? instruments.size() : 0;
    }
}
