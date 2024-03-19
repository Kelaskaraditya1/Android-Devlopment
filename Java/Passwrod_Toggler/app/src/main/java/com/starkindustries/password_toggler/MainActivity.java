package com.starkindustries.password_toggler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.starkindustries.password_toggler.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
ConstraintLayout layout;
boolean pass_ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        binding.pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int right=2;
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(event.getRawX()>=(binding.pass.getRight()-binding.pass.getCompoundDrawables()[right].getBounds().width()))
                    {
                        int selection=binding.pass.getSelectionEnd();
                        if(pass_ed)
                        {
                            binding.pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.eye_close,0);
                            binding.pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            pass_ed=false;
                        }
                        else
                        {
                            binding.pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.eye_open,0);
                            binding.pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            pass_ed=true;
                        }
                        binding.pass.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
        layout=findViewById(R.id.layout);
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                Snackbar snackbar = Snackbar.make(layout, "Snackbar-Clicked", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Toast", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "I am Ironaman", Toast.LENGTH_SHORT).show();
                            }
                        });
                snackbar.show();
            }

        });
    }
}
