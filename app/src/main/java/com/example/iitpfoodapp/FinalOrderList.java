package com.example.iitpfoodapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.iitpfoodapp.Quantity.finalFood;

import static com.example.iitpfoodapp.Quantity.finalQuantity;
import static com.example.iitpfoodapp.Quantity.finalTotal;
import static com.example.iitpfoodapp.Quantity.totalItems;
import static com.example.iitpfoodapp.Quantity.totalPrice;

public class FinalOrderList extends AppCompatActivity {

    public static final String ANONYMOUS = "anonymous";
   public static  String mUsername;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase1;
    private DatabaseReference mMessageDatabaseReference1;
    private ChildEventListener mChildEventListner;
    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_order_list);


//        LinearLayout rootView = findViewById(R.id.rootView);
//        for (int i = 0; i < totalItems; i++) {
//            TextView t = new TextView(FinalOrderList.this);
//            t.setText(finalList.get(i));
//            rootView.addView(t);
//        }
//        TextView grandTotal = findViewById(R.id.grandTotal);
//        grandTotal.setText("Grand Total: " + totalPrice + "/-");
        //********************
        final ArrayList<foodList> food=new ArrayList<foodList>();
        for(int i=0;i<totalItems;i++) {
            food.add(new foodList(finalFood.get(i), mUsername, finalQuantity.get(i), finalTotal.get(i)));
        }
        MessageAdapter adapter=new MessageAdapter(this,R.layout.final_list,food);
        ListView listView=findViewById(R.id.rootView);
        listView.setAdapter(adapter);

        TextView grandTotal = findViewById(R.id.grandTotal);
     grandTotal.setText("Grand Total: " + totalPrice + "/-");
        //*****************************

        //firebase veirfy
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child(mUsername);
        mFirebaseDatabase1 = FirebaseDatabase.getInstance();
        mMessageDatabaseReference1 = mFirebaseDatabase1.getReference().child("Arya Services");


        Button mSendButton = findViewById(R.id.verify);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(FinalOrderList.this,"Order sent for verification",Toast.LENGTH_SHORT).show();
                for (int i = 0; i < totalItems; i++) {
                    foodList friendlyMessage = new foodList(finalFood.get(i), mUsername,finalQuantity.get(i),finalTotal.get(i));
                    mMessageDatabaseReference.push().setValue(friendlyMessage);
                    foodList friendlyMessage1 = new foodList(finalFood.get(i), mUsername,finalQuantity.get(i),finalTotal.get(i));
                    mMessageDatabaseReference1.push().setValue(friendlyMessage);
                }

            }
        });


    }


    public void goToUpi(View view) {

        Uri uri = Uri.parse("upi://pay?pa=9572544000@upi&pn=Abhishek%20Kumar&tn=IIIP%20FOOOD%20APP&am=" + totalPrice + "&cu=INR&url=https://mystar.co"); // missing 'http://' will cause crashed
        //Log.d(TAG, "onClick: uri: "+uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivityForResult(intent, 1);


    }

}