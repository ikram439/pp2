package com.example.pp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class home_employes extends AppCompatActivity {
ImageButton home,donnee,message,profil,deconnexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_employes);

        home = findViewById(R.id.home);
        donnee = findViewById(R.id.donnee);
        message = findViewById(R.id.message);
        profil = findViewById(R.id.profil);
        deconnexion = findViewById(R.id.deconnexion);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home_employes.this,acueil_employer.class);
                startActivity(intent);
            }
        });


        donnee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home_employes.this,donnee_employes.class);
                startActivity(intent);
            }
        });



        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home_employes.this,message_employes.class);
                startActivity(intent);
            }
        });



        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home_employes.this,profil_employes.class);
                startActivity(intent);
            }
        });


        deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home_employes.this,login_employees.class);
                startActivity(intent);
            }
        });
    }
}