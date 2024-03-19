package com.starkindustries.login_mechanism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.starkindustries.login_mechanism.databinding.ActivityLoginBinding;

public class Login_Activity extends AppCompatActivity {
ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding= DataBindingUtil.setContentView(Login_Activity.this,R.layout.activity_login);
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefrances = getSharedPreferences("Login_Mechanism",MODE_PRIVATE);
                SharedPreferences.Editor editor = prefrances.edit();
                editor.putBoolean("flag",true);
                editor.apply();
                Intent inext = new Intent(Login_Activity.this,Home_Activity.class);
                startActivity(inext);
            }
        });
    }
}