package com.example.pp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.pp.R;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativebtn1,relativebtn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativebtn1 = findViewById(R.id.relativebtn1);
        relativebtn2 = findViewById(R.id.relativebtn2);

        relativebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,login_admin.class);
                startActivity(intent);
            }
        });

        relativebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,login_employees.class);
                startActivity(intent);
            }
        });
    }
}