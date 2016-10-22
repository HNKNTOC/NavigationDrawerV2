package com.hnkntoc.navigationdrawerv2.logic;

import android.content.res.Resources;
import android.util.Log;

import com.hnkntoc.navigationdrawerv2.R;
import com.parsingHTML.logic.ParsingHTML;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.element.NumeratorName;
import com.parsingHTML.logic.extractor.xml.ExtractorSchedule;
import com.parsingHTML.logic.extractor.xml.Lesson;
import com.parsingHTML.logic.parser.exception.ExceptionParser;

import org.jsoup.nodes.Element;
import org.w3c.dom.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Менеджер для работ с Lesson.
 */
public class LessonHelper {

    private static final String TAG = LessonHelper.class.getName();


    public static ArrayList<Lesson> getLesson(DayName dayName, Document document) {
        Log.d(TAG, "getLesson() dayName " + dayName + " document = " + document);
        ArrayList<Lesson> lessons = null;
        try {
            lessons = ExtractorSchedule.extractLessonWhitTime(dayName, NumeratorName.NUMERATOR, document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "getLesson() return " + lessons);
        return lessons;
    }

    public static Document parsingHTML(InputStream timeContent, InputStream scheduleContent) throws IOException {
        Log.i(TAG, "parsingHTML()1 InputStream");
        try {
            return ParsingHTML.transformation(
                    ParsingHTML.parsingSchedule(timeContent, scheduleContent, "UTF-8"));
        } catch (ExceptionParser exceptionParser) {
            exceptionParser.printStackTrace();
            return null;
        }
    }

    public static Document parsingHTML(Element timeContent, Element scheduleContent) {
        Log.i(TAG, "parsingHTML()1 Element");
        try {
            return ParsingHTML.transformation(
                    ParsingHTML.parsingSchedule(timeContent, scheduleContent, "UTF-8"));
        } catch (ExceptionParser exceptionParser) {
            exceptionParser.printStackTrace();
            return null;
        }
    }


    public static Document parsingHTML(Resources resources) throws IOException {
        Log.i(TAG, "parsingHTML()2");
        return parsingHTML(
                resources.openRawResource(R.raw.raspbukepru),
                resources.openRawResource(R.raw.raspbukepru2));
    }


    /**
     * Возвращает сегодняшний день.
     *
     * @return сегодняшний день.
     */
    public static DayName getDayName() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        Log.d(TAG, "DAY_OF_WEEK = " + day);
        switch (day) {
            case 1:
                return DayName.SUNDAY;
            case 2:
                return DayName.MONDAY;
            case 3:
                return DayName.TUESDAY;
            case 4:
                return DayName.WEDNESDAY;
            case 5:
                return DayName.THURSDAY;
            case 6:
                return DayName.FRIDAY;
            case 7:
                return DayName.SATURDAY;
            default:
                Log.w(TAG, "getDayName() return null! day = " + day);
                return DayName.MONDAY;
        }
    }
}
