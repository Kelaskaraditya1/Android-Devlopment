package com.starkindustries.binding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.starkindustries.binding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding=DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                Intent inext = new Intent(MainActivity.this,Second_Activity.class);
                inext.putExtra("name",binding.name.getText().toString().trim());
                inext.putExtra("password",binding.password.getText().toString().trim());
                inext.putExtra("Image",R.drawable.user_logo);
                startActivity(inext);
            }
        });
    }
}