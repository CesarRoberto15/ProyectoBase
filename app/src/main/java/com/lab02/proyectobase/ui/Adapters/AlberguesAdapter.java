package com.lab02.proyectobase.ui.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lab02.proyectobase.R;
import com.lab02.proyectobase.databinding.ListItemAlbergueBinding;
import com.lab02.proyectobase.databinding.ListItemBinding;
import com.lab02.proyectobase.model.Albergues;
import com.lab02.proyectobase.model.Data.DbAlbergues;
import com.lab02.proyectobase.model.Data.DbVeterinarias;
import com.lab02.proyectobase.model.Veterinarias;
import com.lab02.proyectobase.viewmodel.MenuViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlberguesAdapter extends RecyclerView.Adapter<AlberguesAdapter.AlberguesViewHodler> {

    ArrayList<Albergues> listAlbergues;
    ArrayList<Albergues> listaOriginal;
    private final MenuViewModel mViewModel;
    public AlberguesAdapter(MenuViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setAlberguesList(ArrayList<Albergues> listAlbergues) {
        this.listAlbergues = listAlbergues;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listAlbergues);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AlberguesAdapter.AlberguesViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemAlbergueBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.list_item_albergue, parent, false);
        return new AlberguesAdapter.AlberguesViewHodler(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlberguesAdapter.AlberguesViewHodler holder, int position) {
        holder.binding.setViewModel(mViewModel);
        holder.binding.setAlbergue(listAlbergues.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return listAlbergues == null ? 0 : listAlbergues.size();
    }

    public void filtrado(final String txtBuscar) {
        final DbAlbergues dbAlbergues= new DbAlbergues(mViewModel.getContext());
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listAlbergues.clear();
            listAlbergues.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Albergues> collecion = listaOriginal.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())
                                || i.getCelular().toLowerCase().contains(txtBuscar.toLowerCase())
                                || i.getDistrito().toLowerCase().contains(txtBuscar.toLowerCase())
                                || i.getCorreo().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listAlbergues.clear();
                listAlbergues.addAll(collecion);
            } else {
                for (Albergues c : listaOriginal) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())
                            || c.getCelular().toLowerCase().contains(txtBuscar.toLowerCase())
                            || c.getDistrito().toLowerCase().contains(txtBuscar.toLowerCase())
                            || c.getCorreo().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listAlbergues.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class AlberguesViewHodler extends RecyclerView.ViewHolder {
        final ListItemAlbergueBinding binding;

        public AlberguesViewHodler(ListItemAlbergueBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
