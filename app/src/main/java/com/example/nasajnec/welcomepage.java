package com.example.nasajnec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

public class welcomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);


        VideoView videoView = findViewById(R.id.videoView);

        String path = "android.resource://com.example.nasajnec/"+R.raw.splashscreen;

        Uri u = Uri.parse(path);

        videoView.setVideoURI(u);

        videoView.start();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(welcomepage.this,LogInActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
