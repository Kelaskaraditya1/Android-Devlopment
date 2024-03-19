package com.starkindustries.list_autocomp_spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.starkindustries.list_autocomp_spinner.databinding.ActivitySecondBinding;

public class Second_Activity extends AppCompatActivity {
    ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        binding= DataBindingUtil.setContentView(Second_Activity.this,R.layout.activity_second);
//        Intent iprev = getIntent();
        Bundle bundle = getIntent().getExtras();
        binding.showDepartment.setText(bundle.getString("department"));
    }
}