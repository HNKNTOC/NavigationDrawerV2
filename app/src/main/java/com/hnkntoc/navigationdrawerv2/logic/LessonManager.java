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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Менеджер для работ с Lesson.
 */
public class LessonManager {

    private static final String TAG = LessonManager.class.getName();


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
        String dayNameShort = new SimpleDateFormat("EE", Locale.getDefault()).format(new Date());
        DayName dayName = DayName.valueOfNameShort(dayNameShort);
        if (dayName == null) {
            Log.w(TAG, "getDayName return null! dayNameShort = " + dayNameShort);
        }
        Log.d(TAG, "getDayName return " + dayNameShort);
        return dayName;
    }
}
