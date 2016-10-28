package com.hnkntoc.navigationdrawerv2.logic;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.hnkntoc.navigationdrawerv2.view.fragment.DayFragment;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.extractor.xml.Lesson;

import java.util.ArrayList;

/**
 * Адаптео для Tabs.
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {
    private static final String TAG = TabFragmentAdapter.class.getName();
    private ArrayList<ArrayList<Lesson>> arrayLists;

    public TabFragmentAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem position = " + position);
        return createDay(position);
    }

    @NonNull
    private DayFragment createDay(int position) {
        DayFragment newDay = new DayFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DayFragment.KEY_LESSON_LIST, arrayLists.get(position));
        bundle.putInt(DayFragment.KEY_DAY_NAME, position);
        Log.d(TAG, "getItem bundle = " + bundle);
        newDay.setArguments(bundle);
        return newDay;
    }

    @Override
    public int getItemPosition(Object object) {
        DayFragment dayFragment = (DayFragment) object;
        Log.d(TAG, "getItemPosition dayFragment = " + dayFragment);
        int ordinal = dayFragment.getDayName().ordinal();
        ArrayList<Lesson> lessons = arrayLists.get(ordinal);
        dayFragment.update(lessons);
        return dayFragment.getDayName().ordinal();
    }

    @Override
    public int getCount() {
        return DayName.values().length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.v(TAG, "getPageTitle position = " + position);
        return DayName.values()[position].getNameShort();
    }

    public void updateLesson(ArrayList<ArrayList<Lesson>> arrayLists) {
        Log.d(TAG, "updateLesson()");
        for (int i = 0; i < arrayLists.size(); i++) {
            Log.d(TAG, "i = " + i + " " + arrayLists.get(i));
            arrayLists.get(i);
        }
        this.arrayLists = arrayLists;
        notifyDataSetChanged();
    }
}
