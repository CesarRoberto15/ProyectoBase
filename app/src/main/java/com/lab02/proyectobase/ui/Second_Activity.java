package com.lab02.proyectobase.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.lab02.proyectobase.databinding.ActivitySecondBinding;
import com.lab02.proyectobase.model.Veterinarias;
import com.lab02.proyectobase.ui.Adapters.VeterinariasAdapter;
import com.lab02.proyectobase.viewmodel.MenuViewModel;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lab02.proyectobase.R;

public class Second_Activity extends AppCompatActivity {

    FirstFragment firstFragment=new FirstFragment();
    SecondFragment secondtFragment=new SecondFragment();
    ThirdFragment thirdFragment=new ThirdFragment();

    private ActivitySecondBinding mBinding;
    private MenuViewModel mViewModel;
    private VeterinariasAdapter veterinariasAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_second);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        mViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);

        //BottomNavigationView navigation=findViewById(R.id.botton_navigation);
        //navigation.setOnNavigationItemSelectedListener(mOrNavigationItemSelectedListener);
        mViewModel.addBase(this);
        setupNavigation();
       // setupListAdapter();
        loadFragment(firstFragment);
    }

    /*private final BottomNavigationView.OnNavigationItemSelectedListener mOrNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    loadFragment(firstFragment);
                    return true;
                case R.id.secondFragment:
                    loadFragment(secondtFragment);
                    return true;
                case R.id.thirdFragment:
                    loadFragment(thirdFragment);
                    return true;
            }
            return false;
        }
    };*/
    private void setupListAdapter() {
        veterinariasAdapter = new VeterinariasAdapter(mViewModel);

    }
    //METODO PARA GESTIONAR LOS EVENTOS QUE SE VAN DANDO
    private void setupNavigation() {
        // Observamos la variable
        mViewModel.getEvento().observe(this, integerEvent -> {
            Integer id = integerEvent.getContentIfNotHandled();
            if (id!= null){
                if (id == 1) {
                    loadFragment(firstFragment);
                }
                if (id == 2) {
                    loadFragment(secondtFragment);
                }
                if (id == 3) {
                    loadFragment(thirdFragment);
                }
            }

        });
    }
    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.commit();


    }
}