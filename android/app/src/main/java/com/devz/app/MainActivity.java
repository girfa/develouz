package com.devz.app;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.develouz.menu.DevelouzMenu;
import com.develouz.sdk.Helper;
import com.develouz.view.ViewDesign;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {
    private DevelouzMenu menu;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigation;
    private FrameLayout frame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        frame = findViewById(R.id.frame);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toggle);
        drawer.addDrawerListener(this);
        toggle.syncState();

        navigation = findViewById(R.id.nav_view);
        navigation.bringToFront();
        navigation.setNavigationItemSelectedListener(this);
        menu = new DevelouzMenu(this, navigation.getMenu());
        menu.setToolbar(toolbar);
        menu.setHeader(navigation.getHeaderView(0), R.id.nav_icon, R.id.nav_title, R.id.nav_subtitle);
        menu.create();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MenuItem selected = navigation.getCheckedItem();
        menu.select(selected == null ? menu.getFirst() : selected);
        onDrawerClosed(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!menu.onBackPressed()) {
            menu.closeApp();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        menu.select(item);
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
        if (menu.shouldDraw()) {
            ViewDesign view = menu.drawView();
            frame.removeAllViews();
            frame.addView(view);
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
