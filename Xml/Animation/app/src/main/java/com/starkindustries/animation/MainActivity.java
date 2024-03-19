package com.starkindustries.animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.starkindustries.animation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        binding.translation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation translation_animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.translation_animation);
                binding.image.startAnimation(translation_animation);
            }
        });
        binding.scaling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation scaling_animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scaling_animation);
                binding.image.startAnimation(scaling_animation);
            }
        });
        binding.rotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation rotation_animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotation_animation);
                binding.image.startAnimation(rotation_animation);
            }
        });
        binding.alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation alpha_animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha_animation);
                binding.image.startAnimation(alpha_animation);
            }
        });
    }
}