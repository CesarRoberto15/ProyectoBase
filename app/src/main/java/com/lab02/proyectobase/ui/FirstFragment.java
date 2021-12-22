package com.lab02.proyectobase.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lab02.proyectobase.R;
import com.lab02.proyectobase.databinding.FragmentFirstBinding;
import com.lab02.proyectobase.model.Data.DbVeterinarias;
import com.lab02.proyectobase.model.Veterinarias;
import com.lab02.proyectobase.ui.Adapters.VeterinariasAdapter;
import com.lab02.proyectobase.viewmodel.MenuViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView rv;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentFirstBinding mBinding;
    private MenuViewModel mViewModel;
    SearchView sv;
    public FirstFragment() {
        // Required empty public constructor

    }

    public void setViewModel(MenuViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_first, container, false);

        mBinding.setViewModel(mViewModel);

        rv= (RecyclerView) mBinding.getRoot().findViewById(R.id.lista_veterinarias);

        DbVeterinarias db = new DbVeterinarias(getContext());
        VeterinariasAdapter veterinariasAdapter = new VeterinariasAdapter(mViewModel);
        veterinariasAdapter.setVeterinariasList(db.mostrarVeterinarias());
        rv.setAdapter(veterinariasAdapter);
        sv = (SearchView) mBinding.getRoot().findViewById(R.id.buscarVeterinarias);

        sv.setOnQueryTextListener(mViewModel);
        if (mViewModel != null){
            mViewModel.setVeterinariasAdapter(veterinariasAdapter);
        }

        return mBinding.getRoot();
    }
    //ubicacion del mapa en la aplicacion
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            /*LatLng sydney = new LatLng(-16.404815474841488, -71.52656651318283); //-16.404815474841488, -71.52656651318283
            googleMap.addMarker(new MarkerOptions().position(sydney).title("UNSA Ingenierias"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));*/
            DbVeterinarias db = new DbVeterinarias(getContext());
            ArrayList<Veterinarias> lista = new ArrayList<Veterinarias>();
            lista = db.mostrarVeterinarias();
            if (lista.size() > 0){
                LatLng marcador = new LatLng(Double.parseDouble(lista.get(0).getLatitud()), Double.parseDouble(lista.get(0).getLongitud())); //-16.404815474841488, -71.52656651318283
                googleMap.addMarker(new MarkerOptions().position(marcador).title(lista.get(0).getNombre()));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marcador, 10));
                for (int i=1 ; i< lista.size() ; i++){
                    marcador = new LatLng(Double.parseDouble(lista.get(i).getLatitud()), Double.parseDouble(lista.get(i).getLongitud())); //-16.404815474841488, -71.52656651318283
                    googleMap.addMarker(new MarkerOptions().position(marcador).title(lista.get(i).getNombre()));
                }
            }


        }
    };
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}