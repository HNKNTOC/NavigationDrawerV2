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
public class LessonManager {

    private static final String TAG = LessonManager.class.getName();


    public static ArrayList<Lesson> getLesson(DayName dayName, Document document) {
        Log.d(TAG, "getLesson()");
        ExtractorSchedule extractorSchedule = new ExtractorSchedule(document);
        return extractorSchedule.extractLessonWhitTime(dayName);
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
    private static DayName getDayName() {
        Log.d(TAG, "getDayName()");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int dayNumber = calendar.get(Calendar.DAY_OF_WEEK);
        dayNumber--;
        DayName dayName = DayName.values()[dayNumber];
        if (dayName == null) {
            Log.w(TAG, "getDayName return null! dayNameShort = " + dayNumber);
        }
        Log.d(TAG, "getDayName return " + dayName);
        return dayName;
    }
}
