package com.lab02.proyectobase.viewmodel;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lab02.proyectobase.Event;
import com.lab02.proyectobase.R;

public class MenuViewModel  extends ViewModel  implements BottomNavigationView.OnNavigationItemSelectedListener{
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.firstFragment:
                evento.setValue(new Event<>(1));
                return true;
            case R.id.secondFragment:
                evento.setValue(new Event<>(2));
                return true;
            case R.id.thirdFragment:
                evento.setValue(new Event<>(3));
                return true;
        }
        return false;
    }
    private final MutableLiveData<Event<Integer>> evento = new MutableLiveData<>();



    //VARIABLES OBSERVADAS
    public MutableLiveData<Event<Integer>> getEvento() {
        return evento;
    }



}
