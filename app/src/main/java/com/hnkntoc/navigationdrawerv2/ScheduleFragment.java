package com.hnkntoc.navigationdrawerv2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * Фрагмент отображаем список пар.
 */
public class ScheduleFragment extends Fragment {


    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.liner_layout);
        CardViewFactory cardViewFactory = new CardViewFactory(getActivity());
        for (int i = 0; i < 20; i++) {
            linearLayout.addView(cardViewFactory.getCardView());
        }
        return view;
    }

}
