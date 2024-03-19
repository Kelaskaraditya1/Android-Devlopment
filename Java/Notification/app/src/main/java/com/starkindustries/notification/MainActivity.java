package com.starkindustries.notification;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.res.ResourcesCompat;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    public AppCompatButton notificaiton_button;
    public static final String CHANNEL_ID="my_channel";
    public static final String CHANNEL_NAME="My Channel";
    public static final int NOTIFICATION_ID=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificaiton_button=findViewById(R.id.notification_button);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.instagram,null);
        BitmapDrawable bitmapdrawable =(BitmapDrawable) drawable;
        Bitmap bitmap = bitmapdrawable.getBitmap();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(MainActivity.this)
                .setLargeIcon(bitmap)
                .setSmallIcon(R.drawable.instagram)
                .setContentText("I am Ironman")
                .setSubText("Message from Tony Stark")
                .setChannelId(CHANNEL_ID)
                .build();
        manager.createNotificationChannel(new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH));
        notificaiton_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.notify(NOTIFICATION_ID,notification);
            }
        });
    }
}