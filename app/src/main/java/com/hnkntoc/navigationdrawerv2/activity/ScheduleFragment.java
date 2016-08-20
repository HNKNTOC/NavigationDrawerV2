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
import com.hnkntoc.navigationdrawerv2.logic.LessonManager;
import com.parsingHTML.logic.extractor.xml.Lesson;

import java.util.ArrayList;


/**
 * Фрагмент отображаем список пар.
 */
public class ScheduleFragment extends Fragment {
    private static final String TAG = ScheduleFragment.class.getName();


    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        LayoutInflater ltInflater = getLayoutInflater(savedInstanceState);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.liner_layout);

        LessonManager lessonManager = new LessonManager(this);

        ArrayList<Lesson> lessonList = lessonManager.getLesson();

        CardViewFactory factory = new CardViewFactory(inflater,linearLayout);

        for (Lesson lesson : lessonList) {
            Log.i(TAG,"Add lesson "+lesson);
            View card = factory.addNewCard(
                    lesson.getName(), lesson.getDescription(), lesson.getTime1(), lesson.getTime2(), lesson.getNumber());
            linearLayout.addView(card);
        }

        return view;
    }

}
