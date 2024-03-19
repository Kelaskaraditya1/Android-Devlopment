package com.starkindustries.sharedprefrance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.starkindustries.sharedprefrance.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        binding = DataBindingUtil.setContentView(MainActivity2.this,R.layout.activity_main2);
        SharedPreferences prefrences = getSharedPreferences("Data_Transfer",MODE_PRIVATE);
        binding.name.setText(prefrences.getString("name","Rocky"));
        binding.studentId.setText(prefrences.getString("Student_id","Enter Your Student-id"));
        binding.department.setText(prefrences.getString("Department","Boxing"));
        binding.year.setText(prefrences.getString("Year","Third-Year"));
        binding.returnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(inext);
            }
        });
    }
}