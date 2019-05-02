package com.example.iitpfoodapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.example.iitpfoodapp.FinalOrderList.dateToStr;
import static com.example.iitpfoodapp.FinalOrderList.food;
import static com.example.iitpfoodapp.FinalOrderList.mUsername;
import static com.example.iitpfoodapp.Quantity.totalPrice;
import static com.example.iitpfoodapp.Restaurants.FirebaseAddress;
import static com.example.iitpfoodapp.Restaurants.FirebasePhone;
import static com.example.iitpfoodapp.currentStatus.random;
//import static com.example.iitpfoodapp.currentStatus.dateToStr;

public class HomeDelivery extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabaseD;
    private DatabaseReference mMessageDatabaseReferenceD;
    private FirebaseDatabase mFirebaseDatabaseHis;
    private DatabaseReference mMessageDatabaseReferenceHis;
    private finalFoodList finalfoodlist;
    FirebaseAuth firebaseAuth;
    private String user;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_delivery);
       final EditText val=findViewById(R.id.address);
        final EditText val1=findViewById(R.id.phoneNo);
        firebaseAuth = FirebaseAuth.getInstance();
        Button b=findViewById(R.id.send);
//        Date today = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss");
//        final String dateToStr = format.format(today);
        mFirebaseDatabaseD = FirebaseDatabase.getInstance();
        mMessageDatabaseReferenceD = mFirebaseDatabaseD.getReference().child("Arya Services Home Delivery");
        mFirebaseDatabaseHis = FirebaseDatabase.getInstance();
        mMessageDatabaseReferenceHis = mFirebaseDatabaseHis.getReference().child(firebaseAuth.getCurrentUser().getUid()).child("History");

       // mFirebaseDatabaseE = FirebaseDatabase.getInstance();
       // mMessageDatabaseReferenceE = mFirebaseDatabaseE.getReference().child("Arya Services Home Delivery Address");
     Button ad=findViewById(R.id.addr);
     ad.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             val.setText(FirebaseAddress);

         }
     });
        Button ph=findViewById(R.id.pho);
        ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val1.setText(FirebasePhone);

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                finalfoodlist = new finalFoodList(food);

                //Toast.makeText(HomeDelivery.this,""+user,Toast.LENGTH_SHORT).show();
                 user =val.getText().toString();
                 phone =val1.getText().toString();
                if(TextUtils.isEmpty(user))
                {
                    Toast.makeText(HomeDelivery.this,"Please Enter Address ",Toast.LENGTH_SHORT).show();
                }
                else  if(TextUtils.isEmpty(phone))
                {
                    Toast.makeText(HomeDelivery.this,"Please Enter Phone number ",Toast.LENGTH_SHORT).show();
                }

               else
                {
                    new AlertDialog.Builder(HomeDelivery.this)
                            .setTitle("Sending Order")
                            .setMessage("Are you sure you want to send the order?")

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation

                                    random = new Random().nextInt(9000) + 1000;
                                    mMessageDatabaseReferenceD.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).setValue(finalfoodlist);
                                    mMessageDatabaseReferenceD.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Address").setValue(user);
                                    mMessageDatabaseReferenceD.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Phone").setValue(phone);
                                    mMessageDatabaseReferenceD.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("OrderCode").setValue(""+random);
                                    mMessageDatabaseReferenceD.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Date and Time").setValue(dateToStr);
                                    mMessageDatabaseReferenceD.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("UserName").setValue(mUsername);
                                    mMessageDatabaseReferenceD.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Price").setValue(""+totalPrice);

                                    mMessageDatabaseReferenceHis.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).setValue(finalfoodlist);
                                    mMessageDatabaseReferenceHis.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("OrderCode").setValue(""+random);
                                    mMessageDatabaseReferenceHis.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Date and Time").setValue(dateToStr);
                                    mMessageDatabaseReferenceHis.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Phone").setValue(phone);
                                    mMessageDatabaseReferenceHis.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Address").setValue(user);
                                    mMessageDatabaseReferenceD.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("UserName").setValue(mUsername);
                                    mMessageDatabaseReferenceHis.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Price").setValue(""+totalPrice);

                                    Toast.makeText(HomeDelivery.this,"Order Sent ",Toast.LENGTH_SHORT).show();

                                    Intent iii=new Intent(HomeDelivery.this,OrderCodePage.class);
                                    startActivity(iii);
                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();




            }
            }
        });



    }
}
