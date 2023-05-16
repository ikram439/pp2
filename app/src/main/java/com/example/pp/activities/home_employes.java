package com.example.pp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.pp.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class home_employes extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    HomeEmplyesFragment homeFragment = new HomeEmplyesFragment();
    DonneeEmployesFragment donneFragment = new DonneeEmployesFragment();
    MessageEmployesFragment messageFragment = new MessageEmployesFragment();
    ProfilEmployesFragment profilFragment = new ProfilEmployesFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_employes);

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
                        Intent intent=new Intent(home_employes.this,login_employees.class);
                        startActivity(intent);
                }

                return false;
            }
        });

    }
}