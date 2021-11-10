package com.lab02.proyectobase.ui.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lab02.proyectobase.databinding.ListItemBinding;
import com.lab02.proyectobase.model.Veterinarias;
import com.lab02.proyectobase.viewmodel.MenuViewModel;
import com.lab02.proyectobase.R;
import java.util.ArrayList;
import java.util.List;

public class VeterinariasAdapter extends RecyclerView.Adapter<VeterinariasAdapter.VeterinariasViewHolder> {

    ArrayList<Veterinarias> listVeterinarias;
    private final MenuViewModel mViewModel;
    public VeterinariasAdapter(MenuViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setVeterinariasList(ArrayList<Veterinarias> listVeterinarias) {
        this.listVeterinarias = listVeterinarias;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public VeterinariasAdapter.VeterinariasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.list_item, parent, false);
        return new VeterinariasViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VeterinariasAdapter.VeterinariasViewHolder holder, int position) {
        holder.binding.setViewModel(mViewModel);
        holder.binding.setVeterinaria(listVeterinarias.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return listVeterinarias == null ? 0 : listVeterinarias.size();
    }

    public class VeterinariasViewHolder extends RecyclerView.ViewHolder {
        final ListItemBinding binding;

        public VeterinariasViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
