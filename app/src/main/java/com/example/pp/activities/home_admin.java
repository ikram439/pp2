package com.example.pp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.example.pp.R;

public class home_admin extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeAdminFragment homeFragment = new HomeAdminFragment();
    DonneAdminFragment donneFragment = new DonneAdminFragment();
    MessageFragmentAdmin messageFragment = new MessageFragmentAdmin();
    ProfilFragmentAdmin profilFragment = new ProfilFragmentAdmin();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.Message);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.donnee:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,donneFragment).commit();
                        return true;
                    case R.id.Message:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,messageFragment).commit();
                        return true;
                    case R.id.Profil:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profilFragment).commit();
                        return true;

                    case R.id.Deconexion:
                        Intent intent=new Intent(home_admin.this,login_admin.class);
                        startActivity(intent);
                }

                return false;
            }
        });

    }
}