package com.starkindustries.login_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.starkindustries.login_page.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.loading_animation);
        binding.loading.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent inext = new Intent(MainActivity.this, Second_Activity.class);
                Pair pairs[] = new Pair[2];
                pairs[0] = new Pair<View,String>(binding.logo,"app_logo");
                pairs[1] = new Pair<View,String>(binding.appname,"app_name");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(inext,options.toBundle());
            }
        },2000);
    }
}