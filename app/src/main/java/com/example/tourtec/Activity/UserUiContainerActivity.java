package com.example.tourtec.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.tourtec.Fragment.HistoryFragment;
import com.example.tourtec.Fragment.HomeFragment;
import com.example.tourtec.Fragment.NearbyPlaceFragment;
import com.example.tourtec.Fragment.ProfileFragment;
import com.example.tourtec.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserUiContainerActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavView;
    private Toolbar dToolbar;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ui_container);

        initView();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new HomeFragment())
                .commit();

        initBottomNavigation();

    }

    private void initBottomNavigation() {
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.homeFm:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.nearPlaceFm:
                        selectedFragment = new NearbyPlaceFragment();
                        break;
                    case R.id.historyFm:
                        selectedFragment = new HistoryFragment();
                        break;
                    case R.id.profileFm:
                        selectedFragment = new ProfileFragment();
                        break;

                    default:
                        break;
                }
                if (selectedFragment != null) {
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragmentContainer, selectedFragment)
                            .commit();
                }
                return true;
            }
        });
    }

    private void initView() {
        bottomNavView = findViewById(R.id.bottomNavigationView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dToolbar = findViewById(R.id.toolbar);
        }
        frameLayout = findViewById(R.id.fragmentContainer);
    }
}