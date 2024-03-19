package com.starkindustries.side_drawer_navigation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User_profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User_profile extends Fragment {
    public AppCompatImageView user_profile;
    public AppCompatEditText username,password;
    public AppCompatButton camera,gallery;
    public static final int RIGHT=2;
    public static boolean passed;
    public static final int CAPTURE_IMAGE_CODE=100;
    public static final int GALLERY_CODE=101;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public User_profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment User_profile.
     */
    // TODO: Rename and change types and number of parameters
    public static User_profile newInstance(String param1, String param2) {
        User_profile fragment = new User_profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        user_profile=view.findViewById(R.id.user_profile);
        username=view.findViewById(R.id.username);
        password=view.findViewById(R.id.password);
        camera=view.findViewById(R.id.camera);
        gallery=view.findViewById(R.id.gallery);
        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(event.getRawX()>=(password.getRight()-password.getCompoundDrawables()[RIGHT].getBounds().width()))
                    {
                        int selection=password.getSelectionEnd();
                        if(passed)
                        {
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off,0);
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passed=false;
                        }
                        else
                        {
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_on,0);
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passed=true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera,CAPTURE_IMAGE_CODE);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,GALLERY_CODE);
            }
        });
        Log.d("details","Username: "+username.getText().toString().trim()+" "+" Paddword: "+password.getText().toString().trim());
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CAPTURE_IMAGE_CODE) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            user_profile.setImageBitmap(image);
        }
        if(requestCode==GALLERY_CODE)
        {
            user_profile.setImageURI(data.getData());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}