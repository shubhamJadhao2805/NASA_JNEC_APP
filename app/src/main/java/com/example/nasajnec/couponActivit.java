package com.example.nasajnec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.WriterException;

import java.util.ArrayList;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class couponActivit extends AppCompatActivity {
    QRGEncoder qrgEncoder;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    List<ImageView> imageViews;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        imageViews = new ArrayList<>();
       imageView = findViewById(R.id.imageView);
       imageView1 = findViewById(R.id.imageView2);
       imageView2 = findViewById(R.id.imageView3);
       imageView3 = findViewById(R.id.imageView4);
       imageView4 = findViewById(R.id.imageView5);
       imageView5 = findViewById(R.id.imageView6);
       imageView6 = findViewById(R.id.imageView7);
       imageView7 = findViewById(R.id.imageView8);
       imageView8 = findViewById(R.id.imageView9);
       imageView9 = findViewById(R.id.imageView10);
       imageView10 = findViewById(R.id.imageView11);
       imageView11 = findViewById(R.id.imageView12);


        imageViews.add(imageView);
        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        imageViews.add(imageView4);
        imageViews.add(imageView5);
        imageViews.add(imageView6);
        imageViews.add(imageView7);
        imageViews.add(imageView8);
        imageViews.add(imageView9);
        imageViews.add(imageView10);
        imageViews.add(imageView11);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching coupons...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);




        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("coupon");





        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(LogInActivity.userId)){
                    DatabaseReference reference2 = reference.child(LogInActivity.userId);
                    reference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            progressDialog.dismiss();
                            String couponsuse = (String) dataSnapshot.getValue();
                            generateQrCode();
                            int i;
                            if(Integer.parseInt(couponsuse) < 12) {
                                for (i = 0; i < Integer.parseInt(couponsuse); i++) {
                                    imageViews.get(i).setImageResource(R.drawable.co3);
                                    imageViews.get(i).setPadding(20,20,20,20);
                                }
                            }else{
                                for (i = 0; i < imageViews.size(); i++) {
                                    imageViews.get(i).setVisibility(View.INVISIBLE);
                                }
                                Toast.makeText(couponActivit.this,"You have used all coupons",Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            Toast.makeText(couponActivit.this,"Failed!",Toast.LENGTH_SHORT).show();

                            progressDialog.dismiss();
                        }
                    });
                }else{

                generateQrCode();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(couponActivit.this,"Failed!",Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        });

    }

    public void generateQrCode(){
        String inputValue = LogInActivity.userId.trim();
        if (inputValue.length() > 0) {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 3 / 4;

            qrgEncoder = new QRGEncoder(
                    inputValue, null,
                    QRGContents.Type.TEXT,
                    smallerDimension);
            try {
                Bitmap bitmap = qrgEncoder.encodeAsBitmap();
                int i;
                for(i=0;i<imageViews.size();i++){
                    imageViews.get(i).setImageBitmap(bitmap);
                }



            } catch (WriterException e) {

            }
        } else {

            Toast.makeText(couponActivit.this,"Error",Toast.LENGTH_SHORT).show();
        }
    }
}
