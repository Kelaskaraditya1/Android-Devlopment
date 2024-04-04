package com.starkindustries.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.mlkit.vision.face.Face;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Example usage:
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mayur_bhaaiiiiii);
        FaceDetectionHelper.detectFaces(imageBitmap, new FaceDetectionHelper.FaceDetectionListener() {
            @Override
            public void onFacesDetected(List<Face> faces) {
                // Handle detected faces here
                Toast.makeText(MainActivity.this, "Number of faces detected: " + faces.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                // Handle error
                Toast.makeText(MainActivity.this, "Face detection error: " + error, Toast.LENGTH_SHORT).show();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}