package com.example.basicmathschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView time;
    TextView ques;
    TextView ans;
    int seconds = 24;
    boolean result;
    private int sc= 0;
    private boolean stopTimer = false;
    TextView score;
    protected  void generateQues(){
        Random rand=new Random();
        int a=rand.nextInt(100);
        int b=rand.nextInt(100);
        int res=a+b;
        result=true;
        float f=rand.nextFloat();
        if(f>0.5f){
            res=rand.nextInt(100);
            result=false;
        }
        ques.setText(a+"+"+b);
        ans.setText("="+res);
    }
    protected  void verifyResult(boolean answer){
        if(answer==result){
            sc +=5;
            score.setText("SCORE :"+sc);
        }
        else{
            Vibrator vibe=(Vibrator) this.getSystemService(VIBRATOR_SERVICE);
            vibe.vibrate(100);
        }
        generateQues();
    }
    private void timer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                time.setText("TIME :" + seconds);
                seconds--;
                if (seconds < 0) {
                    Intent i = new Intent(GameActivity.this, ScoreActivity.class);
                    i.putExtra("score", sc);
                    startActivity(i);
                    stopTimer = true;
                }
                if (stopTimer == false) {
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ImageButton correct=(ImageButton) findViewById(R.id.correct);
        ImageButton close=(ImageButton) findViewById(R.id.close);
        time=(TextView) findViewById(R.id.textTime);
        score=(TextView) findViewById(R.id.textScore);
         ques=(TextView) findViewById(R.id.textQues);
        ans=(TextView) findViewById(R.id.textAns);


        timer();
        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyResult(true);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyResult(false);
            }
        });
        generateQues();
    }
    @Override
    protected void onPause() {
        super.onPause();
        stopTimer = false;
        finish();
    }
}