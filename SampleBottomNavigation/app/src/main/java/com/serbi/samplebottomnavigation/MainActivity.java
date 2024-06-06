package com.serbi.samplebottomnavigation;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    ProfileFragment profileFragment = new ProfileFragment();
    ArchiveFragment archiveFragment = new ArchiveFragment();
    SettingsFragment settingsFragment = new SettingsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView = findViewById(R.id.bnv_main_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.bnv_frame_layout, profileFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bnv_profile) {
                getSupportFragmentManager().beginTransaction().replace(R.id.bnv_frame_layout, profileFragment).commit();
                return true;
            } else if (item.getItemId() == R.id.bnv_archive) {
                getSupportFragmentManager().beginTransaction().replace(R.id.bnv_frame_layout, archiveFragment).commit();
                return true;
            } else if (item.getItemId() == R.id.bnv_settings) {
                getSupportFragmentManager().beginTransaction().replace(R.id.bnv_frame_layout, settingsFragment).commit();
                return true;
            }
            return false;
        });
    }
}