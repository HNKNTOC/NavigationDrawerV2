package com.hnkntoc.navigationdrawerv2.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hnkntoc.navigationdrawerv2.R;
import com.hnkntoc.navigationdrawerv2.logic.CardViewFactory;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.extractor.xml.Lesson;

import java.util.ArrayList;


/**
 * Фрагмент отображаем список пар.
 */
public class DayFragment extends Fragment {
    private static final String TAG = DayFragment.class.getName();
    public static final String KEY_DAY_NAME = "KeyDayName";
    public static final String KEY_LESSON = "KeyLesson";
    /**
     * День который отображает DayFragment.
     */
    private DayName dayName;
    private ArrayList<Lesson> lessons;


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

        if (lessons == null) {
            lessons = (ArrayList<Lesson>) getArguments().getSerializable(KEY_LESSON);
        }

        CardViewFactory cardViewFactory = new CardViewFactory(inflater, linearLayout);

        for (Lesson lesson : lessons) {
            linearLayout.addView(cardViewFactory.addNewCard(lesson));
        }

        return myFragment;
    }
}
