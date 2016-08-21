package com.hnkntoc.navigationdrawerv2.logic;

import android.content.res.Resources;
import android.util.Log;

import com.hnkntoc.navigationdrawerv2.R;
import com.hnkntoc.navigationdrawerv2.activity.ScheduleFragment;
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
    private InputStream inputStream1;
    private InputStream inputStream2;
    private Document document;
    private DayName todayDayName;

    public LessonManager(ScheduleFragment fragment) {
        Resources resources = fragment.getResources();
        inputStream1 = resources.openRawResource(R.raw.raspbukepru);
        inputStream2 = resources.openRawResource(R.raw.raspbukepru2);
        updateDayName();
    }

    private void updateDayName() {
        todayDayName = getDayName();
        if (todayDayName == null) {
            Log.w(TAG, "Failed updateDayName()");
        }
    }

    public ArrayList<Lesson> getLesson() {
        try {
            if (document == null) {
                parsingXHTL();
            }
            ExtractorSchedule extractorSchedule = new ExtractorSchedule(document);
            return extractorSchedule.extractLessonWhitTime(todayDayName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void parsingXHTL() throws IOException {
        Log.i(TAG, "parsingXHTL()");
        document = ParsingHTML.transformation(
                ParsingHTML.parsingSchedule(inputStream1, inputStream2, "UTF-8"));
    }

    /**
     * Возвращает сегодняшний день.
     *
     * @return сегодняшний день.
     */
    private DayName getDayName() {
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
