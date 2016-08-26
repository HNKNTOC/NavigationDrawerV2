package com.hnkntoc.navigationdrawerv2.model;

import android.support.v7.widget.CardView;

import com.hnkntoc.navigationdrawerv2.logic.CardViewFactory;
import com.hnkntoc.navigationdrawerv2.logic.LessonHelper;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.extractor.xml.Lesson;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikita on 25.08.2016.
 */
public class SchedulesData {
    private Document document;

    public SchedulesData(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public List<Lesson> extractLessonForDay(DayName dayName) {
        return LessonHelper.getLesson(dayName, document);
    }

    public List<CardView> extraxtCardViewForDay(DayName dayName, CardViewFactory factory) {
        List<Lesson> lessons = extractLessonForDay(dayName);
        List<CardView> cardViews = new ArrayList<>();

        for (Lesson lesson : lessons) {
            cardViews.add(factory.addNewCard(lesson));
        }

        return cardViews;
    }

}
