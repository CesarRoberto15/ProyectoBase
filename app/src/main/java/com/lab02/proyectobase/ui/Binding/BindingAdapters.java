package com.lab02.proyectobase.ui.Binding;

import android.view.View;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lab02.proyectobase.model.Veterinarias;
import com.lab02.proyectobase.ui.Adapters.VeterinariasAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BindingAdapters {

    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelected(
            BottomNavigationView view, BottomNavigationView.OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    @BindingAdapter("selectedItemPosition")
    public static void setSelectedItemPosition(
            BottomNavigationView view, int position) {
        view.setSelectedItemId(position);
    }
   /* @BindingAdapter("items")
    public static void setItems(RecyclerView list,ArrayList<Veterinarias> items) {
        VeterinariasAdapter adapter = (VeterinariasAdapter) list.getAdapter();

        if (items != null) {
            adapter.setVeterinariasList(items);

        }
    }*/
}
