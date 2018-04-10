package com.finalproj.finley.thyroidtracker;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import trios.trio_a;
import trios.trio_b;
import trios.trio_c;
import trios.trio_d;
import trios.trio_e;
import trios.trio_f;

/**
 * Created by Finley on 04/01/2018.
 */



public class Tab_b extends Fragment {

    int previouspage;
    ViewPager mPager;
    ViewPager inputPager;
    FragmentPagerAdapter adapterViewPager;
    FragmentPagerAdapter adapterViewPager2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View Fragment = inflater.inflate(R.layout.tab_b, container, false);

        final ViewPager viewPager = (ViewPager) Fragment.findViewById(R.id.TypeSelector);
        adapterViewPager = new PageAdapterSelector(getChildFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        final ViewPager viewPager2 = (ViewPager) Fragment.findViewById(R.id.InputSelector);
        adapterViewPager2 = new PagerAdapterInput(getChildFragmentManager());
        viewPager2.setAdapter(adapterViewPager2);

//        Input_Energy input_energy = new Input_Energy();
//        FragmentManager manager = getChildFragmentManager();
//        manager.beginTransaction()
//                .replace(R.id.InputLayout, input_energy, input_energy.getTag())
//                .commit();

        viewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (viewPager2.getCurrentItem()%3==0)
//                {
//                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);
//                }
//                Context context = getContext();
//                Toast toast = Toast.makeText(context, String.valueOf(viewPager.getCurrentItem() +", " +viewPager2.getCurrentItem()+", " +currentpage), Toast.LENGTH_SHORT);
//                toast.show();
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                toast.cancel();
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("debug",position +","+previouspage);

                if ((position)%3==0&&previouspage<position)
                {

                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);
                }
                else if((position+1)%3==0&&previouspage>position)
                {

                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1,true);
                }
                previouspage = viewPager2.getCurrentItem();
                //                if (position%3==0&&currentpage>position)
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//        Context context = getContext();
//        Toast toast = Toast.makeText(context, date + "," + dayOfWeek, Toast.LENGTH_LONG);
//        toast.show();

        Calendar now = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("dd");

        String[] days = new String[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }
        System.out.println(Arrays.toString(days));


        final TextView Sunday = (TextView) Fragment.findViewById(R.id.Sunday);
        Sunday.setText(days[6]);
        Sunday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

        final TextView Monday = (TextView) Fragment.findViewById(R.id.Monday);
        Monday.setText(days[0]);
        Monday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

        final TextView Tuesday = (TextView) Fragment.findViewById(R.id.Tuesday);
        Tuesday.setText(days[1]);
        Tuesday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

        final TextView Wednesday = (TextView) Fragment.findViewById(R.id.Wednesday);
        Wednesday.setText(days[2]);
        Wednesday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

        final TextView Thursday = (TextView) Fragment.findViewById(R.id.Thursday);
        Thursday.setText(days[3]);
        Thursday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

        final TextView Friday = (TextView) Fragment.findViewById(R.id.Friday);
        Friday.setText(days[4]);
        Friday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

        final TextView Saturday = (TextView) Fragment.findViewById(R.id.Saturday);
        Saturday.setText(days[5]);
        Saturday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

        switch (dayOfWeek) {
            case 1:
                Sunday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
            case 2:
                Monday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
            case 3:
                Tuesday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
            case 4:
                Wednesday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
            case 5:
                Thursday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
            case 6:
                Friday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
            case 7:
                Saturday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
        }

