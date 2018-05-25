package trios;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.finalproj.finley.thyroidtracker.R;
import com.finalproj.finley.thyroidtracker.Tab_b;

public class trio_f extends Fragment {

//    private String title;
//    private int page;

    public static trio_f newInstance() {
        trio_f trio_f = new trio_f();
        Bundle args = new Bundle();

        return trio_f;
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
        View view = inflater.inflate(R.layout.fragment_trio_f, container, false);

        ImageButton button1 = (ImageButton) view.findViewById(R.id.SleepLogo);
        button1.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                Tab_b fragment = (Tab_b)getParentFragment();
                fragment.SetPage(15);
            }
        });

        ImageButton button2 = (ImageButton) view.findViewById(R.id.EmotionalLogo);
        button2.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                Tab_b fragment = (Tab_b)getParentFragment();
                fragment.SetPage(16);
            }
        });

        ImageButton button3 = (ImageButton) view.findViewById(R.id.AlcoholLogo);
        button3.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                Tab_b fragment = (Tab_b)getParentFragment();
                fragment.SetPage(17);
            }
        });

        return view;
    }

}
