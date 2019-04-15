package com.example.iitpfoodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.iitpfoodapp.FinalOrderList.food;

public class HomeDelivery extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabaseD;
    private DatabaseReference mMessageDatabaseReferenceD;
    private FirebaseDatabase mFirebaseDatabaseE;
    private DatabaseReference mMessageDatabaseReferenceE;
    private finalFoodList finalfoodlist;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_delivery);

        firebaseAuth = FirebaseAuth.getInstance();
        Button b=findViewById(R.id.send);
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss");
        final String dateToStr = format.format(today);
        mFirebaseDatabaseD = FirebaseDatabase.getInstance();
        mMessageDatabaseReferenceD = mFirebaseDatabaseD.getReference().child("Arya Services Home Delivery");

       // mFirebaseDatabaseE = FirebaseDatabase.getInstance();
       // mMessageDatabaseReferenceE = mFirebaseDatabaseE.getReference().child("Arya Services Home Delivery Address");


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText val=findViewById(R.id.address);
                String user =val.getText().toString();
                EditText val1=findViewById(R.id.phoneNo);
                String phone =val1.getText().toString();

                finalfoodlist = new finalFoodList(food);
                mMessageDatabaseReferenceD.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).setValue(finalfoodlist);
                Toast.makeText(HomeDelivery.this,""+user,Toast.LENGTH_SHORT).show();

                mMessageDatabaseReferenceD.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Address").setValue(user);
                mMessageDatabaseReferenceD.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Mobile").setValue(phone);
            }
        });



    }
}
