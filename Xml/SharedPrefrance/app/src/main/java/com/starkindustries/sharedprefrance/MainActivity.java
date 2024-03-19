package com.starkindustries.sharedprefrance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.starkindustries.sharedprefrance.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(MainActivity.this,MainActivity2.class);
                SharedPreferences prefrances = getSharedPreferences("Data_Transfer",MODE_PRIVATE);
                SharedPreferences.Editor editor = prefrances.edit();
                editor.putString("name",binding.name.getText().toString().trim());
                editor.putString("Student_id",binding.studentId.getText().toString().trim());
                editor.putString("Department",binding.department.getText().toString().trim());
                editor.putString("Year",binding.year.getText().toString().trim());
                editor.apply();
                startActivity(inext);
            }
        });
//        SharedPreferences prefrances = getSharedPreferences("Data_transfer",MODE_PRIVATE)
    }
}