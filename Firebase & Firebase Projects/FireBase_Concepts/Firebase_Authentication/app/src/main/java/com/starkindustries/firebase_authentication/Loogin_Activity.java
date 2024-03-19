package com.starkindustries.firebase_authentication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.starkindustries.firebase_authentication.databinding.ActivityLooginBinding;
public class Loogin_Activity extends AppCompatActivity {
    public ActivityLooginBinding binding;
    public static final int RIGHT=2;
    public  boolean passed;
    public FirebaseAuth firebaseAuth;
    public FirebaseFirestore fstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loogin);
        binding= DataBindingUtil.setContentView(Loogin_Activity.this,R.layout.activity_loogin);
        firebaseAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inext = new Intent(Loogin_Activity.this,MainActivity.class);
                startActivity(inext);
            }
        });
        binding.password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(event.getRawX()>(binding.password.getRight()-binding.password.getCompoundDrawables()[RIGHT].getBounds().width()))
                    {
                        int selection = binding.password.getSelectionEnd();
                        if(passed)
                        {
                            binding.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            binding.password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off,0);
                            passed=false;
                        }
                        else
                        {
                            binding.password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_on_two,0);
                            binding.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passed=true;
                        }
                        binding.password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(binding.email.getText().toString().trim()))
                    binding.email.setError("Enter valid Email");
                if(TextUtils.isEmpty(binding.password.getText().toString().trim()))
                    binding.password.setError("Enter Valid Password");
                if(binding.password.getText().toString().trim().length()<8)
                    binding.password.setError("The Length of Password should be atleast 8 char");
                firebaseAuth.signInWithEmailAndPassword(binding.email.getText().toString().trim(),binding.password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent inext = new Intent(Loogin_Activity.this,DashBoard.class);
                            startActivity(inext);
                        }
                        else
                        {
                            Toast.makeText(Loogin_Activity.this, "Error: Invalid username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        binding.forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Loogin_Activity.this);
                dialog.setContentView(R.layout.reset_email_layout);
                dialog.setCancelable(false);
                TextInputEditText email=dialog.findViewById(R.id.email);
                AppCompatButton reset_password=dialog.findViewById(R.id.reset);
                reset_password.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firebaseAuth.sendPasswordResetEmail(email.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Loogin_Activity.this, "Email Send Sucessfully", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Loogin_Activity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
    }
}