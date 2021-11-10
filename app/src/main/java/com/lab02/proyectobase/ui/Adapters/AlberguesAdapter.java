package com.lab02.proyectobase.ui.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlberguesAdapter extends RecyclerView.Adapter<AlberguesAdapter.AlberguesViewHodler> {
    @NonNull
    @Override
    public AlberguesAdapter.AlberguesViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AlberguesAdapter.AlberguesViewHodler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AlberguesViewHodler extends RecyclerView.ViewHolder {
        public AlberguesViewHodler(@NonNull View itemView) {
            super(itemView);
        }
    }
}
