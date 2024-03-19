package com.starkindustries.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.starkindustries.fragments.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction1 = manager.beginTransaction();
//        transaction1.add(R.id.layout_up,new A_Fragment());
//        transaction1.commit();
//        FragmentTransaction transaction2 = manager.beginTransaction();
//        transaction2.add(R.id.layout_down,new B_Fragment());
//        transaction2.commit();
        FragmentManager manager = getSupportFragmentManager();
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                transaction1.add(R.id.frame_layout,new A_Fragment());
                transaction1.commit();
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction2 = manager.beginTransaction();
                transaction2.add(R.id.frame_layout,new B_Fragment());
                transaction2.commit();
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction3 = manager.beginTransaction();
                transaction3.add(R.id.frame_layout,new C_Fragment());
                transaction3.commit();
            }
        });
    }
}