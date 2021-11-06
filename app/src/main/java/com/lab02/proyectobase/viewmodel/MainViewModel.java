package com.lab02.proyectobase.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.lab02.proyectobase.Event;

public class MainViewModel  extends ViewModel {

    private final MutableLiveData<Event<Integer>> evento = new MutableLiveData<>();


    public void goToMenu() {
        evento.setValue(new Event<>(1));
    }

    //VARIABLES OBSERVADAS
    public MutableLiveData<Event<Integer>> getEvento() {
        return evento;
    }

}
