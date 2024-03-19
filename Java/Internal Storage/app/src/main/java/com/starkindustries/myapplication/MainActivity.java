package com.starkindustries.myapplication;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.starkindustries.myapplication.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    public static final int GALLERY_REQ_CODE=101;
    public BitmapDrawable bitmapDrawable;
    public Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            binding.browse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inext = new Intent(Intent.ACTION_PICK);
                    inext.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(inext,GALLERY_REQ_CODE);
                }
            });
            binding.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bitmapDrawable=(BitmapDrawable) binding.imageView.getDrawable();
                    bitmap=bitmapDrawable.getBitmap();
                    FileOutputStream fileOutputStream = null;
                    File sdcard = Environment.getExternalStorageDirectory();
                    File directory = new File(sdcard.getAbsolutePath()+"/Download");
                    directory.mkdir();
                    String file_name=String.format("%d.jpg",System.currentTimeMillis());
                    File outfile = new File(directory,file_name);
                    Toast.makeText(MainActivity.this, "Image saved successfully", Toast.LENGTH_SHORT).show();
                    try {
                        fileOutputStream=new FileOutputStream(outfile);
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        Intent inext = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        getIntent().setData(Uri.fromFile(outfile));
                        sendBroadcast(inext);
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            });
            binding.createFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PackageManager.PERMISSION_GRANTED);

                    Intent inext = new Intent(Intent.ACTION_CREATE_DOCUMENT,MediaStore.Downloads.EXTERNAL_CONTENT_URI);
                    inext.setType("*/*");
                    startActivity(inext);
                }
            });
            binding.openFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inext = new Intent(Intent.ACTION_VIEW,MediaStore.Downloads.EXTERNAL_CONTENT_URI);
                    inext.setType("*/*");
                    startActivity(inext);
                }
            });


            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==GALLERY_REQ_CODE)
            {

                binding.imageView.setImageURI(data.getData());
            }
        }
    }
}