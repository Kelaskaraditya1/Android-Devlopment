package com.starkindustries.test_project;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.starkindustries.test_project.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    public AppCompatImageView profile_image;
    public AppCompatEditText username,password;
    public AppCompatButton submit,camera,gallery;
    public static final int Right = 2;
    public static final int CAMERA_REQ_CODE=101;
    public static final int GALLERY_REQ_CODE=102;
    public boolean passed;
    public static final String CHANNEL_ID="Notification_channel_id";
    public static final int INTEGER_CHANNEL_ID=8828;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(MainActivity.this).inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.user_profile)
        {
            Dialog user_profile = new Dialog(MainActivity.this);
            user_profile.setContentView(R.layout.user_profile);
            profile_image=user_profile.findViewById(R.id.user_profile);
            username=user_profile.findViewById(R.id.username);
            password=user_profile.findViewById(R.id.password);
            submit=user_profile.findViewById(R.id.submit);
            camera=user_profile.findViewById(R.id.camera);
            gallery=user_profile.findViewById(R.id.gallery);
            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera,CAMERA_REQ_CODE);
                }
            });
            gallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent gallery = new Intent(Intent.ACTION_PICK);
                    gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(gallery,GALLERY_REQ_CODE);
                }
            });
            password.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction()==MotionEvent.ACTION_UP)
                    {
                        if(event.getRawX()>=(password.getRight()-password.getCompoundDrawables()[Right].getBounds().width()))
                        {
                            int selection= password.getSelectionEnd();
                            if(passed)
                            {
                                password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off,0);
                                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                passed=false;
                            }
                            else
                            {
                                password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_on_two,0);
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
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user_profile.setCancelable(true);
                    Log.d("User_Details","Username: "+username.getText().toString().trim()+"\n"+"Password: "+password.getText().toString().trim());
                    InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(v.getWindowToken(),0);
                    user_profile.dismiss();
                }
            });
            user_profile.show();
        }
        else if(id==android.R.id.home)
        {
            AlertDialog.Builder exit_dialog = new AlertDialog.Builder(MainActivity.this);
            exit_dialog.setTitle("Exit");
            exit_dialog.setMessage("Are you sure,you want to exit?");
            exit_dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.super.onBackPressed();
                }
            });
            exit_dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            exit_dialog.setNeutralButton("Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            exit_dialog.setCancelable(false);
            exit_dialog.show();
        }
        else if(id==R.id.toolbar)
            Toast.makeText(this, "Opening Toolbar", Toast.LENGTH_SHORT).show();
        else if(id==R.id.database)
        {
            AlertDialog database = new AlertDialog.Builder(MainActivity.this).create();
            database.setTitle("DataBase");
            database.setMessage("Are you sure,you want to connect to Database");
            database.setCancelable(false);
            database.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Establishing Connection with Database", Toast.LENGTH_SHORT).show();
                }
            });
            database.show();
        }
        else if(id==R.id.delete)
        {
            AlertDialog.Builder delete = new AlertDialog.Builder(MainActivity.this);
            delete.setTitle("Delete");
            delete.setMessage("Are you sure,you want to delete?");
            delete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "File Deleted Sucessfully", Toast.LENGTH_SHORT).show();
                }
            });
            delete.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            delete.setCancelable(true);
            delete.show();
        }
        else if(id==R.id.logout)
            Toast.makeText(this, "Loging out", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        try
        {
            getSupportActionBar().setTitle("ToolBar");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
//        binding.notification.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.instagram,null);
//                BitmapDrawable bitmap_drawable = (BitmapDrawable) drawable;
//                Bitmap image_bitmap = bitmap_drawable.getBitmap();
//                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                Notification notification = new Notification.Builder(MainActivity.this)
//                        .setLargeIcon(image_bitmap)
//                        .setSmallIcon(R.drawable.instagram)
//                        .setContentText("Now you have to go Through real Hell worst than any Nightmare but I know you will be the last on standing")
//                        .setSubText("Message From Future Aditya")
//                        .setChannelId(CHANNEL_ID)
//                        .build();
//                manager.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"Positivity",NotificationManager.IMPORTANCE_HIGH));
//                manager.notify(INTEGER_CHANNEL_ID,notification);
//            }
//        });
//        binding.dial.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent dial = new Intent(Intent.ACTION_DIAL);
//                dial.setData(Uri.parse("tel:9819375722"));
//                startActivity(dial);
//            }
//        });
//        binding.message.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent message = new Intent(Intent.ACTION_SENDTO);
//                message.setData(Uri.parse("smsto:"+Uri.encode("9819375722")));
//                startActivity(message);
//            }
//        });
//        binding.email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent email = new Intent(Intent.ACTION_SEND);
//                email.setType("message/rfc882");
//                email.putExtra(Intent.EXTRA_EMAIL,new String[]{"kelaskaraditya1@gmail.com","sandy.kelaskar@gmail.com"});
//                email.putExtra(Intent.EXTRA_SUBJECT,"Goals,Responsiibility,Mindset");
//                email.putExtra(Intent.EXTRA_TEXT,"Now you have to go Through real Hell worst than any Nightmare but I know you will be the last on standing");
//                startActivity(Intent.createChooser(email,"share via"));
//            }
//        });
//        binding.share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent share = new Intent(Intent.ACTION_SEND);
//                share.setType("text/plain");
//                share.putExtra(Intent.EXTRA_TEXT,"I am Sharing");
//                startActivity(Intent.createChooser(share,"share Via"));
//            }
//        });
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction1 = manager.beginTransaction();
//        transaction1.add(R.id.framelayout1,new AFragment());
//        transaction1.commit();
//        FragmentTransaction transaction2 = manager.beginTransaction();
//        transaction2.add(R.id.framelayout2,new BFragment());
//        transaction2.commit();
        FragmentManager manager = getSupportFragmentManager();
        binding.buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                transaction1.add(R.id.framelayout,new AFragment());
                transaction1.commit();
            }
        });
        binding.buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction2 = manager.beginTransaction();
                transaction2.add(R.id.framelayout,new BFragment());
                transaction2.commit();
            }
        });
        binding.buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction3 = manager.beginTransaction();
                transaction3.add(R.id.framelayout,new CFragment());
                transaction3.commit();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==CAMERA_REQ_CODE)
            {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                profile_image.setImageBitmap(image);
            }
            if(requestCode==GALLERY_REQ_CODE)
                profile_image.setImageURI(data.getData());
        }
    }
}