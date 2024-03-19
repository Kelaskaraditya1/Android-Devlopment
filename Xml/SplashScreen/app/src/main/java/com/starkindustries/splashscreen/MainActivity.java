package com.starkindustries.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;

import com.starkindustries.splashscreen.databinding.ActivityMainBinding;

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
                Intent inext = new Intent(getApplicationContext(), Second_Activity.class);
                Pair pairs [] = new Pair[2];
                pairs[0] = new Pair<View,String>(binding.logo,"app_logo");
                pairs[1] = new Pair<View,String>(binding.name,"app_name");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(inext,options.toBundle());
            }
        },2000);
    }
}