package com.appdev.jphil.practicesetlistgenerator;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.appdev.jphil.practicesetlistgenerator.fragments.InstrumentsFragment;
import com.appdev.jphil.practicesetlistgenerator.fragments.PracticeFragment;
import com.appdev.jphil.practicesetlistgenerator.fragments.SongsFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        switch (item.getItemId()){
                            case R.id.practice:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.content_frame, new PracticeFragment())
                                        .commit();
                                break;
                            case R.id.songs:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.content_frame, new SongsFragment())
                                        .commit();
                                break;
                            case R.id.instruments:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.content_frame, new InstrumentsFragment())
                                        .commit();
                                break;
                        }

                        return true;
                    }
                }
        );


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new PracticeFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
