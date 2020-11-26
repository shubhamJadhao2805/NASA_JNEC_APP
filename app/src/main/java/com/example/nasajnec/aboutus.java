package com.example.nasajnec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class aboutus extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);


//        final HashMap<String,HashMap<String,String>> map2 = new HashMap<>();
//        int i;
//        for(i =1;i<=35;i++){
//            String result = "Z310" + i;
//            String result2 = "Z305" + i;
//            String result3 = "Z312" + i;
//            String result4 = "Z314" + i;
//            String result5 = "Z316" + i;
//            String result6 = "Z318" + i;
//            String result7 = "Z319" + i;
//            String result8 = "Z320" + i;
//            String result9 = "Z325" + i;
//            String result10 = "Z328" + i;
//            String result11 = "Z329" + i;
//            String result12 = "Z330" + i;
//            String result13= "Z342" + i;
//            String result14 = "Z346" + i;
//            String result15= "Z340" + i;
//            String result16= "Z301" + i;
//            String result17 = "Z341" + i;
//            String result18= "Z343" + i;
//            String result19= "Z344" + i;
//            String result20= "Z345" + i;
//            String result21= "Z331" + i;
//            String result22= "Z333" + i;
//            String result23= "Z336" + i;
//            String result24= "Z338" + i;
//            String result25= "Z304" + i;
//            String result26= "MH81" + i;
//            String result27= "MH97" + i;
//            String result28= "MH24" + i;
//            String result29= "6840" + i;
//            String result30= "6896" + i;
//
//
//            HashMap<String,String> map= new HashMap<>();
//            HashMap<String,String> map1= new HashMap<>();
//            HashMap<String,String> map3= new HashMap<>();
//            HashMap<String,String> map4= new HashMap<>();
//            HashMap<String,String> map5= new HashMap<>();
//            HashMap<String,String> map6= new HashMap<>();
//            HashMap<String,String> map7= new HashMap<>();
//            HashMap<String,String> map8= new HashMap<>();
//            HashMap<String,String> map9= new HashMap<>();
//            HashMap<String,String> map10= new HashMap<>();
//            HashMap<String,String> map11= new HashMap<>();
//            HashMap<String,String> map12= new HashMap<>();
//            HashMap<String,String> map13= new HashMap<>();
//            HashMap<String,String> map14= new HashMap<>();
//            HashMap<String,String> map15= new HashMap<>();
//            HashMap<String,String> map16= new HashMap<>();
//            HashMap<String,String> map17= new HashMap<>();
//            HashMap<String,String> map18= new HashMap<>();
//            HashMap<String,String> map19= new HashMap<>();
//            HashMap<String,String> map20= new HashMap<>();
//            HashMap<String,String> map21= new HashMap<>();
//            HashMap<String,String> map22= new HashMap<>();
//            HashMap<String,String> map23= new HashMap<>();
//            HashMap<String,String> map24= new HashMap<>();
//            HashMap<String,String> map25= new HashMap<>();
//            HashMap<String,String> map26= new HashMap<>();
//            HashMap<String,String> map27= new HashMap<>();
//            HashMap<String,String> map28= new HashMap<>();
//            HashMap<String,String> map29= new HashMap<>();
//            HashMap<String,String> map30= new HashMap<>();
//
//            map.put("pass",result);
//            map1.put("pass",result2);
//            map30.put("pass",result3);
//            map3.put("pass",result4);
//            map4.put("pass",result5);
//            map5.put("pass",result6);
//            map6.put("pass",result7);
//            map7.put("pass",result8);
//            map8.put("pass",result9);
//            map9.put("pass",result10);
//            map10.put("pass",result11);
//            map11.put("pass",result12);
//            map12.put("pass",result13);
//            map13.put("pass",result14);
//            map14.put("pass",result15);
//            map15.put("pass",result16);
//            map16.put("pass",result17);
//            map17.put("pass",result18);
//            map18.put("pass",result19);
//            map19.put("pass",result20);
//            map20.put("pass",result21);
//            map21.put("pass",result22);
//            map22.put("pass",result23);
//            map23.put("pass",result24);
//            map24.put("pass",result25);
//            map25.put("pass",result26);
//            map26.put("pass",result27);
//            map27.put("pass",result28);
//            map28.put("pass",result29);
//            map29.put("pass",result30);
//            map2.put(result,map);
//            map2.put(result2,map1);
//            map2.put(result3,map30);
//            map2.put(result4,map3);
//            map2.put(result5,map4);
//            map2.put(result6,map5);
//            map2.put(result7,map6);
//            map2.put(result8,map7);
//            map2.put(result9,map8);
//            map2.put(result10,map9);
//            map2.put(result11,map10);
//            map2.put(result12,map11);
//            map2.put(result13,map12);
//            map2.put(result14,map13);
//            map2.put(result15,map14);
//            map2.put(result16,map15);
//            map2.put(result17,map16);
//            map2.put(result18,map17);
//            map2.put(result19,map18);
//            map2.put(result20,map19);
//            map2.put(result21,map20);
//            map2.put(result22,map21);
//            map2.put(result23,map22);
//            map2.put(result24,map23);
//            map2.put(result25,map24);
//            map2.put(result26,map25);
//            map2.put(result27,map26);
//            map2.put(result28,map27);
//            map2.put(result29,map28);
//            map2.put(result30,map29);
//        }
//
//        button = findViewById(R.id.button5);
//
//
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                progressDialog.show();
//                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Auth");
//                reference.setValue(map2).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            progressDialog.dismiss();
//                            Toast.makeText(aboutus.this,"Done",Toast.LENGTH_SHORT).show();
//                        }else{
//                            progressDialog.dismiss();
//                            Toast.makeText(aboutus.this,"Failed",Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//
//
//            }
//        });
//    }
    }



}
