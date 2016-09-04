package com.hnkntoc.navigationdrawerv2.activity;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.hnkntoc.navigationdrawerv2.R;
import com.hnkntoc.navigationdrawerv2.logic.LessonHelper;
import com.parsingHTML.logic.element.DayName;
import com.parsingHTML.logic.extractor.xml.Lesson;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Главное MainActivity.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    /**
     * DrawerLayout который находится на  MainActivity.
     */
    private DrawerLayout drawerLayout;
    /**
     * Кнопка обновления.
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
        Log.i(TAG, "onCreate()");
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentPagerAdapter adapter = createFragmentPagerAdapter();
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.addDrawerListener(new MyDrawerListener());

        fab = (FloatingActionButton) findViewById(R.id.fab);
        settingFloatingActionButton(fab);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new MenuItemListener(navigationView, drawerLayout));

        setTitle(R.string.schedule);
    }

    private Document initializationDocument() {
        Log.i(TAG, "initializationDocument()");
        Document document = renewDocument();
        if (document == null) {
            try {
                Log.i(TAG, "initializationDocument parsingHTML!");
                document = LessonHelper.parsingHTML(getResources());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG, "initializationDocument return " + document);
        return document;
    }

    public Document renewDocument() {
        Log.i(TAG, "renewDocument()");
        try {
            File file = new File(getFilesDir(), "Schedules.xml");
            if (!file.exists()) {
                Log.i(TAG, "File not exists!");
                return null;
            }
            Log.i(TAG, "File path = " + file.getAbsolutePath());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            return dBuilder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            Log.e(TAG, "Failed renewDocument()", e);
        }
        return null;
    }

    public void saveDOC(Document document) {
        Log.i(TAG, "saveDOC()");
        try {
            File file = new File(getFilesDir().getAbsolutePath(), "Schedules.xml");
            Log.i(TAG, "File path = " + file.getAbsolutePath());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(file);
            Source input = new DOMSource(document);

            transformer.transform(input, output);
        } catch (TransformerException e) {
            Log.e(TAG, "Failed saveDOC()");
            e.printStackTrace();
        }
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
        Document document = initializationDocument();
        addFragment(adapter, DayName.MONDAY, document);
        addFragment(adapter, DayName.TUESDAY, document);
        addFragment(adapter, DayName.WEDNESDAY, document);
        addFragment(adapter, DayName.THURSDAY, document);
        addFragment(adapter, DayName.FRIDAY, document);
        addFragment(adapter, DayName.SATURDAY, document);
        addFragment(adapter, DayName.SUNDAY, document);
        saveDOC(document);
        return adapter;
    }

    protected void addFragment(Adapter adapter, DayName dayName, Document document) {
        DayFragment fragment = new DayFragment();
        Bundle bundle = new Bundle();
        ArrayList<Lesson> lesson = LessonHelper.getLesson(dayName, document);
        bundle.putSerializable(DayFragment.KEY_LESSON_LIST, lesson);
        bundle.putInt(DayFragment.KEY_DAY_NAME, dayName.ordinal());
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, dayName.getNameShort());
    }


    @Override
    protected void onStart() {
        super.onStart();
        selectTabToday();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause()");
        super.onPause();
    }

    /**
     * Выделить Tab с сегодняшним днём.
     */
    private void selectTabToday() {
        int ordinal = LessonHelper.getDayName().ordinal();
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
     * Убрать выбор с Tab.
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
            Log.v(TAG, "onDrawerSlide");
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

    /**
     * Слушателт для Navigation Drawer меню.
     */
    private class MenuItemListener implements NavigationView.OnNavigationItemSelectedListener {

        final NavigationView navigationView;
        final DrawerLayout drawerLayout;

        public MenuItemListener(NavigationView navigationView, DrawerLayout drawerLayout) {
            this.navigationView = navigationView;
            this.drawerLayout = drawerLayout;
        }

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Log.d(TAG, "onNavigationItemSelected() Item = " + item.getTitle());
            selectItem(item);
            return true;
        }

        private void selectItem(MenuItem item) {

            item.setCheckable(true);
            setTitle(item.getTitle());
            drawerLayout.closeDrawers();
        }
    }
}
