package com.example.finalproject;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String EXTRA_CARDRARITY = "cardrarity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Menu m = navigationView.getMenu();

        MenuItem legendaryItem = m.findItem(R.id.nav_legendary);
        SpannableString sLegendary = new SpannableString(legendaryItem.getTitle());
        sLegendary.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorLegendary)), 0, sLegendary.length(), 0);
        legendaryItem.setTitle(sLegendary);

        MenuItem epicItem = m.findItem(R.id.nav_epic);
        SpannableString sEpic = new SpannableString(epicItem.getTitle());
        sEpic.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorEpic)), 0, sEpic.length(), 0);
        epicItem.setTitle(sEpic);

        MenuItem rareItem = m.findItem(R.id.nav_rare);
        SpannableString sRare = new SpannableString(rareItem.getTitle());
        sRare.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorRare)), 0, sRare.length(), 0);
        rareItem.setTitle(sRare);

        MenuItem commonItem = m.findItem(R.id.nav_common);
        SpannableString sCommon = new SpannableString(commonItem.getTitle());
        sCommon.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorCommon)), 0, sCommon.length(), 0);
        commonItem.setTitle(sCommon);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        }

        if (id == R.id.nav_common) {
            fragment = new CardsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_CARDRARITY,"Common");
            fragment.setArguments(bundle);
        }

        if (id == R.id.nav_rare) {
            fragment = new CardsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_CARDRARITY,"Rare");
            fragment.setArguments(bundle);
        }

        if (id == R.id.nav_epic) {
            fragment = new CardsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_CARDRARITY,"Epic");
            fragment.setArguments(bundle);
        }

        if (id == R.id.nav_legendary) {
            fragment = new CardsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_CARDRARITY,"Legendary");
            fragment.setArguments(bundle);
        }

        FragmentManager fm = getSupportFragmentManager();
        if (fragment != null) {
            fm.beginTransaction()
                    .replace(R.id.constraintlayout_main_container, fragment)
                    .commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
