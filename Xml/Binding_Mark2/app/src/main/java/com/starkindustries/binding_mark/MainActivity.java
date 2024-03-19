package com.starkindustries.binding_mark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.starkindustries.binding_mark.Model.Bio;
//import com.starkindustries.binding_mark.databinding.ActivityMainBinding;
import com.starkindustries.binding_marki2.R;
import com.starkindustries.binding_marki2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bio bio = new Bio();
                bio.name=binding.getName.getText().toString().trim();
                bio.hobby=binding.getHobby.getText().toString().trim();
                binding.setBio(bio);
            }
        });
    }
}