package com.example.nasajnec;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQrCode extends AppCompatActivity {

    EditText id;
    EditText used;
    private IntentIntegrator qrScan;
    private Button buttonScan;
    ProgressDialog progressDialog;
    TextView couponId;
    TextView couponused;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code);

        id = findViewById(R.id.id);
        couponused = findViewById(R.id.coyponused);

        couponId = findViewById(R.id.idcoupan2);
        Button button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("coupon").child(id.getText().toString());
                reference.setValue(used.getText().toString());
                Toast.makeText(ScanQrCode.this,"Done",Toast.LENGTH_SHORT).show();

            }
        });
        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(true);
        qrScan.setBeepEnabled(true);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");

        progressDialog.setCanceledOnTouchOutside(false);
        buttonScan = findViewById(R.id.submit);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan.initiateScan();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    progressDialog.show();
                    progressDialog.setCanceledOnTouchOutside(false);
                    //converting the data to json
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("coupon");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(result.getContents())){
                                reference.child(result.getContents()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        try {
                                            String coupan = dataSnapshot.getValue().toString();
                                            if(Integer.parseInt(coupan) < 12) {
                                                int new_coupan = Integer.parseInt(coupan) + 1;
                                                String couponusedString = "Coupon used : " + new_coupan;
                                                couponused.setText(couponusedString);
                                                reference.child(result.getContents().toString()).setValue(String.valueOf(new_coupan)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if (task.isSuccessful()) {
                                                            couponId.setText("Coupon ID : " + result.getContents().toString());
                                                            Toast.makeText(ScanQrCode.this, "Done!", Toast.LENGTH_SHORT).show();

                                                            progressDialog.dismiss();

                                                        } else {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(ScanQrCode.this, "Failed!Please try again!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });

                                            }else{
                                                Toast.makeText(ScanQrCode.this, "You have used all coupons!", Toast.LENGTH_SHORT).show();

                                            }
                                        }catch (Exception e){
                                            Toast.makeText(ScanQrCode.this, "Failed!Please try again!", Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                        progressDialog.dismiss();
                                        Toast.makeText(ScanQrCode.this,"Failed!Please try again!",Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }else{

                                progressDialog.dismiss();
                                Toast.makeText(ScanQrCode.this,"Invalid coupon!",Toast.LENGTH_SHORT).show();


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            progressDialog.dismiss();
                            Toast.makeText(ScanQrCode.this,"Failed!Please try agian!",Toast.LENGTH_SHORT).show();

                        }
                    });

                    Toast.makeText(ScanQrCode.this,result.getContents(),Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }    }


        public void submit(View view) {


        progressDialog.show();
            if (!TextUtils.isEmpty(id.getText().toString())) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("coupon");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if (dataSnapshot.hasChild(id.getText().toString())) {
                            reference.child(id.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    try {
                                        String coupan = dataSnapshot.getValue().toString();
                                        if (Integer.parseInt(coupan) < 12) {
                                            int new_coupan = Integer.parseInt(coupan) + 1;
                                            String couponusedString = "Coupon used : " + new_coupan;
                                            couponused.setText(couponusedString);
                                            reference.child(id.getText().toString()).setValue(String.valueOf(new_coupan)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful()) {
                                                        couponId.setText("Coupon ID : " + id.getText().toString());
                                                        Toast.makeText(ScanQrCode.this, "Done!", Toast.LENGTH_SHORT).show();

                                                        progressDialog.dismiss();

                                                    } else {
                                                        progressDialog.dismiss();
                                                        Toast.makeText(ScanQrCode.this, "Failed!Please try again!", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });

                                        } else {
                                            Toast.makeText(ScanQrCode.this, "You have used all coupons!", Toast.LENGTH_SHORT).show();

                                        }
                                    } catch (Exception e) {
                                        Toast.makeText(ScanQrCode.this, "Failed!Please try again!", Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                    progressDialog.dismiss();
                                    Toast.makeText(ScanQrCode.this, "Failed!Please try again!", Toast.LENGTH_SHORT).show();

                                }
                            });
                        } else {

                            progressDialog.dismiss();
                            Toast.makeText(ScanQrCode.this, "Invalid coupon!", Toast.LENGTH_SHORT).show();


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }else{
                Toast.makeText(ScanQrCode.this, "Please enter id!", Toast.LENGTH_SHORT).show();

            }
        }

}

