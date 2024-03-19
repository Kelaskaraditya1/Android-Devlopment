package com.starkindustries.firebase_authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;

import com.starkindustries.firebase_authentication.databinding.ActivityLauncherActiivityBinding;

public class Launcher_Actiivity extends AppCompatActivity {
    public ActivityLauncherActiivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_actiivity);
        binding= DataBindingUtil.setContentView(Launcher_Actiivity.this,R.layout.activity_launcher_actiivity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent inext = new Intent(Launcher_Actiivity.this,Loogin_Activity.class);
                Pair pairs[] = new Pair[2];
                pairs[0] = new Pair<View,String>(binding.logo,"app_logo");
                pairs[1] = new Pair<View,String>(binding.name,"app_name");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Launcher_Actiivity.this,pairs);
                startActivity(inext,options.toBundle());
                finish();
            }
        },1000);
    }
}