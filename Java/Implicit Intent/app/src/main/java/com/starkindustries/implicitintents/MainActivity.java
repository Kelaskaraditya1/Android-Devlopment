package com.starkindustries.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.starkindustries.implicitintents.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        binding.dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(Intent.ACTION_DIAL);
                inext.setData(Uri.parse("tel: +919819375722"));
                startActivity(inext);
            }
        });
        binding.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(Intent.ACTION_SENDTO);
                inext.setData(Uri.parse("smsto:"+Uri.encode("+918591059220")));
                startActivity(inext);
            }
        });
        binding.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(Intent.ACTION_SEND);
                inext.setType("message/rfc882");
                inext.putExtra(Intent.EXTRA_EMAIL,new String[]{"kelaskaraditya1@gmial.com","sandy.kelaskar@gmail.com"});
                inext.putExtra(Intent.EXTRA_SUBJECT,"Query");
                inext.putExtra(Intent.EXTRA_TEXT,"Please resolve this Query as possible");
                startActivity(Intent.createChooser(inext,"Email via"));
            }
        });
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(Intent.ACTION_SEND);
                inext.setType("text/plain");
                inext.putExtra(Intent.EXTRA_TEXT,"I am Sharing ");
                startActivity(Intent.createChooser(inext,"Share Via"));
            }
        });

    }
}