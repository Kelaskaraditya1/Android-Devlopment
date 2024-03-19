package com.starkindustries.side_drawer_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.starkindustries.side_drawer_navigation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    public ActionBarDrawerToggle toggler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        try
        {
         getSupportActionBar().setTitle("Toolbar");
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        toggler = new ActionBarDrawerToggle(MainActivity.this,binding.drawerlayout,binding.toolbar,R.string.open,R.string.close);
        binding.drawerlayout.addDrawerListener(toggler);
        toggler.syncState();
        binding.navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.userprofile)
                {
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction1 = manager.beginTransaction();
                    transaction1.add(R.id.frame,new User_profile());
                    transaction1.commit();
                    binding.drawerlayout.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(MainActivity.this).inflate(R.menu.menu_items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.userprofile)
            Toast.makeText(this, "Opening User_Profile", Toast.LENGTH_SHORT).show();
        else if(id==R.id.analytics)
            Toast.makeText(this, "Opening Analytics", Toast.LENGTH_SHORT).show();
        else if(id==R.id.groups)
            Toast.makeText(this, "Opening group Chat", Toast.LENGTH_SHORT).show();
        else if(id==R.id.logout)
            Toast.makeText(this, "Opening Settings", Toast.LENGTH_SHORT).show();
        else if(id==R.id.logout)
            Toast.makeText(this, "Loging out", Toast.LENGTH_SHORT).show();
        else {
            AlertDialog.Builder exit = new AlertDialog.Builder(MainActivity.this);
            exit.setTitle("Exit");
            exit.setMessage("Are you sure,you want to quit");
            exit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
               MainActivity.super.onBackPressed();
                }
            });
            exit.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            exit.setCancelable(false);
            exit.show();
        }
        return super.onOptionsItemSelected(item);
    }
}