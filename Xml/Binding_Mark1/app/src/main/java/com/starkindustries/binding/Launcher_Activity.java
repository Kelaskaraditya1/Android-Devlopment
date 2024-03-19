package com.starkindustries.binding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;

import com.starkindustries.binding.databinding.ActivityLauncherBinding;

public class Launcher_Activity extends AppCompatActivity {
    ActivityLauncherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        binding= DataBindingUtil.setContentView(Launcher_Activity.this,R.layout.activity_launcher);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent inext = new Intent(Launcher_Activity.this,MainActivity.class);
                Pair pairs [] = new Pair[2];
                pairs[0] = new Pair<View,String>(binding.loogoId,"logo");
                pairs[1] = new Pair<View,String>(binding.nameId,"app_name");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Launcher_Activity.this,pairs);
                startActivity(inext,options.toBundle());
            }
        },2000);
    }
}