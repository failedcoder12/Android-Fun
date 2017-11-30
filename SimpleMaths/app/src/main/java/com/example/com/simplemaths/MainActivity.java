package com.example.com.simplemaths;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button playButton;
    RelativeLayout relativeLayout;
    Button ans1;
    Button ans2;
    Button ans3;
    Button ans4;
    TextView answer;
    TextView clock;
    TextView ys;
    TextView points;
    TextView question;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationofanswer;
    int score = 0;
    int number = 0;

    public void generateQuestions(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        answers.clear();
        question.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationofanswer = random.nextInt(4);
        int galat;
        for(int i=0;i<4;i++){
            if(i==locationofanswer){
                answers.add(a+b);
            }else {
                galat = random.nextInt(41);
                while(galat == a+b){
                    galat = random.nextInt(41);
                }
                answers.add(galat);
            }
        }
        ans1.setText(Integer.toString(answers.get(0)));
        ans2.setText(Integer.toString(answers.get(1)));
        ans3.setText(Integer.toString(answers.get(2)));
        ans4.setText(Integer.toString(answers.get(3)));

    }

    public void checkanswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationofanswer)))
        {
            score++;
            answer.setText("Correct!");
        }
        else {
            answer.setText("Incorrect!");
        }
        number++;
        points.setText(Integer.toString(score)+ "/" + Integer.toString(number));
        generateQuestions();
    }

    public void play (View view){
        playButton.setVisibility(View.INVISIBLE);
        ys.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.VISIBLE);
        score = 0;
        number = 0;

        clock.setText("30s");
        points.setText("0/0");

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                clock.setText(Integer.toString((int)millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playButton.setVisibility(View.VISIBLE);
                ys.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.INVISIBLE);
                ys.setText("Your Score is " + score);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = (Button) findViewById(R.id.go);
        relativeLayout = (RelativeLayout) findViewById(R.id.mainframe);
        ans1 = (Button) findViewById(R.id.button0);
        ans2 = (Button) findViewById(R.id.button1);
        ans3 = (Button) findViewById(R.id.button2);
        ans4 = (Button) findViewById(R.id.button3);
        ys = (TextView) findViewById(R.id.ys);
        answer = (TextView) findViewById(R.id.correct);
        clock = (TextView) findViewById(R.id.timer);
        points = (TextView) findViewById(R.id.score);
        question = (TextView) findViewById(R.id.question);
        generateQuestions();

    }
}
