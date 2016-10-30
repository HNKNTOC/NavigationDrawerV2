package com.hnkntoc.navigationdrawerv2.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.hnkntoc.navigationdrawerv2.R;
import com.hnkntoc.navigationdrawerv2.logic.CardViewFactory;
import com.parsingHTML.logic.extractor.xml.Lesson;

/**
 * Отображает подробное описание урока.
 */
public class DescriptionActivity extends AppCompatActivity {
    private static final String TAG = DescriptionActivity.class.getName();

    public static final String KEY_LESSON = "KeyLesson";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Lesson lesson = (Lesson) getIntent().getSerializableExtra(KEY_LESSON);

        Log.d(TAG, "Lesson = " + lesson);

        TextView textViewLessonName = (TextView) findViewById(R.id.desc_name);
        textViewLessonName.setText(lesson.getName());

        TextView textViewTime1 = (TextView) findViewById(R.id.desc_time1);
        textViewTime1.setText(lesson.getTime1());

        TextView textViewTime2 = (TextView) findViewById(R.id.desc_time2);
        textViewTime2.setText(lesson.getTime2());

        TextView textViewLessonDescription = (TextView) findViewById(R.id.desc_description);
        textViewLessonDescription.setText(lesson.getDescription());

        TextView textViewNameTeachers = (TextView) findViewById(R.id.desc_name_teachers);
        String teacherNames = lesson.getTeacherNames();
        teacherNames = teacherNames.replace(",", ",\n");
        textViewNameTeachers.setText(teacherNames);

        ImageView imageViewLessonNumber = (ImageView) findViewById(R.id.desc_image_number);
        imageViewLessonNumber.setImageResource(CardViewFactory.getImageLessonNumber(lesson.getNumber()));
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();
    }
}
