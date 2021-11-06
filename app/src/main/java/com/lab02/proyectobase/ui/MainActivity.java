package com.lab02.proyectobase.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.lab02.proyectobase.R;
import com.lab02.proyectobase.databinding.ActivityMainBinding;
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

        /*btn_invitado=(Button) findViewById(R.id.btn_invited);

        btn_invitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l=new Intent(MainActivity.this, Second_Activity.class);
                startActivity(l);
            }
        });*/

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
    }

    private void goToMenu(){
        Intent l=new Intent(this, Second_Activity.class);
        startActivity(l);
    }

}