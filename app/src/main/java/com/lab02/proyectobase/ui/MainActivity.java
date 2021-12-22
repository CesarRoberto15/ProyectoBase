package com.lab02.proyectobase.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActionBar;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.lab02.proyectobase.R;
import com.lab02.proyectobase.databinding.ActivityMainBinding;
import com.lab02.proyectobase.model.Data.DbHelper;
import com.lab02.proyectobase.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    Button btn_invitado;
    private ActivityMainBinding mBinding;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);
        setupNavigation();
        setupDataBase();
        mViewModel.actualizarTextBase(this);

        // fullscreen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }
    private void setupDataBase() {
        DbHelper dbHelper = new DbHelper (this);
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        if ( db!= null){
            Toast.makeText(this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "BASE DE DATOS NO CREADA", Toast.LENGTH_LONG).show();
        }

    }
    //METODO PARA GESTIONAR LOS EVENTOS QUE SE VAN DANDO
    private void setupNavigation() {
        // Observamos la variable
        mViewModel.getEvento().observe(this, integerEvent -> {
            Integer id = integerEvent.getContentIfNotHandled();
            if (id == 1) {
                goToMenu();
            }
        });
        mViewModel.getError().observe(this, event -> {
            // Mostrar error en el nombre de la categoría
            Integer msg = event.getContentIfNotHandled();
            if (msg != null) {
                switch (msg)
                {
                    case R.string.correo_vacio:  mBinding.edtEmail.setError(getText(msg));
                        break;
                    case R.string.password_vacio:  mBinding.edtPassword.setError(getText(msg));
                        break;
                }
            }
        });
        mViewModel.getDatabaseError().observe(this, event -> {
            // Mostrar error en el nombre de la categoría
            Integer msg = event.getContentIfNotHandled();
            if (msg != null) {
                switch (msg)
                {
                    case R.string.registro_exitoso:
                        Toast.makeText(MainActivity.this, R.string.registro_exitoso,Toast.LENGTH_LONG).show();
                        break;
                    case R.string.registro_error:
                        Toast.makeText(MainActivity.this, R.string.registro_error,Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

    }

    private void goToMenu(){
        Intent l=new Intent(this, Second_Activity.class);
        startActivity(l);
    }

}