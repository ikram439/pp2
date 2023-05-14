package com.example.pp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class donnee_admin extends AppCompatActivity {
    EditText matricule, name, type_m;
    Button insert, update, delete;
    MyBaseDonnee db;
    Employee emp,emp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donnee_admin);
        matricule = findViewById(R.id.matricule);
        name = findViewById(R.id.name);
        type_m = findViewById(R.id.type_m);
        insert = findViewById(R.id.binsert);
        update = findViewById(R.id.bupdate);
        delete = findViewById(R.id.bdelete);
        db = new MyBaseDonnee(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emp = new Employee(matricule.getText().toString(),name.getText().toString(),type_m.getText().toString());

                int x =  db.addEmployee(emp);
                if (x == 0){
                    Toast.makeText(donnee_admin.this, "echoue", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(donnee_admin.this, "valide", Toast.LENGTH_SHORT).show();
                }

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emp2 = new Employee(matricule.getText().toString(), name.getText().toString(),type_m.getText().toString());
                boolean result = db.updateEmployee(emp2);
                if (result) {
                    Toast.makeText(donnee_admin.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(donnee_admin.this, "Errore", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res = db.deleteEmployee(Integer.parseInt(type_m.getText().toString()));
                // Toast.makeText(MainActivity.this, ""+ res, Toast.LENGTH_SHORT).show();
                if (res == 0) {
                    Toast.makeText(donnee_admin.this, "Errore", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(donnee_admin.this, "Student Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }}