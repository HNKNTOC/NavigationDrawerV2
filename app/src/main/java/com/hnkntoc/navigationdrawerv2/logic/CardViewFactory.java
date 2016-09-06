package com.hnkntoc.navigationdrawerv2.logic;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hnkntoc.navigationdrawerv2.R;
import com.hnkntoc.navigationdrawerv2.view.activity.DescriptionActivity;
import com.parsingHTML.logic.extractor.xml.Lesson;

/**
 * Фабрика по сазданию CardView.
 */
public class CardViewFactory {
    private static final String TAG = CardViewFactory.class.getName();
    private LayoutInflater layoutInflater;
    private ViewGroup viewGroup;
    private Activity activity;

    public CardViewFactory(LayoutInflater layoutInflater, ViewGroup viewGroup, Activity activity) {
        this.layoutInflater = layoutInflater;
        this.viewGroup = viewGroup;
        this.activity = activity;
    }

    public CardView addNewCard(Lesson lesson) {
        CardView card = (CardView) layoutInflater.inflate(R.layout.custom_card_viwe, viewGroup, false);

        TextView textViewName = (TextView) card.findViewById(R.id.card_name);
        textViewName.setText(lesson.getName());

        TextView textViewDescription = (TextView) card.findViewById(R.id.card_description);
        textViewDescription.setText(lesson.getDescription());

        TextView textViewTime1 = (TextView) card.findViewById(R.id.card_time_1);
        textViewTime1.setText(lesson.getTime1());

        TextView textViewTime2 = (TextView) card.findViewById(R.id.card_time_2);
        textViewTime2.setText(lesson.getTime2());

        ImageView imageViewNumber = (ImageView) card.findViewById(R.id.card_image_number);
        imageViewNumber.setImageResource(getImageLessonNumber(lesson.getNumber()));

        card.setOnClickListener(new CardViewListener(lesson));

        Log.d(TAG, "addNewCard return " + card);
        return card;
    }

    public static int getImageLessonNumber(int lessonNumber) {
        if (lessonNumber == 1) {
            return R.drawable.lesson_number_1;
        }
        if (lessonNumber == 2) {
            return R.drawable.lesson_number_2;
        }
        if (lessonNumber == 3) {
            return R.drawable.lesson_number_3;
        }
        if (lessonNumber == 4) {
            return R.drawable.lesson_number_4;
        }
        if (lessonNumber == 5) {
            return R.drawable.lesson_number_5;
        }
        if (lessonNumber == 6) {
            return R.drawable.lesson_number_6;
        }
        if (lessonNumber == 7) {
            return R.drawable.lesson_number_7;
        }
        if (lessonNumber == 8) {
            return R.drawable.lesson_number_8;
        }
        if (lessonNumber == 9) {
            return R.drawable.lesson_number_9;
        }
        Log.e(TAG, "Failed getImageLessonNumber lessonNumber = " + lessonNumber);
        return R.drawable.error;
    }

    /**
     * Обработчик нажатия на CardView.
     * Вызывает DescriptionActivity.
     */
    private class CardViewListener implements View.OnClickListener {

        /**
         * Lesson для DescriptionActivity.
         */
        private final Lesson lesson;

        public CardViewListener(Lesson lesson) {
            this.lesson = lesson;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(activity, DescriptionActivity.class);
            intent.putExtra(DescriptionActivity.KEY_LESSON, lesson);
            activity.startActivity(intent);
        }
    }
}
