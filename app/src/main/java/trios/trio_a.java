package trios;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproj.finley.thyroidtracker.R;

public class trio_a extends Fragment {

//    private String title;
//    private int page;

    public static trio_a newInstance() {
        trio_a trio_a = new trio_a();
        Bundle args = new Bundle();

        return trio_a;
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
        View view = inflater.inflate(R.layout.fragment_trio_a, container, false);
        return view;
    }

}
