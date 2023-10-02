package com.example.exampractice;

import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.widget.FrameLayout;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout main_frame;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            setFragment(new CategoryFragment());
                            return true;

                        case R.id.nav_leaderboard:
                            setFragment(new LeaderboardFragment());
                            return true;

                        case R.id.nav_account:
                            setFragment(new AccountFragment());
                            return true;
                    }
                    return false;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.open_navigation_drawer,   // Provide the resource ID for "Open navigation drawer"
                R.string.close_navigation_drawer   // Provide the resource ID for "Close Navigation drawer"
        );

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        main_frame = findViewById(R.id.main_frame);
        setFragment(new CategoryFragment()); // Load the initial fragment
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
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
         if (id == R.id.nav_gallery) {
            // Handle nav_gallery
        } else if (id == R.id.nav_slideshow) {
            // Handle nav_slideshow
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(main_frame.getId(), fragment);
        transaction.commit();
    }
}
