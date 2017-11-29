package com.example.com.smusic;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    AudioManager audioManager;

    GridLayout grid;

    LinearLayout linearLayout;

    TextView textView;

    public void close(View view){
        mediaPlayer.stop();
        linearLayout.animate().translationX(-1000f);
        grid.animate().translationX(0f);
    }

    public void playaudio (View view){

        mediaPlayer.start();
    }

    public void pauseaudio (View view){

        mediaPlayer.pause();
    }

    public void gaana(View view){
        Button button = (Button) view;
        mediaPlayer.stop();
        String name = (String) button.getText();
        int tapped = Integer.parseInt(button.getTag().toString());
        if(tapped == 1){
            mediaPlayer = MediaPlayer.create(this,R.raw.b);
            textView.setText(name);
        }else if(tapped == 2){
            textView.setText(name);
            mediaPlayer = MediaPlayer.create(this,R.raw.a);
        }else if(tapped == 3){
            textView.setText(name);
            mediaPlayer = MediaPlayer.create(this,R.raw.c);
        }else if(tapped == 4){
            textView.setText(name);
            mediaPlayer = MediaPlayer.create(this,R.raw.d);
        }else if(tapped == 5){
            textView.setText(name);
            mediaPlayer = MediaPlayer.create(this,R.raw.e);
        }else if(tapped == 6){
            textView.setText(name);
            mediaPlayer = MediaPlayer.create(this,R.raw.f);
        }else if(tapped == 7){
            textView.setText(name);
            mediaPlayer = MediaPlayer.create(this,R.raw.g);
        }else if(tapped == 8){
            textView.setText(name);
            mediaPlayer = MediaPlayer.create(this,R.raw.g);
        }
        mediaPlayer.start();
        grid.animate().translationX(1000f);
        linearLayout.animate().translationX(0f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        grid = (GridLayout) findViewById(R.id.grid);
        linearLayout.setTranslationX(-1000f);
        textView = (TextView) findViewById(R.id.song);
        mediaPlayer = MediaPlayer.create(this,R.raw.b);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxvolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currvolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar volcon = (SeekBar) findViewById(R.id.vol);
        volcon.setMax(maxvolume);
        volcon.setProgress(currvolume);
        volcon.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar durcon = (SeekBar) findViewById(R.id.dur);
        durcon.setMax(mediaPlayer.getDuration());
//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                durcon.setProgress(mediaPlayer.getCurrentPosition());
//            }
//        },0,100);

        durcon.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
