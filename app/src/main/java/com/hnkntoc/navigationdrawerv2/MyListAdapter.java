package com.hnkntoc.navigationdrawerv2;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

/**
 * Created by Nikita on 13.08.2016.
 */
public class MyListAdapter extends ArrayAdapter<Lesson> {

    public MyListAdapter(Context context, int resource, Lesson[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
