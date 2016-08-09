package com.hnkntoc.navigationdrawerv2;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setting();
    }

    private void setting() {
        Toolbar toolbar = getToolbar();
        settingForDrawerLayout((DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
    }

    private Toolbar getToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void settingForDrawerLayout(DrawerLayout drawerLayout, Toolbar myToolbar) {
        drawerLayout.addDrawerListener(new MyDrawerListener());

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                myToolbar,                    /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        );
        drawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
