package com.hnkntoc.navigationdrawerv2.view.fragment;


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
import java.util.List;


/**
 * Фрагмент отображаем список пар.
 */
public class DayFragment extends Fragment {
    private static final String TAG = DayFragment.class.getName();
    public static final String KEY_DAY_NAME = "KeyDayName";
    public static final String KEY_LESSON_LIST = "KeyLessonList";
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
        View myFragment = inflater.inflate(R.layout.fragment_list_cards, container, false);
        LinearLayout linearLayout = (LinearLayout) myFragment.findViewById(R.id.liner_layout);

        Bundle arguments = getArguments();
        if (dayName == null) {
            int intExtraDayName = arguments.getInt(KEY_DAY_NAME, 0);
            dayName = DayName.values()[intExtraDayName];
            Log.d(TAG, "Get dayName of arguments = " + dayName.getName());
        }

        if (lessons == null) {
            lessons = (ArrayList<Lesson>) arguments.getSerializable(KEY_LESSON_LIST);
            Log.d(TAG, "Get lessons of arguments = " + lessons);
        }

        Log.i(TAG, "DayFragment = " + toString());
        addCardView(inflater, linearLayout, lessons);
        return myFragment;
    }

    private void addCardView(LayoutInflater inflater, LinearLayout linearLayout, List<Lesson> lessons) {
        CardViewFactory cardViewFactory = new CardViewFactory(inflater, linearLayout, getActivity());
        for (Lesson lesson : lessons) {
            linearLayout.addView(cardViewFactory.addNewCard(lesson));
        }
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy() DayName " + dayName);
        lessons = null;
        dayName = null;
        super.onDestroy();
    }

    @Override
    public String toString() {
        return "DayFragment{" +
                "dayName=" + dayName +
                ", lessons=" + lessons +
                '}';
    }
}
