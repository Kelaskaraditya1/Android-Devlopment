package com.starkindustries.room_library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.starkindustries.room_library.RoomDB.DatabaseHandler;
import com.starkindustries.room_library.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    public DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        db=DatabaseHandler.getDB(MainActivity.this);
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.expensesDAO().insert_expense(new Expenses(binding.item.getText().toString().trim(),binding.price.getText().toString().trim()));
                db.expensesDAO().update_expense(new Expenses(1,"Sandeep","10000000000"));
            }
        });
    }
}