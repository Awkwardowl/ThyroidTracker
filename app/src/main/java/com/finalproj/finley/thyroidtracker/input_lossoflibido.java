package com.finalproj.finley.thyroidtracker;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class input_lossoflibido extends android.support.v4.app.Fragment {

//    private String title;
//    private int page;

    public static input_lossoflibido newInstance() {
        input_lossoflibido input_lossoflibido = new input_lossoflibido();
        Bundle args = new Bundle();

        return input_lossoflibido;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        page = getArguments().getInt("someInt", 0);
//        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_lossoflibido, container, false);
        return view;
    }
}
