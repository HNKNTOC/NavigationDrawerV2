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
    private DayName todayDayName;

    public LessonManager(ScheduleFragment fragment) {
        Resources resources = fragment.getResources();
        inputStream1 = resources.openRawResource(R.raw.raspbukepru);
        inputStream2 = resources.openRawResource(R.raw.raspbukepru2);
        updateDayName();
    }

    private void updateDayName() {
        todayDayName = getDayName();
        if (todayDayName == null){
            Log.w(TAG,"Failed updateDayName()");
        }
    }

    public ArrayList<Lesson> getLesson()  {
        try {
            Document doc = ParsingHTML.transformation(
                    ParsingHTML.parsingSchedule(inputStream1, inputStream2, "UTF-8"));
            ExtractorSchedule extractorSchedule = new ExtractorSchedule(doc);
            return extractorSchedule.extractLessonWhitTime(todayDayName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Возвращает сегодняшний день.
     * @return сегодняшний день.
     */
    private DayName getDayName(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day){
            //TODO для Воскресенья сейчас возврашает понедельниек так как в  DayName нет Воскресенья.
            case 1: return DayName.MONDAY;
            case 2: return DayName.MONDAY;
            case 3: return DayName.TUESDAY;
            case 4: return DayName.WEDNESDAY;
            case 5: return DayName.THURSDAY;
            case 6: return DayName.FRIDAY;
            case 7: return DayName.SATURDAY;
            default: return null;
        }
    }
}
