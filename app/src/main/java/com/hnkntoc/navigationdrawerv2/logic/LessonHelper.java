package com.hnkntoc.navigationdrawerv2.logic;

import android.content.res.Resources;
import android.util.Log;

import com.hnkntoc.navigationdrawerv2.R;
import com.parsingHTML.logic.ParsingHTML;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.extractor.xml.ExtractorSchedule;
import com.parsingHTML.logic.extractor.xml.Lesson;

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
        ExtractorSchedule extractorSchedule = new ExtractorSchedule(document);
        ArrayList<Lesson> lessons = extractorSchedule.extractLessonWhitTime(dayName);
        Log.d(TAG, "getLesson() return " + lessons);
        return lessons;
    }

    public static Document parsingHTML(InputStream inputStream1, InputStream inputStream2) throws IOException {
        Log.i(TAG, "parsingHTML()1");
        return ParsingHTML.transformation(
                ParsingHTML.parsingSchedule(inputStream1, inputStream2, "UTF-8"));
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
        Log.w(TAG, "DAY_OF_WEEK = " + day);
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
                return null;
        }
    }
}
