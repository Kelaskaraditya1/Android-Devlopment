package com.starkindustries.binding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.starkindustries.binding.databinding.ActivitySecondBinding;

public class Second_Activity extends AppCompatActivity {
    ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        binding= DataBindingUtil.setContentView(Second_Activity.this,R.layout.activity_second);
        Intent iprev =getIntent();
        binding.name.setText(iprev.getStringExtra("name"));
        binding.password.setText(iprev.getStringExtra("password"));
        binding.logo.setImageResource(iprev.getIntExtra("Image",0));
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(view.getWindowToken(),0);
                Intent inext = new Intent(Second_Activity.this,MainActivity.class);
                startActivity(inext);
            }
        });
    }
}