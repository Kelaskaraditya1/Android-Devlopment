package com.starkindustries.media_player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.starkindustries.media_player.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    public MediaPlayer mediaplayer;
    public AudioManager audiomanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        mediaplayer=new MediaPlayer();
        mediaplayer=MediaPlayer.create(MainActivity.this,R.raw.harmane_song);
        binding.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaplayer!=null&&!(mediaplayer.isPlaying()))
                    mediaplayer.start();
            }
        });
        binding.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaplayer!=null&&mediaplayer.isPlaying())
                    mediaplayer.pause();
            }
        });
        binding.stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaplayer!=null&&mediaplayer.isPlaying())
                    mediaplayer.stop();
            }
        });
        audiomanager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxvol = audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current_vol=audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC);
        binding.seekbar.setMax(maxvol);
        binding.seekbar.setProgress(current_vol);
        binding.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.progressSeekbar.setMax(mediaplayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                binding.progressSeekbar.setProgress(mediaplayer.getCurrentPosition());
            }
        },0,900);
        binding.progressSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                mediaplayer.seekTo(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaplayer!=null&&(mediaplayer.isPlaying()))
        {
            mediaplayer.pause();
            mediaplayer.release();
        }
    }

}