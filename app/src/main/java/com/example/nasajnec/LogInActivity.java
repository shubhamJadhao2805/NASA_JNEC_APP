package com.example.nasajnec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LogInActivity extends AppCompatActivity {

    EditText id;
    EditText password;
    static String userId;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        button = findViewById(R.id.proceed);
         SharedPreferences editor = getSharedPreferences("Auth", MODE_PRIVATE);
         String isLogIn = editor.getString("isLogIn","0");
         if(isLogIn.equals("1")){
             userId = editor.getString("userId","0");
             Intent intent = new Intent(LogInActivity.this,MainActivity.class);
             startActivity(intent);
             finish();
         }




    }

    public void logIn(View view){

        final SharedPreferences.Editor editor = getSharedPreferences("Auth", MODE_PRIVATE).edit();

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Auth");
        if(TextUtils.isEmpty(id.getText()) || TextUtils.isEmpty(password.getText()) || id.getText().toString().equals("admin")){

            if(id.getText().toString().equals("admin") && password.getText().toString().equals("1234")){
                Intent intent = new Intent(LogInActivity.this,ScanQrCode.class);
                startActivity(intent);
            }
            if(TextUtils.isEmpty(id.getText())){

            }

        }else{


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Log in...");
            progressDialog.show();

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild(id.getText().toString())){
                       DatabaseReference reference1 = reference.child(id.getText().toString());
                       reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                               HashMap<String,String> map;
                               map = (HashMap<String, String>) dataSnapshot.getValue();
                               String pass = map.get("pass");

                               if(pass.equals(password.getText().toString())){

                                   editor.putString("userId",id.getText().toString());
                                   editor.putString("isLogIn","1");
                                   editor.commit();
                                   userId = id.getText().toString();
                                   try {
                                       final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("coupon");
                                       reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                               if (!dataSnapshot.hasChild(id.getText().toString())) {

                                                   reference.child(id.getText().toString()).setValue("0");


                                               }
                                           }

                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {

                                           }
                                       });

                                       Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                                       startActivity(intent);
                                       finish();
                                       progressDialog.dismiss();


                                   }catch (Exception e){
                                       Toast.makeText(LogInActivity.this,"Failed!",Toast.LENGTH_SHORT).show();
                                       progressDialog.dismiss();
                                   }


                               }else{
                                   Toast.makeText(LogInActivity.this,"Please check your password!",Toast.LENGTH_SHORT).show();
                                   progressDialog.dismiss();

                               }

                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError databaseError) {
                               progressDialog.dismiss();

                           }
                       });

                    }else{
                        Toast.makeText(LogInActivity.this,"Wrong id!",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();

                }
            });


        }


    }



}
