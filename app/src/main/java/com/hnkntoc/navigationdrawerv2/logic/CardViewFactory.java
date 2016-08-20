package com.hnkntoc.navigationdrawerv2.logic;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hnkntoc.navigationdrawerv2.R;

/**
 * Фабрика по сазданию CardView.
 */
public class CardViewFactory {
    private static final String TAG = CardViewFactory.class.getName();
    private LayoutInflater layoutInflater;
    private ViewGroup viewGroup;

    public CardViewFactory(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.layoutInflater = layoutInflater;
        this.viewGroup = viewGroup;
    }

    public View addNewCard(String name, String description, String time1, String time2, int lessonNumber) {
        View card = layoutInflater.inflate(R.layout.custon_card_viwe, viewGroup,false);

        TextView textViewName = (TextView) card.findViewById(R.id.card_name);
        textViewName.setText(name);

        TextView textViewDescription = (TextView) card.findViewById(R.id.card_description);
        textViewDescription.setText(description);

        TextView textViewTime1 = (TextView) card.findViewById(R.id.card_time_1);
        textViewTime1.setText(time1);

        TextView textViewTime2 = (TextView) card.findViewById(R.id.card_time_2);
        textViewTime2.setText(time2);

        ImageView imageViewNumber = (ImageView) card.findViewById(R.id.card_image_number);
        imageViewNumber.setImageResource(getImageLessonNumber(lessonNumber));

        Log.d(TAG, "addNewCard return " + card);
        return card;
    }

    private int getImageLessonNumber(int lessonNumber) {
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

}
