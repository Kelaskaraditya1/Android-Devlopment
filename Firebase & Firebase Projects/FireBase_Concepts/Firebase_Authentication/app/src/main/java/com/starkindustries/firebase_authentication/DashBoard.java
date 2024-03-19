package com.starkindustries.firebase_authentication;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.starkindustries.firebase_authentication.databinding.ActivityDashBoardBinding;
public class DashBoard extends AppCompatActivity {
    public ActivityDashBoardBinding binding;
    public FirebaseAuth auth;
    public FirebaseFirestore store;
    public FirebaseUser user;
    public String user_id;
    public static final int GALLERY_CODE=101;
    public StorageReference refrence,child_ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        binding= DataBindingUtil.setContentView(DashBoard.this,R.layout.activity_dash_board);
        auth=FirebaseAuth.getInstance();
        store=FirebaseFirestore.getInstance();
        user=auth.getCurrentUser();
        refrence=FirebaseStorage.getInstance().getReference();
        user_id=auth.getCurrentUser().getUid();
        StorageReference local_ref = FirebaseStorage.getInstance().getReference();
        StorageReference child_refrence = local_ref.child("Profile.jpg");
        child_refrence.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(binding.profileImage);
            }
        });
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent inext = new Intent(DashBoard.this, Loogin_Activity.class);
                startActivity(inext);
                finish();
            }
        });
        DocumentReference docref = store.collection(Keys.COLLECTION_NAME).document(user_id);
        docref.addSnapshotListener(DashBoard.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
             binding.name.setText("Name: "+value.getString(Keys.NAME));
             binding.email.setText("Email: "+value.getString(Keys.EMAIL));
             binding.password.setText("Password: "+value.getString(Keys.PASSWORD));
            }
        });
        if(!user.isEmailVerified())
        {
            binding.verifyText.setVisibility(View.VISIBLE);
            binding.verifyButton.setVisibility(View.VISIBLE);
            binding.verifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(DashBoard.this, "Email Veriified Sucessfully", Toast.LENGTH_SHORT).show();
                            binding.verifyText.setVisibility(View.INVISIBLE);
                            binding.verifyButton.setVisibility(View.INVISIBLE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("verification_failed",e.getMessage().toString().trim());
                        }
                    });
                }
            });
        }
        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK);
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,GALLERY_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK)
        {
            if(requestCode==GALLERY_CODE)
                Log.d("Ironman","I am Ironman");
        }
        Uri image_uri=data.getData();
        child_ref=refrence.child("Profile.jpg");
        child_ref.putFile(image_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(DashBoard.this, "Image Uploaded Sucessfully", Toast.LENGTH_SHORT).show();
                child_ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(binding.profileImage);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Error",e.getMessage().toString().trim());
            }
        });
        super.onActivityResult(requestCode, resultCode, data);
    }
}