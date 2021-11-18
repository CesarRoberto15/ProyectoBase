package com.lab02.proyectobase.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lab02.proyectobase.R;
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
        ArrayList arrayList = new ArrayList<>();
        BarData barData = new BarData("C", 100);
        arrayList.add(barData);
        barData = new BarData("Java", 50);
        arrayList.add(barData);
        barData = new BarData("Python", 150);
        arrayList.add(barData);
        barData = new BarData("C++", 60);
        arrayList.add(barData);
        barData = new BarData("C#", 100);
        arrayList.add(barData);
        barData = new BarData("JS", 20);
        arrayList.add(barData);
        barData = new BarData("Kotlin", 40);
        arrayList.add(barData);


        BarData[] arrayBarData = (BarData[]) arrayList.toArray((new BarData[arrayList.size()]));
        barChartView.dibujarGraficoData(arrayBarData);

        BarChartView barChartView2 = (BarChartView) view.findViewById(R.id.barChart2);
        ArrayList arrayList2 = new ArrayList<>();
        BarData barData2 = new BarData("C", 100);
        arrayList2.add(barData2);
        barData2 = new BarData("Java", 50);
        arrayList2.add(barData2);
        barData2 = new BarData("Python", 150);
        arrayList2.add(barData2);
        barData2 = new BarData("C++", 60);
        arrayList2.add(barData2);
        barData2 = new BarData("C#", 100);
        arrayList2.add(barData2);
        barData2 = new BarData("JS", 20);
        arrayList2.add(barData2);
        barData2 = new BarData("Kotlin", 40);
        arrayList2.add(barData2);


        BarData[] arrayBarData2 = (BarData[]) arrayList2.toArray((new BarData[arrayList2.size()]));
        barChartView2.dibujarGraficoData(arrayBarData2);
        return view;
    }
}