package com.starkindustries.firebase_authentication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputBinding;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.starkindustries.firebase_authentication.databinding.ActivityMainBinding;

import java.util.HashMap;
public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    public FirebaseAuth firebaseAuth;
    public FirebaseUser user;
    public FirebaseFirestore fstore;
    public static final int RIGHT=2;
    public  boolean passed;
    public static final String NAME="name";
    public static final String EMAIL="email";
    public static final String PASSWORD="password";
    public static final String COLLECTION_NAME="My Collection";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            Intent inext = new Intent(MainActivity.this,DashBoard.class);
            startActivity(inext);
            finish();
        }
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
        binding.confirmPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(event.getRawX()>(binding.confirmPass.getRight()-binding.confirmPass.getCompoundDrawables()[RIGHT].getBounds().width()))
                    {
                        int selection = binding.confirmPass.getSelectionEnd();
                        if(passed)
                        {
                            binding.confirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            binding.confirmPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off,0);
                            passed=false;
                        }
                        else
                        {
                            binding.confirmPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_on_two,0);
                            binding.confirmPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passed=true;
                        }
                        binding.confirmPass.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(v.getWindowToken(),0);
                binding.progressBar.setAlpha(1f);
                if(TextUtils.isEmpty(binding.name.getText().toString().trim()))
                {
                    binding.name.setError("Enter Proper Name");
                    return ;
                }
                if(TextUtils.isEmpty(binding.email.getText().toString().trim())) {
                    binding.email.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(binding.password.getText().toString().trim())) {
                    binding.password.setError("Password is Required");
                    return;
                }
                if(TextUtils.isEmpty(binding.confirmPass.getText().toString().trim()))
                {
                    binding.confirmPass.setError("Enter Correct Password");
                    return ;
                }
                if(!(binding.password.getText().toString().trim().equals(binding.confirmPass.getText().toString().trim())))
                {
                    binding.password.setError("Password and Confirm password didnot matched");
                    binding.confirmPass.setError("Password and Confirm password didnot matched");
                    return;
                }
                if((binding.password.getText().toString().trim().length()<8)&&(binding.confirmPass.getText().toString().trim().length()<8)) {
                    binding.password.setError("Password length is less than 8 Charecter");
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(binding.email.getText().toString().trim(),binding.password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            user=firebaseAuth.getCurrentUser();
                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(MainActivity.this, "Verification Email Send Sucessfully", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("failure",e.getMessage().toString().trim());
                                }
                            });
                            binding.progressBar.setVisibility(View.VISIBLE);
                            Toast.makeText(MainActivity.this, "User Created Sucessfully", Toast.LENGTH_SHORT).show();
                            String user_id=firebaseAuth.getCurrentUser().getUid();
                            DocumentReference docref = fstore.collection(COLLECTION_NAME).document(user_id);
                            HashMap<String,Object> map = new HashMap<String,Object>();
                            map.put(NAME,binding.name.getText().toString().trim());
                            map.put(EMAIL,binding.email.getText().toString().trim());
                            map.put(PASSWORD,binding.password.getText().toString().trim());
                            docref.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("Data","Name: "+binding.name.getText().toString().trim()+" Added Sucessfully");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("Fail","Fail: "+e.toString());
                                }
                            });
                            Intent inext = new Intent(MainActivity.this,DashBoard.class);
                            startActivity(inext);
                        }
                        else
                            Toast.makeText(MainActivity.this, "Error: "+task.getException().getMessage().toString().trim(), Toast.LENGTH_SHORT).show();
                        binding.progressBar.setVisibility(View.GONE);
                    }
                });

            }
        });
    }
}