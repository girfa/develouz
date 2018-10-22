package com.devz.app;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.develouz.lib.Helper;
import com.develouz.lib.DevelouzMenu;
import com.develouz.view.Layout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {
    private DevelouzMenu menu;
    private Toolbar toolbar;
    private FrameLayout frame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        frame = findViewById(R.id.frame);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toggle);
        drawer.addDrawerListener(this);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        menu = new DevelouzMenu(this, navigationView.getMenu());
        menu.create();
        onDrawerClosed(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!menu.onBackPressed()) {
            menu.closeApp();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        menu.select(item);
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {}

    @Override
    public void onDrawerOpened(View drawerView) {}

    @Override
    public void onDrawerClosed(View drawerView) {
        toolbar.setSubtitle(menu.getSelected().getTitle());
        Layout layout = menu.getLayout();
        if (layout != null && !menu.equals()) {
            menu.equalize();
            frame.removeAllViews();
            frame.addView(layout);
        }
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        Helper.hideKeyboard(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        menu.destroy();
    }
}
