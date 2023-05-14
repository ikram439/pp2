package com.example.pp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login_admin extends AppCompatActivity {
    TextView signin;
    EditText name,password;
    Button btn1 ;
    MyBaseDonnee  db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
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
                        Toast.makeText(login_admin.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                    else{
                        Boolean checkuserpass=db.checkusernamepasswordAdmi(log,pass);
                        if (checkuserpass==true){
                            Toast.makeText(login_admin.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),home_admin.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(login_admin.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
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
                Intent intent=new Intent(login_admin.this,inscription_admin.class);
                startActivity(intent);

            }

        });

    }





}