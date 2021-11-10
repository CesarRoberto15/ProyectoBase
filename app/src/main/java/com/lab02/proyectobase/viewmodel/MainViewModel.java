package com.lab02.proyectobase.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Patterns;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lab02.proyectobase.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lab02.proyectobase.Event;

import java.util.HashMap;
import java.util.Map;

public class MainViewModel  extends ViewModel {

    Context context;
    private final MutableLiveData<Event<Integer>> evento = new MutableLiveData<>();
    private final MutableLiveData<Event<Integer>> error = new MutableLiveData<>();
    private final MutableLiveData<Event<Integer>> databaseError = new MutableLiveData<>();
    private final MutableLiveData<String> correo = new MutableLiveData<>();
    private final MutableLiveData<String> password = new MutableLiveData<>();
    private final MutableLiveData<String> text = new MutableLiveData<>();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public void goToMenu() {
        evento.setValue(new Event<>(1));
    }

    public void addCliente() {
       if ( correo.getValue() == null || !Patterns.EMAIL_ADDRESS.matcher(correo.getValue()).matches()){
           error.setValue(new Event<>(R.string.correo_vacio));
           return;
       }
        if ( password.getValue() == null || password.getValue().length()<=4 ){
            error.setValue(new Event<>(R.string.password_vacio));
            return;
        }
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("correo", correo.getValue().toString());
        user.put("password", password.getValue().toString());


        // Add a new document with a generated ID
        db.collection("users")
                .document(correo.getValue().toString())
                .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                databaseError.setValue(new Event<>(R.string.registro_exitoso));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                databaseError.setValue(new Event<>(R.string.registro_error));
            }
        });
                /*.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        databaseError.setValue(new Event<>(R.string.registro_exitoso));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        databaseError.setValue(new Event<>(R.string.registro_error));
                    }
                });*/
        getDatos();
    }

    public void getDatos(){

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                text.setValue(text.getValue()+"\n"+document.getId() + " => " + document.getString("correo") +"\n");
                            }
                        }
                    }
                });
    }

    public void iniciarSesion (){
        if ( correo.getValue() == null || !Patterns.EMAIL_ADDRESS.matcher(correo.getValue()).matches()){
            error.setValue(new Event<>(R.string.correo_vacio));
            return;
        }
        if ( password.getValue() == null || password.getValue().length()<=4 ){
            error.setValue(new Event<>(R.string.password_vacio));
            return;
        }
        db.collection("users").document(correo.getValue().toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    if ( documentSnapshot.getString("correo").equals(correo.getValue().toString()) &&
                            documentSnapshot.getString("password").equals(password.getValue().toString())){
                        evento.setValue(new Event<>(1));
                    }
                }
            }
        });
    }
    //VARIABLES OBSERVADAS
    public MutableLiveData<Event<Integer>> getEvento() {
        return evento;
    }
    public MutableLiveData<Event<Integer>> getError() {
        return error;
    }
    public MutableLiveData<Event<Integer>> getDatabaseError() {
        return databaseError;
    }
    public MutableLiveData<String> getCorreo() {
        return correo;
    }
    public MutableLiveData<String> getPassword() { return password; }
    public MutableLiveData<String> getText() {
        return text;
    }

}
