package com.starkindustries.myapplication;
import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import java.util.List;
public class FaceDetectionHelper {
    private static final String TAG = "FaceDetectionHelper";

    public interface FaceDetectionListener {
        void onFacesDetected(List<Face> faces);
        void onError(String error);
    }

    public static void detectFaces(Bitmap bitmap, FaceDetectionListener listener) {
        InputImage image = InputImage.fromBitmap(bitmap, 0);
        FaceDetector detector = FaceDetection.getClient();

        detector.process(image)
                .addOnSuccessListener(new OnSuccessListener<List<Face>>() {
                    @Override
                    public void onSuccess(List<Face> faces) {
                        Log.d(TAG, "Number of faces detected: " + faces.size());
                        if (listener != null) {
                            listener.onFacesDetected(faces);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Face detection failed: " + e.getMessage());
                        if (listener != null) {
                            listener.onError(e.getMessage());
                        }
                    }
                });
    }
}
