package com.starkindustries.ml_kit_demo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.starkindustries.ml_kit_demo.databinding.ActivityTypeScreenBinding;

public class TypeScreen extends AppCompatActivity {
    public ActivityTypeScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_type_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            binding= DataBindingUtil.setContentView(TypeScreen.this,R.layout.activity_type_screen);
            binding.count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inext = new Intent(TypeScreen.this,MainActivity.class);
                    startActivity(inext);
                }
            });
            return insets;
        });
    }
}