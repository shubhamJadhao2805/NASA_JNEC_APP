package com.example.nasajnec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
    }


    public void keynotes(View view){
        Intent intent = new Intent(Event.this,keynotes.class);
        startActivity(intent);
    }
    public void work(View view){
        Intent intent = new Intent(Event.this,workshop.class);
        startActivity(intent);
    }
    public void seminars(View view){
        Intent intent = new Intent(Event.this,seminars.class);
        startActivity(intent);
    }
}
