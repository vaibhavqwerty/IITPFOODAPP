package com.example.iitpfoodapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.iitpfoodapp.FinalOrderList.mUsername;
//import static com.example.iitpfoodapp.MainActivity.UserUniqueId;

public class Restaurants extends AppCompatActivity {
public static String FirebaseAddress;
public static String FirebasePhone;
    public static String UserUniqueId;
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListner;
    private String ss,p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        setTitle("Restaurants");
UserInfo f;
        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid());
//mMessageDatabaseReference.child("address").setValue("hi");
UserUniqueId=firebaseAuth.getCurrentUser().getUid();

        mChildEventListner= new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

//               if(dataSnapshot.getKey()=="address")
//               {
//                    ss=dataSnapshot.getValue(String.class);
//               }
//                if(dataSnapshot.getKey()=="phone_number")
//                {
//                    p=dataSnapshot.getValue(String.class);
//                }

                UserInfo f=dataSnapshot.getValue(UserInfo.class);

                   FirebaseAddress=f.getAddress();

                //Toast.makeText(Restaurants.this,FirebaseAddress,Toast.LENGTH_LONG).show();
                   FirebasePhone=f.getPhone_number();

                   mUsername=f.getName();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mMessageDatabaseReference.addChildEventListener(mChildEventListner);

        TextView arya=findViewById(R.id.arya);
        arya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Restaurants.this,AryaCanteen.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.order_his, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){


            case R.id.history_menu:
                Intent i=new Intent(Restaurants.this,history.class);
                startActivity(i);
                return true;





            default:
                return super.onOptionsItemSelected(item);
        }}
}
