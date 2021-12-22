package com.lab02.proyectobase.viewmodel;

import android.content.Context;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lab02.proyectobase.Event;
import com.lab02.proyectobase.R;
import com.lab02.proyectobase.model.Data.DbVeterinarias;
import com.lab02.proyectobase.model.Veterinarias;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuViewModel  extends ViewModel  implements BottomNavigationView.OnNavigationItemSelectedListener{
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
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

    public void addBase(Context context){
        DbVeterinarias dbVeterinarias = new DbVeterinarias(context);
        db.collection("veterinarias")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Veterinarias aux= dbVeterinarias.verVeterinaria(document.getString("correo"));
                                if (aux==null){
                                    dbVeterinarias.insertarVeterinaria(document.getString("nombre"), document.getString("distrito"),
                                            document.getString("latitud") ,document.getString("longitud") ,document.getString("celular") ,document.getString("correo"));
                                }
                            }
                        }
                    }
                });
    }
    private final MutableLiveData<Event<Integer>> evento = new MutableLiveData<>();

    public void gotoDetalle(int type,int id){
        // completar
    }

    //VARIABLES OBSERVADAS
    public MutableLiveData<Event<Integer>> getEvento() {
        return evento;
    }


}
