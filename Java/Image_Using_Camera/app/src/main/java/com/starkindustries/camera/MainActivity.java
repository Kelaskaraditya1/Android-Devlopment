package com.starkindustries.camera;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.starkindustries.camera.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    public static final int CAPTURE_REQ_CODE=100;
    public static final int GALLERY_REQ_CODE=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        binding.capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(camera,CAPTURE_REQ_CODE);
            }
        });
        Intent gallery = new Intent(Intent.ACTION_PICK);
        gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        binding.gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(gallery,GALLERY_REQ_CODE);
            }
        });
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==CAPTURE_REQ_CODE)
            {
                Bitmap img = (Bitmap) data.getExtras().get("data");
                binding.image.setImageBitmap(img);
            }
            if(requestCode==GALLERY_REQ_CODE)
                binding.image.setImageURI(data.getData());
        }
    }
}