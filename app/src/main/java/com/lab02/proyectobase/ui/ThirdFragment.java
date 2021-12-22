package com.lab02.proyectobase.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lab02.proyectobase.R;
import com.lab02.proyectobase.model.Albergues;
import com.lab02.proyectobase.model.Data.DbAlbergues;
import com.lab02.proyectobase.model.Data.DbVeterinarias;
import com.lab02.proyectobase.model.Veterinarias;
import com.lab02.proyectobase.model.grafico.BarChartView;
import com.lab02.proyectobase.model.grafico.BarData;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
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
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        BarChartView barChartView = (BarChartView) view.findViewById(R.id.barChart);
        BarChartView barChartView2 = (BarChartView) view.findViewById(R.id.barChart2);


        DbVeterinarias dbVeterinarias = new DbVeterinarias(getContext());
        ArrayList<Veterinarias> array = dbVeterinarias.mostrarVeterinarias();
        ArrayList<BarData> datos = new ArrayList<BarData>();
        for (int i =0 ;i<array.size();i++){

            if (datos.size() ==0){
                datos.add(new BarData(array.get(i).getDistrito(),1));
            }else{
                if (!getStrings(datos).contains(array.get(i).getDistrito())){
                    datos.add(new BarData(array.get(i).getDistrito(),1));
                }else{
                    for (int j=0 ;j<datos.size();j++){
                        if (datos.get(j).getLayoutX().equalsIgnoreCase(array.get(i).getDistrito())){
                            datos.get(j).setValue(datos.get(j).getValor()+1);
                        }
                    }
                }
            }
        }
        BarData[] arrayBarData = (BarData[]) datos.toArray((new BarData[datos.size()]));
        barChartView.dibujarGraficoData(arrayBarData);


        DbAlbergues dbAlbergues = new DbAlbergues(getContext());
        ArrayList<Albergues> array2 = dbAlbergues.mostrarAlbergues();
        ArrayList<BarData> datos2 = new ArrayList<BarData>();
        for (int i =0 ;i<array2.size();i++){

            if (datos2.size() ==0){
                datos2.add(new BarData(array2.get(i).getDistrito(),1));
            }else{
                if (!getStrings(datos2).contains(array2.get(i).getDistrito())){
                    datos2.add(new BarData(array2.get(i).getDistrito(),1));
                }else{
                    for (int j=0 ;j<datos2.size();j++){
                        if (datos2.get(j).getLayoutX().equalsIgnoreCase(array2.get(i).getDistrito())){
                            datos2.get(j).setValue(datos2.get(j).getValor()+1);
                        }
                    }
                }
            }
        }

        BarData[] arrayBarData2 = (BarData[]) datos2.toArray((new BarData[datos2.size()]));
        Log.d("DATOS 2: ", "DATOS DEL ARRAY DOS : "+datos2.size()+" VALOR   " +datos2.get(1).getValor());
        barChartView2.dibujarGraficoData(arrayBarData2);
        return view;
    }
    public ArrayList<String> getStrings (ArrayList<BarData> arreglo){
        ArrayList <String> res = new ArrayList<String>();
        for (int i=0; i<arreglo.size();i++){
            res.add(arreglo.get(i).getLayoutX());
        }
        return res;
    }
}