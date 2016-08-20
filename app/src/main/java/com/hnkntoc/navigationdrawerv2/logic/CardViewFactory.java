package com.hnkntoc.navigationdrawerv2.logic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hnkntoc.navigationdrawerv2.R;

/**
 * Фабрика по сазданию CardView.
 */
public class CardViewFactory {
    private LayoutInflater layoutInflater;
    private ViewGroup viewGroup;

    public CardViewFactory(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.layoutInflater = layoutInflater;
        this.viewGroup = viewGroup;
    }

    public View addNewCard(String name, String description, String time1, String time2){
        View card = layoutInflater.inflate(R.layout.custon_card_viwe, viewGroup,false);

        TextView textViewName = (TextView) card.findViewById(R.id.card_name_IdNumber0);
        textViewName.setText(name);
        TextView textViewDescription = (TextView) card.findViewById(R.id.card_description_IdNumber0);
        textViewDescription.setText(description);
        TextView textViewTime1 = (TextView) card.findViewById(R.id.card_time_1_IdNumber0);
        textViewTime1.setText(time1);
        TextView textViewTime2 = (TextView) card.findViewById(R.id.card_time_2_IdNumber0);
        textViewTime2.setText(time2);
        return card;
    }

}
