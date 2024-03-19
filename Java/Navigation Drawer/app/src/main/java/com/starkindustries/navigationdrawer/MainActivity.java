package com.starkindustries.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.starkindustries.navigationdrawer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggler = new ActionBarDrawerToggle(MainActivity.this,binding.drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        binding.drawerLayout.addDrawerListener(toggler);
        toggler.syncState();
//        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id=item.getItemId();
//                if(id==R.id.create) {
//                    AppCompatTextView text = findViewById(R.id.text);
//                    text.setTextSize(35);
//                    text.setText(" I am Batman");
//                }
//                else if(id==R.id.update)
//                    Toast.makeText(MainActivity.this, "Folder Updated", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(MainActivity.this, "Folder Deleted", Toast.LENGTH_SHORT).show();
//                binding.drawerLayout.closeDrawer(GravityCompat.START);
//                return true;
//            }
//        });
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.create)
                    Toast.makeText(MainActivity.this, "Folder Created", Toast.LENGTH_SHORT).show();
                else if(id==R.id.update)
                    Toast.makeText(MainActivity.this, "Folder Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Folder Deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}