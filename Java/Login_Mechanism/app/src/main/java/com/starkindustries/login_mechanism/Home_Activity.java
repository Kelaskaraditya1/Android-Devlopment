package com.starkindustries.login_mechanism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.starkindustries.login_mechanism.databinding.ActivityHomeBinding;

public class Home_Activity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        binding= DataBindingUtil.setContentView(Home_Activity.this,R.layout.activity_home);
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(Home_Activity.this, Login_Activity.class);
                SharedPreferences preferences = getSharedPreferences("Login_Mechanism",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("flag",false);
                editor.apply();
                startActivity(inext);
            }
        });
    }
}