//        final Button ButtonA = (Button) Fragment.findViewById(R.id.buttonA);
//        final Button ButtonB = (Button) Fragment.findViewById(R.id.buttonB);
//        final Button ButtonC = (Button) Fragment.findViewById(R.id.buttonC);
//        final Button ButtonD = (Button) Fragment.findViewById(R.id.buttonD);
//
//
//        ImageButton EnergyLogo = (ImageButton) Fragment.findViewById(R.id.EnergyLogo);
//        final TextView textView = (TextView) Fragment.findViewById(R.id.Title);
//        EnergyLogo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText("Energy Levels");
//                ButtonA.setText("High Energy");
//                ButtonA.setBackgroundResource(R.drawable.goodenergy);
//                ButtonB.setText("Medium Energy");
//                ButtonB.setBackgroundResource(R.drawable.okayenergy);
//                ButtonC.setText("Low Energy");
//                ButtonC.setBackgroundResource(R.drawable.badenergy);
//                ButtonD.setText("No Energy");
//            }
//        });
//
//        ImageButton SleepLogo = (ImageButton) Fragment.findViewById(R.id.SleepLogo);
//        SleepLogo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText("Sleep Quality");
//                ButtonA.setText("Slept Well");
//                ButtonA.setBackgroundResource(R.drawable.goodsleep);
//                ButtonB.setText("Slept Okay");
//                ButtonB.setBackgroundResource(R.drawable.okaysleep);
//                ButtonC.setText("Slept Poorly");
//                ButtonC.setBackgroundResource(R.drawable.badsleep);
//                ButtonD.setText("Slept extremely poorly.");
//            }
//        });


        return Fragment;
    }

    public static class PageAdapterSelector extends FragmentPagerAdapter {

        private static int Num_Items = 6;

        public PageAdapterSelector(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
//                    return trio_a.newInstance(0, "Page # 1");
                    return trio_a.newInstance();
                case 1: // Fragment # 0 - This will show FirstFragment different title
//                    return trio_b.newInstance(1, "Page # 2");
                    return trio_b.newInstance();
                case 2: // Fragment # 0 - This will show FirstFragment different title
//                    return trio_b.newInstance(1, "Page # 2");
                    return trio_c.newInstance();
                case 3: // Fragment # 0 - This will show FirstFragment different title
//                    return trio_b.newInstance(1, "Page # 2");
                    return trio_d.newInstance();
                case 4: // Fragment # 0 - This will show FirstFragment different title
//                    return trio_b.newInstance(1, "Page # 2");
                    return trio_e.newInstance();
                case 5: // Fragment # 0 - This will show FirstFragment different title
//                    return trio_b.newInstance(1, "Page # 2");
                    return trio_f.newInstance();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

        @Override
        public int getCount() {
            return Num_Items;
        }
    }


    //BottomHalfViewPager --------------------------------------



    public static class PagerAdapterInput extends FragmentPagerAdapter {

        private static int Num_Items = 16;

        public PagerAdapterInput(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment different title
                    return input_activity.newInstance();
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return input_brainfog.newInstance();
                case 2: // Fragment # 0 - This will show FirstFragment different title
                    return input_brittlenails.newInstance();
                case 3: // Fragment # 0 - This will show FirstFragment different title
                    return input_cold.newInstance();
                case 4: // Fragment # 0 - This will show FirstFragment different title
                    return input_constipation.newInstance();
                case 5: // Fragment # 0 - This will show FirstFragment different title
                    return input_cramps.newInstance();
                case 6: // Fragment # 0 - This will show FirstFragment different title
                    return input_cruciferous.newInstance();
                case 7: // Fragment # 0 - This will show FirstFragment different title
                    return input_depression.newInstance();
                case 8: // Fragment # 0 - This will show FirstFragment
                    return input_energy.newInstance();
                case 9: // Fragment # 0 - This will show FirstFragment different title
                    return input_iodine.newInstance();
                case 10: // Fragment # 0 - This will show FirstFragment different title
                    return input_lossoflibido.newInstance();
                case 11: // Fragment # 0 - This will show FirstFragment different title
                    return input_pinsandneedles.newInstance();
                case 12: // Fragment # 0 - This will show FirstFragment different title
                    return input_sleep.newInstance();
                case 13: // Fragment # 0 - This will show FirstFragment different title
                    return input_soya.newInstance();
                case 14: // Fragment # 0 - This will show FirstFragment different title
                    return input_tiredness.newInstance();
                case 15: // Fragment # 0 - This will show FirstFragment different title
                    return input_weakness.newInstance();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

        @Override
        public int getCount() {
            return Num_Items;
        }
    }
}
