package com.starkindustries.login_mechanism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;

import com.starkindustries.login_mechanism.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences prefrances = getSharedPreferences("Login_Mechanism",MODE_PRIVATE);
//                SharedPreferences.Editor editor = prefrances.edit();
                Boolean status= prefrances.getBoolean("flag",false);
                if(status)
                {
                    Intent inext = new Intent(MainActivity.this,Home_Activity.class);
                    startActivity(inext);
                }
                else
                {
                    Intent inext = new Intent(MainActivity.this,Login_Activity.class);
                    startActivity(inext);
                }
                Pair pairs [] = new Pair[2];
                pairs[0] = new Pair<View,String>(binding.logo,"app_logo");
                pairs[1] = new Pair<View,String>(binding.name,"app_name");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                finish();
            }
        },1000);
    }
}