package com.example.tryoutpas_23_3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Tampilkan fragment default saat pertama kali
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new FragmentProfile())
                .commit();

        // Handle navigasi bottom nav
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_profile) {
                fragment = new FragmentProfile();
            } else if (itemId == R.id.nav_laliga) {
                fragment = new FragmentLaLiga();
            } else if (itemId == R.id.nav_premiere) {
                fragment = new FragmentPremiere();
            }

            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, fragment)
                        .commit();
                return true;
            }
            return false;
        });
    }
}
