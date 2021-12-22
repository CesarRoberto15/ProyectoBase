package com.lab02.proyectobase.ui.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lab02.proyectobase.databinding.ListItemBinding;
import com.lab02.proyectobase.model.Data.DbVeterinarias;
import com.lab02.proyectobase.model.Veterinarias;
import com.lab02.proyectobase.viewmodel.MenuViewModel;
import com.lab02.proyectobase.R;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VeterinariasAdapter extends RecyclerView.Adapter<VeterinariasAdapter.VeterinariasViewHolder>  {

    ArrayList<Veterinarias> listVeterinarias;
    ArrayList<Veterinarias> listaOriginal;
    private final MenuViewModel mViewModel;
    public VeterinariasAdapter(MenuViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setVeterinariasList(ArrayList<Veterinarias> listVeterinarias) {
        this.listVeterinarias = listVeterinarias;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listVeterinarias);
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

    public void filtrado(final String txtBuscar) {

        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listVeterinarias.clear();
            listVeterinarias.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Veterinarias> collecion = listaOriginal.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())
                                || i.getCelular().toLowerCase().contains(txtBuscar.toLowerCase())
                                || i.getDistrito().toLowerCase().contains(txtBuscar.toLowerCase())
                                || i.getCorreo().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listVeterinarias.clear();
                listVeterinarias.addAll(collecion);
            } else {
                for (Veterinarias c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())
                            || c.getCelular().toLowerCase().contains(txtBuscar.toLowerCase())
                            || c.getDistrito().toLowerCase().contains(txtBuscar.toLowerCase())
                            || c.getCorreo().toLowerCase().contains(txtBuscar.toLowerCase())
                            ){
                        listVeterinarias.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class VeterinariasViewHolder extends RecyclerView.ViewHolder {
        final ListItemBinding binding;

        public VeterinariasViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
