package com.hnkntoc.navigationdrawerv2.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hnkntoc.navigationdrawerv2.R;
import com.hnkntoc.navigationdrawerv2.logic.CardViewFactory;
import com.hnkntoc.navigationdrawerv2.logic.Model;
import com.hnkntoc.navigationdrawerv2.model.SchedulesData;
import com.parsingHTML.logic.element.DayName;

import java.util.List;


/**
 * Фрагмент отображаем список пар.
 */
public class DayFragment extends Fragment {
    private static final String TAG = DayFragment.class.getName();
    public static final String KEY_DAY_NAME = "KeyDayName";
    /**
     * День который отображает DayFragment.
     */
    private DayName dayName;
    private Model model;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView()");
        // Inflate the layout for this fragment
        View myFragment = inflater.inflate(R.layout.fragment_my, container, false);
        LinearLayout linearLayout = (LinearLayout) myFragment.findViewById(R.id.liner_layout);

        if (dayName == null) {
            int intExtraDayName = getArguments().getInt(KEY_DAY_NAME, 0);
            dayName = DayName.values()[intExtraDayName];
            Log.i(TAG, "Get DayName of Intent = " + dayName);
        }

        model = Model.connect(null);
        SchedulesData schedulesData = model.getSchedulesData();

        List<CardView> cardViews = schedulesData.extraxtCardViewForDay(
                dayName, new CardViewFactory(inflater, linearLayout));

        for (CardView cardView : cardViews) {
            linearLayout.addView(cardView);
        }

        return myFragment;
    }
}
