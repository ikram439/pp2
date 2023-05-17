package com.example.pp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pp.R;
import com.example.pp.db.LeaveDatabaseHelper;

public class demande_conge extends AppCompatActivity {

    EditText startDate,endDate,leaveType;
    Button sendLeaveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_conge);
        startDate=findViewById(R.id.startDate);
        endDate=findViewById(R.id.endDate);
        leaveType=findViewById(R.id.leaveType);
        LeaveDatabaseHelper db=new LeaveDatabaseHelper(this);
        sendLeaveBtn=findViewById(R.id.sendLeaveBtn);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int employeeId = preferences.getInt("EMPLOYEE_ID", -1);
        sendLeaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.insertLeave(employeeId,leaveType.getText().toString(),startDate.getText().toString(),endDate.getText().toString())){
                    Toast.makeText(demande_conge.this, "true", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(demande_conge.this, "false", Toast.LENGTH_SHORT).show();

                }
            }
        });






    }
}