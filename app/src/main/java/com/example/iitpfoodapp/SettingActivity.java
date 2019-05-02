package com.example.iitpfoodapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

public class SettingActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("Users");


        Button update = (Button) findViewById(R.id.updatebutton);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 EditText username  = (EditText) findViewById(R.id.username);
                 EditText phoneno = (EditText) findViewById(R.id.phonenumber);
                // EditText adress = (EditText) findViewById(R.id.address);
                 EditText address = (EditText) findViewById(R.id.email);
                //***************
              //  Toast.makeText(this,"Hiiii Fuck You",Toast.LENGTH_SHORT).show();
                String Username = username.getText().toString();
                String PhoneNo = phoneno.getText().toString();
               // String Adress = adress.getText().toString();
                String Address = address.getText().toString();


                if(TextUtils.isEmpty(Username))
                {
                    Toast.makeText(SettingActivity.this,"Please Enter Your Username ",Toast.LENGTH_SHORT).show();
                }
               else if(TextUtils.isEmpty(PhoneNo))
                {
                    Toast.makeText(SettingActivity.this,"Please Enter Your Phone Number",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(Address))
                {
                    Toast.makeText(SettingActivity.this,"Please Enter Your Adress",Toast.LENGTH_SHORT).show();
                }
              else
                {   String currentUserId=firebaseAuth.getCurrentUser().getUid();
                    String deviceToken = FirebaseInstanceId.getInstance().getToken();
                    //HashMap<String ,String> profileMap = new HashMap<>();
//                    profileMap.put("device_token",deviceToken);
//                    profileMap.put("uid",currentUserId);
//                    profileMap.put("name",Username);
//                    profileMap.put("phone_number",PhoneNo);
//                    profileMap.put("address",Address);
                    UserInfo profileMap=new UserInfo(deviceToken,currentUserId,Username,PhoneNo,Address);
                    mMessageDatabaseReference.child(currentUserId).child("info").setValue(profileMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful())
                                    {

                                        Toast.makeText(SettingActivity.this,"Profile Updated",Toast.LENGTH_SHORT).show();
                                        Intent i =new Intent(SettingActivity.this,Restaurants.class);
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        String Error = task.getException().toString();
                                        Toast.makeText(SettingActivity.this,"Error "+Error,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });
    }




}
