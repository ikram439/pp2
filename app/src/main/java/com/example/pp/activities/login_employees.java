package com.example.pp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pp.MyBaseDonnee;
import com.example.pp.R;

public class login_employees extends AppCompatActivity {
    TextView signin;
    EditText name,password;
    Button btn1 ;
    MyBaseDonnee db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_employees);
        db = new MyBaseDonnee(this);
        name=findViewById(R.id.login);
        password=findViewById(R.id.password);
        btn1 = findViewById(R.id.button);
        signin=findViewById(R.id.signin);
        try{
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String log = name.getText().toString();
                    String pass = password.getText().toString();
                    if(log.equals("")||pass.equals(""))
                        Toast.makeText(login_employees.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                    else{
                        Boolean checkuserpass=db.checkusernamepasswordEmployees(log,pass);
                        if (checkuserpass==true){
                            Toast.makeText(login_employees.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),home_employes.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(login_employees.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }catch (Exception e){
            e.getMessage();
        }
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login_employees.this,inscription_employes.class);
                startActivity(intent);

            }

        });
    }
}