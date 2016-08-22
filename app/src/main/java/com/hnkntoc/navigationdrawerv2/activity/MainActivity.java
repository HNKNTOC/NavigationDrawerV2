package com.hnkntoc.navigationdrawerv2.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.hnkntoc.navigationdrawerv2.R;
import com.hnkntoc.navigationdrawerv2.logic.LessonManager;
import com.parsingHTML.logic.element.DayName;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    /**
     * DrawerLayout который находится на  MainActivity.
     */
    private DrawerLayout drawerLayout;
    /**
     * Кнопка обновленияю.
     */
    private FloatingActionButton fab;
    /**
     * Toolbar который находится на  MainActivity.
     */
    private Toolbar toolbar;
    /**
     * TabLayout который находится на  MainActivity.
     */
    private TabLayout tabLayout;
    /**
     * ViewPager который находится на  MainActivity.
     * для отображения DayFragment.
     */
    private ViewPager viewPager;
    /**
     * Позиция TabLayout.Tab.
     * Выделяется в зависимости от дня недели.
     */
    private int positionTabSelect = -1;


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
        setTitle(R.string.schedule);

        settingFloatingActionButton(fab);

        FragmentPagerAdapter adapter = createFragmentPagerAdapter();
        viewPager.setAdapter(adapter);

        drawerLayout.addDrawerListener(new MyDrawerListener());
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Настройка для FloatingActionButton.
     */
    private void settingFloatingActionButton(FloatingActionButton fab) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick FloatingActionButton.");
            }
        });
    }

    /**
     * Создание и настройка FragmentPagerAdapter.
     */
    private FragmentPagerAdapter createFragmentPagerAdapter() {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        addFragment(adapter, DayName.MONDAY);
        addFragment(adapter, DayName.TUESDAY);
        addFragment(adapter, DayName.WEDNESDAY);
        addFragment(adapter, DayName.THURSDAY);
        addFragment(adapter, DayName.FRIDAY);
        addFragment(adapter, DayName.SATURDAY);
        addFragment(adapter, DayName.SUNDAY);
        return adapter;
    }

    protected void addFragment(Adapter adapter, DayName dayName) {
        DayFragment fragment = new DayFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(DayFragment.KEY_DAY_NAME, dayName.ordinal());
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, dayName.getNameShort());
    }


    @Override
    protected void onStart() {
        super.onStart();
        selectTabToday();
    }

    /**
     * Выделить Tab с сегодняшним днём.
     */
    private void selectTabToday() {
        int ordinal = LessonManager.getDayName().ordinal();
        Log.i(TAG, "selectTabToday() ordinal = " + ordinal + " positionTabSelect = " + positionTabSelect);

        if (positionTabSelect == -1) {
            Log.d(TAG, "positionTabSelect == -1.");
            selectTab(ordinal);
            return;
        }

        if (positionTabSelect == ordinal) {
            Log.d(TAG, "Tab saved position.");
        } else {
            Log.d(TAG, "Tab new position.");
            resetSelectTab(positionTabSelect);
            selectTab(ordinal);
        }
    }

    /**
     * Выбрать Tab.
     *
     * @param position позиция Tab.
     */
    private void selectTab(int position) {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        if (tab != null) {
            tab.setIcon(R.drawable.star);
            viewPager.setCurrentItem(position);
            Log.d(TAG, "Select tab = " + tab.getText());
            positionTabSelect = position;
        } else {
            Log.w(TAG, "Select tab == null!");
        }
    }

    /**
     * Убрать вобор с Tab.
     *
     * @param position позиция Tab.
     */
    private void resetSelectTab(int position) {
        TabLayout.Tab tabAt = tabLayout.getTabAt(position);
        if (tabAt != null) {
            tabAt.setIcon(null);
            Log.d(TAG, "resetSelectTab() to " + tabAt.getText());
        } else {
            Log.w(TAG, "Failed resetSelectTab() tabAt == null. positionTabSelect = " + this.positionTabSelect);
        }
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
