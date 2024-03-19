package com.starkindustries.toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.starkindustries.toolbar.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("My Tool-bar");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(MainActivity.this).inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id= item.getItemId();
        if(item_id==R.id.add_file)
            Toast.makeText(this, "File Added Sucessfully", Toast.LENGTH_SHORT).show();
        else if(item_id==R.id.camera)
            Toast.makeText(this, "Opening Camera", Toast.LENGTH_SHORT).show();
        else if(item_id==R.id.battery_saver)
            Toast.makeText(this, "Battery Low , Turning On Battery Saver", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}