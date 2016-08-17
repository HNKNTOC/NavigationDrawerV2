package com.hnkntoc.navigationdrawerv2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parsingHTML.logic.ParsingHTML;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.extractor.xml.ExtractorSchedule;
import com.parsingHTML.logic.extractor.xml.Lesson;

import org.w3c.dom.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * Фрагмент отображаем список пар.
 */
public class ScheduleFragment extends Fragment {
    private static final String TAG = MainActivity.class.getName();


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

        InputStream inputStream1 = getResources().openRawResource(R.raw.raspbukepru);
        InputStream inputStream2 = getResources().openRawResource(R.raw.raspbukepru2);

        ArrayList<Lesson> lessonList = getLesson(inputStream1, inputStream2);

        for (Lesson lesson : lessonList) {
            Log.i(TAG,"Add lesson "+lesson);
            linearLayout.addView(addNewCard(inflater,linearLayout,lesson.getName(),lesson.getDescription()));
        }

        return view;
    }

    private ArrayList<Lesson> getLesson(InputStream inputStream1, InputStream inputStream2)  {
        try {
            Document doc = ParsingHTML.transformation(
                    ParsingHTML.parsingSchedule(inputStream1, inputStream2, "UTF-8"));
            ExtractorSchedule extractorSchedule = new ExtractorSchedule(doc);
            return extractorSchedule.extractLessonWhitTime(DayName.WEDNESDAY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public View addNewCard(LayoutInflater layoutInflater, ViewGroup root, String name, String description){
        View card = layoutInflater.inflate(R.layout.custon_card_viwe, root,false);
        TextView textView = (TextView) card.findViewById(R.id.card_name_IdNumber0);
        textView.setText(name);
        TextView textViewDescription = (TextView) card.findViewById(R.id.card_description_IdNumber0);
        textViewDescription.setText(description);
        return card;
    }

}
