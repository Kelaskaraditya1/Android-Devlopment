package com.starkindustries.ml_kit_demo;
import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import com.starkindustries.ml_kit_demo.databinding.ActivityMainBinding;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public ImageLabeler imageLabeler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
            imageLabeler= ImageLabeling.getClient(new ImageLabelerOptions.Builder().setConfidenceThreshold(0.7f).build());

            binding.camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inext = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(inext,101);
                }
            });
            binding.gallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inext = new Intent(Intent.ACTION_PICK);
                    inext.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(inext,102);
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
            if(requestCode==101)
            {
                Bitmap bitmap_image = (Bitmap) data.getExtras().get("data");
                binding.image.setImageBitmap(bitmap_image);
            }
            if(requestCode==102)
            {

                binding.image.setImageURI(data.getData());
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),data.getData());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                imageprocess(bitmap);
            }
        }
    }
    public void imageprocess(Bitmap bitmap)
    {
        InputImage image = InputImage.fromBitmap(bitmap,0);
        imageLabeler.process(image).addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
            @Override
            public void onSuccess(List<ImageLabel> imageLabels) {
                if(imageLabels.size()>0)
                {
                    StringBuilder resultstring = new StringBuilder();
                    for(ImageLabel label: imageLabels)
                    {
                        resultstring.append(label.getText()).append(" : ").append(label.getConfidence()).append("\n");
                    }
                    binding.result.setText(resultstring.toString());
                }
                else {
                    binding.result.setText("Could not classify the image");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });
    }

}