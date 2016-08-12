package com.hnkntoc.navigationdrawerv2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private DrawerLayout drawerLayout;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        setSupportActionBar(toolbar);

        setting();
    }

    private void setting() {
        settingFloatingActionButton();
        settingDrawerLayout();
        settingToolbar();
        settingTabLayout();
        settingViewPager();
    }

    private void settingViewPager() {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ScheduleFragment(), getString(R.string._1_day));
        adapter.addFragment(new ScheduleFragment(), getString(R.string._3_day));
        adapter.addFragment(new ScheduleFragment(), getString(R.string._7_day));
        viewPager.setAdapter(adapter);
    }

    private void settingTabLayout() {
        tabLayout.setupWithViewPager(viewPager);
    }

    private void settingToolbar() {

    }

    private void settingFloatingActionButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick FloatingActionButton.");
            }
        });
    }


    private void settingDrawerLayout() {
        drawerLayout.addDrawerListener(new MyDrawerListener());
        setTitle(R.string.schedule);
    }

    /**
     * Слушатель для открытия и закрытия меню.
     */
    class MyDrawerListener implements DrawerLayout.DrawerListener {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            Log.i(TAG, "onDrawerSlide");
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            Log.i(TAG, "onDrawerOpened");
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            Log.i(TAG, "onDrawerClosed");
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            Log.i(TAG, "onDrawerStateChanged");
        }
    }

}
