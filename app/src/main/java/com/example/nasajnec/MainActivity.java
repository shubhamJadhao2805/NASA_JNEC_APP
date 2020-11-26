package com.example.nasajnec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.aboutus);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,aboutus.class);
                startActivity(intent);

            }
        });

        Button button1 = findViewById(R.id.logout);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SharedPreferences.Editor editor = getSharedPreferences("Auth", MODE_PRIVATE).edit();
                editor.putString("isLogIn","0");
                editor.commit();
                Intent intent = new Intent(MainActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }


    public void event(View view){

        Intent intent = new Intent(MainActivity.this,Event.class);
        startActivity(intent);
    }

    public void coupon(View view){
        Intent intent = new Intent(MainActivity.this,couponActivit.class);
        startActivity(intent);
    }

    public void shedule(View view){

        Intent intent = new Intent(MainActivity.this,sheduleActivity.class);
        startActivity(intent);

    }

    public void  map(View view){
        Intent intent = new Intent(MainActivity.this, Maps.class);
        startActivity(intent);
    }



}
