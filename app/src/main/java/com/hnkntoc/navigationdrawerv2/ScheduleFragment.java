package com.hnkntoc.navigationdrawerv2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * Фрагмент отображаем список пар.
 */
public class ScheduleFragment extends ListFragment {


    public ScheduleFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_my, container, false);
        Lesson[] lessons = new Lesson[1];
        lessons[0] = new Lesson("География","Практика 432н","12:30-13:00","13:10-14:00",4,false);
        lessons[1] = new Lesson("Информатика","Лекция 212","14:30-15:00","16:10-16:00",5,false);
        setListAdapter(new MyListAdapter(getActivity(),R.layout.fragment_my,lessons));
        return mainView;
    }

}
