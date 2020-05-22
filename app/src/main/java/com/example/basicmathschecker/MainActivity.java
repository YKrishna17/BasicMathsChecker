package com.example.basicmathschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton share=(ImageButton) findViewById(R.id.shareBtn);
        ImageButton play=(ImageButton) findViewById(R.id.playBtn);
        ImageButton star=(ImageButton) findViewById(R.id.starBtn);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,"BMC - Fun way to learn Maths. http://www.play.google.com");
                startActivity(intent);
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewed) {
                Toast.makeText(MainActivity.this,"You can open your Google Play landing page",Toast.LENGTH_LONG).show();
            }
        });
    }
}
