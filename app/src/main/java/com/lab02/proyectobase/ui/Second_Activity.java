package com.lab02.proyectobase.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SearchView;
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
    vistaDetalleFragment vistaDetalleFragment = new vistaDetalleFragment();

    private ActivitySecondBinding mBinding;
    private MenuViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_second);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        mViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);
        mViewModel.setContext(this);


        //BottomNavigationView navigation=findViewById(R.id.botton_navigation);
        //navigation.setOnNavigationItemSelectedListener(mOrNavigationItemSelectedListener);
        mViewModel.addBase(this);

        firstFragment.setViewModel(mViewModel);
        secondtFragment.setViewModel(mViewModel);
        vistaDetalleFragment.setViewModel(mViewModel);
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

    //METODO PARA GESTIONAR LOS EVENTOS QUE SE VAN DANDO
    private void setupNavigation() {
        // Observamos la variable
        mViewModel.getEvento().observe(this, integerEvent -> {
            Integer id = integerEvent.getContentIfNotHandled();
            if (id!= null){
                if (id == 1) {
                    mViewModel.setEstado(true);
                    loadFragment(firstFragment);
                }
                if (id == 2) {
                    mViewModel.setEstado(false);
                    loadFragment(secondtFragment);
                }
                if (id == 3) {
                    mViewModel.setEstado(true);
                    loadFragment(thirdFragment);
                }
                if (id == 4){
                    addContact();
                }
                if (id == 5){
                    sendEmail();
                }
            }

        });
        mViewModel.getEventoDetalle().observe(this, integerEvent -> {

            Integer id = integerEvent.getContentIfNotHandled();

            if (id != null){

                loadFragment(vistaDetalleFragment);
            }

        });
        mViewModel.getError().observe(this, integerEvent -> {

            Integer id = integerEvent.getContentIfNotHandled();

            if (id != null){
                switch (id)
                {
                    case R.string.emptyEmail:
                        Toast.makeText(Second_Activity.this,getText(R.string.emptyEmail), Toast.LENGTH_LONG).show();
                        break;
                    case R.string.emptyPhone:
                        Toast.makeText(Second_Activity.this,getText(R.string.emptyPhone), Toast.LENGTH_LONG).show();
                        break;
                }
            }

        });
        mViewModel.getFiltro().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (mViewModel.getEstado()){
                    mViewModel.getVeterinariasAdapter().filtrado(s);
                }else{
                    mViewModel.getAlberguesAdapter().filtrado(s);
                }


            }
        });


    }
    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.commit();
    }
    public void addContact(){
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        // Sets the MIME type to match the Contacts Provider
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME,mViewModel.getNombre().getValue())

                // Inserts a phone number
                .putExtra(ContactsContract.Intents.Insert.PHONE, mViewModel.getCelular().getValue())
                /*
                 * In this example, sets the phone type to be a work phone.
                 * You can set other phone types as necessary.
                 */
                .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE,
                        ContactsContract.CommonDataKinds.Phone.TYPE_WORK);
        startActivity(intent);
    }
    public void sendEmail(){
        Intent correoIntent = new Intent(Intent.ACTION_SEND);
        correoIntent.setType("message/rfc22");
        correoIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mViewModel.getCorreo().getValue()});
        correoIntent.putExtra(Intent.EXTRA_SUBJECT, "Correo de prueba");
        correoIntent.putExtra(Intent.EXTRA_TEXT, "Aqui esta el mensaje");
        startActivity(Intent.createChooser(correoIntent, "Enviar Email"));
    }
}