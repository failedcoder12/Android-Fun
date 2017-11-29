package com.example.com.simpletimer;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.com.simpletimer.R.drawable.cic;
import static com.example.com.simpletimer.R.drawable.circle;
import static java.util.Collections.*;

public class MainActivity extends AppCompatActivity {

    int i=0;
    TextView timer;
    Button count;
    Button startstop;
    SeekBar seekBar;
    ListView listView;
    Boolean isCounterActive = false;
    ArrayList<String> counttt;
    CountDownTimer countDownTimer;

    public void timess(int progress){
        int minutes = (int) progress/60;
        int seconds = (progress-minutes*60);
        String sec = Integer.toString(seconds);
        if(sec.length()==1){
            sec = "0"+sec;
        }
        timer.setText(Integer.toString(minutes)+":"+sec);
    }

    public void countt(View view){
        if(isCounterActive == true) {
            i++;
            counttt.add(0, "                 Count"+Integer.toString(i) + "                        " + (String) timer.getText());
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, counttt);
            listView.setAdapter(arrayAdapter);
        }
    }

    public void resetcount(){
        counttt.clear();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, counttt);
        listView.setAdapter(arrayAdapter);
        i=0;
    }

    public void reset(){
        timer.setText("0:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        startstop.setBackgroundResource(circle);
        seekBar.setEnabled(true);
        isCounterActive = false;
    }

    public void controltimer(View view){

        if(isCounterActive == false) {
            resetcount();
            isCounterActive = true;
            seekBar.setEnabled(false);
            startstop.setBackgroundResource(cic);
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    timess((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    reset();
                    timer.setText("0:30");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.pikachu);
                    mediaPlayer.start();
                }
            }.start();
        }else {
            reset();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startstop = (Button) findViewById(R.id.stopplay);
        timer = (TextView) findViewById(R.id.timetext);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        listView = (ListView) findViewById(R.id.listView);
        counttt = new ArrayList<String>();
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timess(progress);
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
