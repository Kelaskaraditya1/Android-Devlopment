package com.starkindustries.databases_mark2;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.starkindustries.databases_mark2.databinding.ActivityLoginScreenBinding;

public class Login_Screen extends AppCompatActivity {
    public ActivityLoginScreenBinding binding;
    public DatabaseHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);
        binding= DataBindingUtil.setContentView(Login_Screen.this,R.layout.activity_login_screen);
        handler=new DatabaseHandler(Login_Screen.this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            handler.get_user_count();
            binding.signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inext = new Intent(Login_Screen.this, Register_Screen.class);
                    startActivity(inext);
                }
            });
            binding.login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean status = handler.login_using_username_password(binding.username.getText().toString().trim(),binding.password.getText().toString().trim());
                    if(status) {
                        Intent inext = new Intent(Login_Screen.this, DashBoard.class);
                        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        manager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        startActivity(inext);
                    }
                    else
                        Toast.makeText(Login_Screen.this, "Either username or password is incorrect ", Toast.LENGTH_SHORT).show();
                }
            });
            return insets;
        });
        binding.dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inext = new Intent(Login_Screen.this, DashBoard.class);
                startActivity(inext);
            }
        });
    }
}