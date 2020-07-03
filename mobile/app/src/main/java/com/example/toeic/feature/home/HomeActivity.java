package com.example.toeic.feature.home;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.toeic.R;
import com.example.toeic.feature.exam.exam_list.ExamFragment;
import com.example.toeic.feature.practice.part_list.PartListFragment;
import com.example.toeic.feature.search_word.SearchWordFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.menu_icon);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    menuItem.setChecked(true);
                    drawer.closeDrawers();
                    openFragment(menuItem.getItemId());
                    return true;
                });
    }

    private void openFragment(int menuId) {
        switch (menuId) {
            case R.id.nav_home:
                changePartFragment();
                break;
            case R.id.nav_gallery:
                changeExamsFragment();
                break;
            case R.id.nav_slideshow:
                changeSearchWordFragment();
                break;
        }
    }

    private void changePartFragment() {
        changeFragment(new PartListFragment());
    }

    private void changeExamsFragment() {
        changeFragment(new ExamFragment());
    }

    private void changeSearchWordFragment() {
        changeFragment(new SearchWordFragment());
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_fragment, fragment);
        fragmentTransaction.commit();
    }

}
