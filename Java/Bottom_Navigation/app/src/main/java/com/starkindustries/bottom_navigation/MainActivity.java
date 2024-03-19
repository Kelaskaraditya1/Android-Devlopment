package com.starkindustries.bottom_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.starkindustries.bottom_navigation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        binding.bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.create)
                    loadFragment(new AFragment(),false);
                else if(id==R.id.bluetooth)
                    loadFragment(new B_Fragment(),true);
                else if(id==R.id.bookmark)
                    loadFragment(new CFragment(),true);
                else if(id==R.id.fix)
                    loadFragment(new DFragment(),true);
                else loadFragment(new EFragment(),true);
                return true;
            }
        });
        binding.bottomnav.setSelectedItemId(R.id.create);

    }
    public void loadFragment(Fragment fragment,boolean flag)
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(flag)
            transaction.add(R.id.framelayout,fragment);
        else
            transaction.replace(R.id.framelayout,fragment);
        transaction.commit();
    }
}