package com.example.pp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pp.R;

public class demande_conge extends AppCompatActivity {

    CardView cardView,cardView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_conge);


        cardView = findViewById(R.id.cardView1);
        cardView1 = findViewById(R.id.cardView2);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),demande_conge.class);
                startActivity(intent);
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),demande_absance.class);
                startActivity(intent);
            }
        });


    }
}