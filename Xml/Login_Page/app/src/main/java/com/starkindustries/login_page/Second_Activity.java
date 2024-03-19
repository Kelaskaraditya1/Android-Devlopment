package com.starkindustries.login_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.starkindustries.login_page.databinding.ActivitySecondBinding;

public class Second_Activity extends AppCompatActivity {
    ActivitySecondBinding binding;
    ConstraintLayout layout;
    boolean pass_ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        binding= DataBindingUtil.setContentView(Second_Activity.this,R.layout.activity_second);
        layout=findViewById(R.id.layout_id);
        binding.pass.setOnTouchListener(new View.OnTouchListener() {
            final int right=2;
            public boolean onTouch(View view, MotionEvent event)
            {
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(event.getRawX()>=(binding.pass.getRight()-binding.pass.getCompoundDrawables()[right].getBounds().width()))
                    {
                        int selection = binding.pass.getSelectionEnd();
                        if(pass_ed)
                        {
                            binding.pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.icons8_visibility_24,0);
                            binding.pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            pass_ed=false;
                        }
                        else
                        {
                            binding.pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.icons8_visibility_50,0);
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

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(layout,"Snack-Bar", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Login", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(Second_Activity.this, "Login-Sucessfully", Toast.LENGTH_SHORT).show();
                            }
                        });
                snackbar.show();
            }
        });
    }
}