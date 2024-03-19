package com.starkindustries.tablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.starkindustries.tablayout.Adapter.ViewPagerAdappter;
import com.starkindustries.tablayout.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        ViewPagerAdappter adappter = new ViewPagerAdappter(getSupportFragmentManager());
        binding.viewpager.setAdapter(adappter);
        binding.tablayout.setupWithViewPager(binding.viewpager);
    }
}