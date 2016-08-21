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
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.extractor.xml.Lesson;

import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Фрагмент отображаем список пар.
 */
public class DayFragment extends Fragment {
    private static final String TAG = DayFragment.class.getName();
    private static Document document;
    public static final String KEY_DAY_NAME = "KeyDayName";
    /**
     * День который отображает DayFragment.
     */
    private DayName dayName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragment = inflater.inflate(R.layout.fragment_my, container, false);
        LinearLayout linearLayout = (LinearLayout) myFragment.findViewById(R.id.liner_layout);

        if (document == null) {
            try {
                Log.i(TAG, "Document == null. Parsing document.");
                document = LessonManager.parsingHTML(getResources());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (dayName == null) {
            int intExtraDayName = getArguments().getInt(KEY_DAY_NAME, 0);
            dayName = DayName.values()[intExtraDayName];
            Log.i(TAG, "Get DayName of Intent = " + dayName);
        }

        ArrayList<Lesson> lessonList = LessonManager.getLesson(dayName, document);

        CardViewFactory factory = new CardViewFactory(inflater, linearLayout);

        for (Lesson lesson : lessonList) {
            Log.i(TAG, "Add lesson " + lesson);
            View card = factory.addNewCard(
                    lesson.getName(), lesson.getDescription(), lesson.getTime1(), lesson.getTime2(), lesson.getNumber());
            linearLayout.addView(card);
        }

        return myFragment;
    }

}
