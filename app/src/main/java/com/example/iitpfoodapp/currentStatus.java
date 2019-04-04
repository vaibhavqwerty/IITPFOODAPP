package com.example.iitpfoodapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import static com.example.iitpfoodapp.FinalOrderList.adapter;
import static com.example.iitpfoodapp.FinalOrderList.food;
import static com.example.iitpfoodapp.FinalOrderList.mUsername;
import static com.example.iitpfoodapp.FinalOrderList.userReference;
import static com.example.iitpfoodapp.Quantity.finalFood;
import static com.example.iitpfoodapp.Quantity.finalQuantity;
import static com.example.iitpfoodapp.Quantity.finalTotal;
import static com.example.iitpfoodapp.Quantity.particularOrderPrice;
import static com.example.iitpfoodapp.Quantity.totalItems;
import static com.example.iitpfoodapp.Quantity.totalPrice;

public class currentStatus extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListner;
    private MessageAdapter mMessageAdapter;
    private finalFoodList qq;
    private ListView OrderView;
    private ArrayList<foodList> ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_status);

        TextView tp=findViewById(R.id.grandTotal);
        tp.setText("Grand Total:"+totalPrice+"/-");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child(mUsername).child(userReference).child("foodListArrayList");
        ll=new ArrayList<foodList>();
        mMessageAdapter = new MessageAdapter(currentStatus.this, R.layout.final_list, ll);
        OrderView=findViewById(R.id.orderOfConsumer);
        OrderView.setAdapter(mMessageAdapter);

        mChildEventListner= new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                foodList f=dataSnapshot.getValue(foodList.class);
              ll.add(f);



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               // finalFoodList f=dataSnapshot.getValue(finalFoodList.class);
                foodList f=dataSnapshot.getValue(foodList.class);
                Toast.makeText(getApplicationContext(),"Order "+(Integer.parseInt(dataSnapshot.getKey())+1)+" Verified",Toast.LENGTH_SHORT).show();
                ll.set(Integer.parseInt(dataSnapshot.getKey()),f);
                mMessageAdapter.notifyDataSetChanged();

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
    }


    public void myClickHandler(View v) {
        LinearLayout vwParentRow = (LinearLayout)v.getParent();
        // LinearLayout qwerty=(LinearLayout)v.getParent().getParent();
        //TextView child = (TextView)vwParentRow.getChildAt(0);
        // child.setText("HI");
        TextView key=(TextView)vwParentRow.getChildAt(2);
//        TextView key1=(TextView)vwParentRow.getChildAt(3);
//        LinearLayout ll=(LinearLayout)qwerty.getChildAt(0);
//        TextView name=(TextView)ll.getChildAt(4);
        int val=Integer.parseInt(key.getText().toString());

        int sub=particularOrderPrice.get(val);
        totalPrice=totalPrice-sub;
      TextView tp=findViewById(R.id.grandTotal);
        tp.setText("Grand Total:"+totalPrice+"/-");
        finalFood.remove(val);
        finalQuantity.remove(val);
        finalTotal.remove(val);
        food.remove(val);
        totalItems--;
        particularOrderPrice.remove(val);
        ll.remove(val);
        mMessageAdapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
        Toast.makeText(this,""+sub,Toast.LENGTH_SHORT).show();
//        mFirebaseDatabase1 = FirebaseDatabase.getInstance();
//        mMessageDatabaseReference1 = mFirebaseDatabase1.getReference().child(name.getText().toString());
//        mMessageDatabaseReference.child(key.getText().toString()).child("foodListArrayList").child(key1.getText().toString()).child("status").setValue("Status:Available");
//
//        //  vwParentRow.setBackgroundColor();
//        mMessageDatabaseReference1.child(key.getText().toString()).child("foodListArrayList").child(key1.getText().toString()).child("status").setValue("Status:Available");

        vwParentRow.refreshDrawableState();


    }
    public void goToUpi(View view) {
        if(totalPrice>0) {
            Uri uri = Uri.parse("upi://pay?pa=9572544000@upi&pn=Abhishek%20Kumar&tn=IITP%20FOOOD%20APP&am=" + totalPrice + "&cu=INR&url=https://mystar.co"); // missing 'http://' will cause crashed
            //Log.d(TAG, "onClick: uri: "+uri);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivityForResult(intent, 1);
        }
        else
        {
            Toast.makeText(this,"No item selected",Toast.LENGTH_SHORT).show();
        }


    }
}
