package com.example.pp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pp.MyBaseDonnee;
import com.example.pp.R;
import com.example.pp.db.LeaveDatabaseHelper;

public class inscription_employes extends AppCompatActivity {
    EditText username, password, confirmpass, email;
    Button btn;
    LeaveDatabaseHelper db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_employes);
        db = new LeaveDatabaseHelper(this);
        username=findViewById(R.id.login);
        password=findViewById(R.id.password);
        confirmpass=findViewById(R.id.Confpassword);
        email=findViewById(R.id.mail);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(view -> {
            String nom = username.getText().toString();
            String pass = password.getText().toString();
            String con = confirmpass.getText().toString();
            String mail = email.getText().toString();

            if(nom.equals("")||pass.equals("")|| con.equals("")||mail.equals("")){
                Toast.makeText(inscription_employes.this,"Plaese enter all the fields",Toast.LENGTH_SHORT).show();
            }else{
                /*if (pass.equals(con)){
                    boolean check = db.checkusernameEmployees(nom);
                    if(!check){
                        boolean insert = db.insertEmployees(nom,pass,mail);
                        if(insert){
                            Toast.makeText(inscription_employes.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(inscription_employes.this,login_employees.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(inscription_employes.this, "Registered failed", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(inscription_employes.this, "user already exist ! please sign in ", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(inscription_employes.this, "Password not matching", Toast.LENGTH_SHORT).show();

                }*/

                if(db.insertEmployee(nom,mail,pass)>0){
                    Toast.makeText(inscription_employes.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(inscription_employes.this,login_employees.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(inscription_employes.this, "Registered failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}