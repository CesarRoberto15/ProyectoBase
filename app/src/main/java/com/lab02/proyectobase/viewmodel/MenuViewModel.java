package com.lab02.proyectobase.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SearchView;

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
import com.lab02.proyectobase.model.Albergues;
import com.lab02.proyectobase.model.Data.DbAlbergues;
import com.lab02.proyectobase.model.Data.DbVeterinarias;
import com.lab02.proyectobase.model.Veterinarias;
import com.lab02.proyectobase.ui.Adapters.AlberguesAdapter;
import com.lab02.proyectobase.ui.Adapters.VeterinariasAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuViewModel  extends ViewModel  implements BottomNavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener{
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final MutableLiveData<String> nombre = new MutableLiveData<>();
    private final MutableLiveData<String> distrito = new MutableLiveData<>();
    private final MutableLiveData<String> celular = new MutableLiveData<>();
    private final MutableLiveData<String> correo = new MutableLiveData<>();
    private final MutableLiveData<Integer> codigo = new MutableLiveData<>();
    private final MutableLiveData<Event<Integer>> evento = new MutableLiveData<>();
    private final MutableLiveData<Event<Integer>> error = new MutableLiveData<>();
    private final MutableLiveData<Event<Integer>> eventoDetalle = new MutableLiveData<>();
    private final MutableLiveData<String> filtro = new MutableLiveData<>();
    private Context context;
    private VeterinariasAdapter veterinariasAdapter;
    private AlberguesAdapter alberguesAdapter;
    private boolean estado = true;

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
    public void setContext(Context context){
        this.context= context;
    }
    public void gotoDetalle(int type,int id){

        codigo.setValue(id);
        //Log.d("Entre ","entreeeeee");
        if (type ==1 ){
            DbVeterinarias dbVeterinarias = new DbVeterinarias(context);
            Veterinarias vet= dbVeterinarias.verVeterinariaByID(id);
            nombre.setValue(vet.getNombre());
            distrito.setValue(vet.getDistrito());
            celular.setValue((vet.getCelular()));
            correo.setValue(vet.getCorreo());
        }else if(type ==2 ){
            DbAlbergues dbAlbergues = new DbAlbergues(context);
            Albergues albergues= dbAlbergues.verAlbergueByID(id);
            nombre.setValue(albergues.getNombre());
            distrito.setValue(albergues.getDistrito());
            celular.setValue((albergues.getCelular()));
            correo.setValue(albergues.getCorreo());
        }

        //Log.d("NOMBRW ",nombre.getValue());
        //Log.d("DETALLE","ME DISTE CLICK!!!!!!!!!!!!!!!!!!!!!!" + id+"type"+type);
        eventoDetalle.setValue(new Event<>(id));
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

        DbAlbergues dbAlbergues = new DbAlbergues(context);
        db.collection("albergues")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Albergues aux= dbAlbergues.verAlberguebyName(document.getString("nombre"));
                                if (aux==null){
                                    dbAlbergues.insertarAlbergue(document.getString("nombre"), document.getString("distrito"),
                                            document.getString("latitud") ,document.getString("longitud") ,document.getString("celular") ,document.getString("correo"));
                                }
                            }
                        }
                    }
                });


    }
    public void accionDetail (int id){
        // AÑADIR CONTACTO PERO NO TIENE NUMERO
        if ( celular.getValue().isEmpty() && id ==4){
            error.setValue(new Event<>(R.string.emptyPhone));
            return;
        }
        // ENVIAR CORREO PERO NO TIENE UN CORREO
        if ( correo.getValue().isEmpty() && id ==5){
            error.setValue(new Event<>(R.string.emptyEmail));
            return;
        }
        evento.setValue(new Event<>(id));
    }

    public void gotoDetalle(int type,int id){
        // completar
    }

    //VARIABLES OBSERVADAS
    public MutableLiveData<Event<Integer>> getEvento() {
        return evento;
    }

    public MutableLiveData<Event<Integer>> getEventoDetalle() {
        return eventoDetalle;
    }

    public MutableLiveData<Event<Integer>> getError() {
        return error;
    }

    public MutableLiveData<String> getFiltro() {
        return filtro;
    }

    public MutableLiveData<String> getNombre() {
        return nombre;
    }
    public boolean getEstado(){
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public MutableLiveData<String> getCorreo() {
        return correo;
    }

    public MutableLiveData<String> getDistrito() {
        return distrito;
    }

    public MutableLiveData<String> getCelular() {
        return celular;
    }

    public Context getContext() {
        return context;
    }

    public void setVeterinariasAdapter(VeterinariasAdapter veterinariasAdapter) {
        this.veterinariasAdapter = veterinariasAdapter;
    }

    public VeterinariasAdapter getVeterinariasAdapter() {
        return veterinariasAdapter;
    }

    public AlberguesAdapter getAlberguesAdapter() {
        return alberguesAdapter;
    }

    public void setAlberguesAdapter(AlberguesAdapter alberguesAdapter) {
        this.alberguesAdapter = alberguesAdapter;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filtro.setValue(s);
        return false;
    }
}
