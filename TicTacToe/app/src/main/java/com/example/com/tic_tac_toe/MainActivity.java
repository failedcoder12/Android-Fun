package com.example.com.tic_tac_toe;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView player1;

    TextView player2;

    GridLayout gridLayout;

    TextView Game;

    int activeplayer = 0;
    int play = 1;

    int[] bareian = {2,2,2,2,2,2,2,2,2};
    int[][] winningposition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void turn (View view){
        ImageView image = (ImageView) view;
        int tapped = Integer.parseInt(image.getTag().toString());

        if(activeplayer == 0 && bareian[tapped] == 2 &&play == 1 ) {
            image.setImageResource(R.drawable.delete);
            image.setTranslationY(-1000f);
            activeplayer = 1;
            player1.animate().translationX(1000f).setDuration(300);
            player2.animate().translationX(0f).setDuration(300);
            bareian[tapped] = 0;
            image.animate().translationYBy(1000f).rotation(360).setDuration(300);
        }
        else if(activeplayer == 1 && bareian[tapped] == 2 && play == 1) {
            activeplayer = 0;
            image.setTranslationY(-1000f);
            player1.animate().translationX(0f).setDuration(300);
            player2.animate().translationX(-1000f).setDuration(300);
            image.setImageResource(R.drawable.icon);
            bareian[tapped] = 1;
            image.animate().translationYBy(1000f).rotation(360).setDuration(300);
        }
        for(int[] win : winningposition) {
            if (bareian[win[0]] == bareian[win[1]] && bareian[win[0]] == bareian[win[2]] && bareian[win[0]] != 2) {
                play = 0;
                if (bareian[win[0]] == 0) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TextView kk = (TextView) findViewById(R.id.ok);
                            kk.setText("Player 1 Wins");
                            findViewById(R.id.fina).setVisibility(View.VISIBLE);
                        }
                    }, 300);
                } else if (bareian[win[0]] == 1) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TextView kk = (TextView) findViewById(R.id.ok);
                            kk.setText("Player 2 Wins");
                            findViewById(R.id.fina).setVisibility(View.VISIBLE);
                        }
                    }, 300);
                }
            }
        }
            if(play == 1) {
                int f = 0;
                for (int i = 0; i < bareian.length; i++) {
                    if (bareian[i] == 2)
                        f = 1;
                }
                if (f == 0) {
                    TextView kk = (TextView) findViewById(R.id.ok);
                    kk.setText("Game Draw");
                    findViewById(R.id.fina).setVisibility(View.VISIBLE);
                }
            }
        }


    public void playagain (View view){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.fina);
        linearLayout.setVisibility(View.GONE);
        play = 1;
        for(int i=0;i<bareian.length;i++){
         bareian[i] = 2;
        }
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = (TextView) findViewById(R.id.name);

        player2 = (TextView) findViewById(R.id.player2);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        player2.setTranslationX(-1000f);
    }
}
