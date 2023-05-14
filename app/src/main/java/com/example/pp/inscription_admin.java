package com.example.pp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class inscription_admin extends AppCompatActivity {
    EditText username, password, confirmpass, email;
    Button btn;
    MyBaseDonnee db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_admin);
        db = new MyBaseDonnee(this);
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
                Toast.makeText(inscription_admin.this,"Plaese enter all the fields",Toast.LENGTH_SHORT).show();
            }else{
                if (pass.equals(con)){
                    boolean check = db.checkusernameAdmi(nom);
                    if(!check){
                        boolean insert = db.insertAdmin(nom,pass,mail);
                        if(insert){
                            Toast.makeText(inscription_admin.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(inscription_admin.this,login_admin.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(inscription_admin.this, "Registered failed", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(inscription_admin.this, "user already exist ! please sign in ", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(inscription_admin.this, "Password not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}