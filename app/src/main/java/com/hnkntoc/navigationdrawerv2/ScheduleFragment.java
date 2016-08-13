package com.hnkntoc.navigationdrawerv2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Фрагмент отображаем список пар.
 */
public class ScheduleFragment extends Fragment {


    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        LayoutInflater ltInflater = getLayoutInflater(savedInstanceState);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.liner_layout);

        linearLayout.addView(addNewCard(inflater,linearLayout,"Матиматика 1","Пара 1"));
        linearLayout.addView(addNewCard(inflater,linearLayout,"Ин. яз 2","Пара 2"));
        linearLayout.addView(addNewCard(inflater,linearLayout,"История 3","Пара 3"));
        linearLayout.addView(addNewCard(inflater,linearLayout,"Физра 4","Пара 4"));

        return view;
    }

    public View addNewCard(LayoutInflater layoutInflater, ViewGroup root, String name, String description){
        View card = layoutInflater.inflate(R.layout.custon_card_viwe, root,false);
        TextView textView = (TextView) card.findViewById(R.id.card_name_IdNumber0);
        textView.setText(name);
        TextView textViewDescription = (TextView) card.findViewById(R.id.card_description_IdNumber0);
        textViewDescription.setText(description);
        return card;
    }

}
