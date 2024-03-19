package com.starkindustries.databases_mark2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.starkindustries.databases_mark2.databinding.ActivityRegisterScreenBinding;

public class Register_Screen extends AppCompatActivity {
    public ActivityRegisterScreenBinding binding;
    public DatabaseHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_screen);
        binding= DataBindingUtil.setContentView(Register_Screen.this,R.layout.activity_register_screen);
        handler=new DatabaseHandler(Register_Screen.this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            handler.get_user_count();
            binding.register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = new User();
                    user.setSid(binding.sid.getText().toString().trim());
                    user.setName(binding.name.getText().toString().trim());
                    user.setUsername(binding.username.getText().toString().trim());
                    user.setPhone_no(binding.phoneNo.getText().toString().trim());
                    user.setPassword(binding.password.getText().toString().trim());
                    handler.create_user(user);
                    Intent inext = new Intent(Register_Screen.this, DashBoard.class);
                    startActivity(inext);
                    InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(v.getWindowToken(),0);
                }
            });
            return insets;
        });
    }
}