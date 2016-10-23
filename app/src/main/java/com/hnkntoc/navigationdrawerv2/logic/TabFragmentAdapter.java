package com.hnkntoc.navigationdrawerv2.logic;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.hnkntoc.navigationdrawerv2.view.fragment.DayFragment;
import com.parsingHTML.logic.element.DayName;

import java.util.ArrayList;
import java.util.List;

/**
 * Адаптео для Tabs.
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {
    private static final String TAG = TabFragmentAdapter.class.getName();
    private List<DayFragment> dayFragmentList = new ArrayList<>();

    public TabFragmentAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return dayFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return dayFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.v(TAG, "getPageTitle position = " + position);
        return DayName.values()[position].getNameShort();
    }

    public void addFragments(List<DayFragment> dayFragmentList) {
        this.dayFragmentList = dayFragmentList;
    }
}
