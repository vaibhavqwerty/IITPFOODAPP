package com.example.iitpfoodapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static com.example.iitpfoodapp.FinalOrderList.adapter;
import static com.example.iitpfoodapp.FinalOrderList.dateToStr;
import static com.example.iitpfoodapp.FinalOrderList.food;
import static com.example.iitpfoodapp.FinalOrderList.mUsername;
import static com.example.iitpfoodapp.FinalOrderList.userReference;
//import static com.example.iitpfoodapp.MainActivity.UserUniqueId;
import static com.example.iitpfoodapp.Quantity.finalFood;
import static com.example.iitpfoodapp.Quantity.finalQuantity;
import static com.example.iitpfoodapp.Quantity.finalTotal;
import static com.example.iitpfoodapp.Quantity.particularOrderPrice;
import static com.example.iitpfoodapp.Quantity.totalItems;
import static com.example.iitpfoodapp.Quantity.totalPrice;
import static com.example.iitpfoodapp.Restaurants.FirebaseAddress;
import static com.example.iitpfoodapp.Restaurants.FirebasePhone;

public class currentStatus extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private FirebaseDatabase mFirebaseDatabaseHis;
    private DatabaseReference mMessageDatabaseReferenceHis;
    private FirebaseDatabase mFirebaseDatabaseQ;
    private DatabaseReference mMessageDatabaseReferenceQ;
    private ChildEventListener mChildEventListner;
    private MessageAdapter mMessageAdapter;
    private finalFoodList qq;
    private finalFoodList finalfoodlist;
    private ListView OrderView;
    FirebaseAuth firebaseAuth;
    private MediaPlayer mediaplayer;
    public static int random;
    //public static String dateToStr;
    private ArrayList<foodList> ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_status);
        mediaplayer=MediaPlayer.create(this,R.raw.bee);
        firebaseAuth = FirebaseAuth.getInstance();

        TextView tp=findViewById(R.id.grandTotal);
        tp.setText("Grand Total:"+totalPrice+"/-");
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child(firebaseAuth.getCurrentUser().getUid()).child("Verification").child(userReference).child("foodListArrayList");
        ll=new ArrayList<foodList>();
        mMessageAdapter = new MessageAdapter(currentStatus.this, R.layout.final_list, ll);
        OrderView=findViewById(R.id.orderOfConsumer);
        OrderView.setAdapter(mMessageAdapter);



//        Date today = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss");
//         dateToStr = format.format(today);
        //userReference=dateToStr+firebaseAuth.getCurrentUser().getUid();
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
               // Toast.makeText(getApplicationContext(),"Order "+(Integer.parseInt(dataSnapshot.getKey())+1)+" Verified",Toast.LENGTH_SHORT).show();
              //  mediaplayer.start();

                ll.set(Integer.parseInt(dataSnapshot.getKey()),f);
                mMessageAdapter.notifyDataSetChanged();
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(currentStatus.this, "CHANNEL_ID");

                notificationBuilder.setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.foodicon)
                        .setTicker("Dilip21")
                        .setContentTitle("IITP FOOD APP Order Verification")
                        .setContentText("Order "+(Integer.parseInt(dataSnapshot.getKey())+1)+" Verified")
                        .setContentInfo("Info");

                NotificationManager notificationManager = (NotificationManager) currentStatus.this.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1, notificationBuilder.build());


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


        //*********************
        mFirebaseDatabaseQ = FirebaseDatabase.getInstance();
        mMessageDatabaseReferenceQ = mFirebaseDatabaseQ.getReference().child("Arya Services Counter");
        mFirebaseDatabaseHis = FirebaseDatabase.getInstance();
        mMessageDatabaseReferenceHis = mFirebaseDatabaseHis.getReference().child(firebaseAuth.getCurrentUser().getUid()).child("History");


        Button mSendButton = findViewById(R.id.sendOrder);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalItems>0) {
                    new AlertDialog.Builder(currentStatus.this)
                            .setTitle("Sending Order")
                            .setMessage("Are you sure you want to send the order?")

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Continue with delete operation
                                    Toast.makeText(currentStatus.this, "Order sent", Toast.LENGTH_SHORT).show();
//                for (int i = 0; i < totalItems; i++) {
//                    foodList friendlyMessage = new foodList(finalFood.get(i), mUsername,finalQuantity.get(i),finalTotal.get(i));
//                    mMessageDatabaseReference.push().setValue(friendlyMessage);
//                    foodList friendlyMessage1 = new foodList(finalFood.get(i), mUsername,finalQuantity.get(i),finalTotal.get(i));
//                    mMessageDatabaseReference1.push().setValue(friendlyMessage);
//                }
                                    finalfoodlist = new finalFoodList(food);
                                    random = new Random().nextInt(9000) + 1000;
                                    mMessageDatabaseReferenceQ.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).setValue(finalfoodlist);

                                    mMessageDatabaseReferenceQ.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("OrderCode").setValue(""+random);
                                    mMessageDatabaseReferenceQ.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Date and Time").setValue(dateToStr);
                                    mMessageDatabaseReferenceQ.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Phone").setValue(FirebasePhone);
                                    mMessageDatabaseReferenceQ.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Address").setValue(FirebaseAddress);
                                    mMessageDatabaseReferenceQ.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("UserName").setValue(mUsername);
                                    mMessageDatabaseReferenceQ.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Price").setValue(""+totalPrice);


                                    mMessageDatabaseReferenceHis.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).setValue(finalfoodlist);
                                    mMessageDatabaseReferenceHis.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("OrderCode").setValue(""+random);
                                    mMessageDatabaseReferenceHis.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Date and Time").setValue(dateToStr);
                                    mMessageDatabaseReferenceHis.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Phone").setValue(FirebasePhone);
                                    mMessageDatabaseReferenceHis.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Address").setValue(FirebaseAddress);
                                    mMessageDatabaseReferenceHis.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).child("Price").setValue(""+totalPrice);

                                    //mMessageDatabaseReference1.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).setValue(finalfoodlist);
                                    // mMessageDatabaseReference.push().setValue(finalfoodlist);
                                    //  mMessageDatabaseReference1.push().setValue(finalfoodlist);
                                    //Intent ii = new Intent(FinalOrderList.this, currentStatus.class);
                                    //startActivity(ii);

                                   Intent iii=new Intent(currentStatus.this,OrderCodePage.class);
                                   startActivity(iii);

                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();



                }
                else
                { Toast.makeText(currentStatus.this, "No item selected", Toast.LENGTH_SHORT).show();

                }
            }
        });

        Button mHomedev =findViewById(R.id.homeDelivery);
        mHomedev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(currentStatus.this,HomeDelivery.class);
               startActivity(i);
            }
        });


OrderView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        new AlertDialog.Builder(currentStatus.this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation

                        int val=position;
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
                        Toast.makeText(currentStatus.this,""+sub,Toast.LENGTH_SHORT).show();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
});
    }


//    public void myClickHandler(View v) {
//        LinearLayout vwParentRow = (LinearLayout)v.getParent();
//        // LinearLayout qwerty=(LinearLayout)v.getParent().getParent();
//        //TextView child = (TextView)vwParentRow.getChildAt(0);
//        // child.setText("HI");
//        TextView key=(TextView)vwParentRow.getChildAt(2);
////        TextView key1=(TextView)vwParentRow.getChildAt(3);
////        LinearLayout ll=(LinearLayout)qwerty.getChildAt(0);
////        TextView name=(TextView)ll.getChildAt(4);
//        int val=Integer.parseInt(key.getText().toString());
//
//        int sub=particularOrderPrice.get(val);
//        totalPrice=totalPrice-sub;
//      TextView tp=findViewById(R.id.grandTotal);
//        tp.setText("Grand Total:"+totalPrice+"/-");
//        finalFood.remove(val);
//        finalQuantity.remove(val);
//        finalTotal.remove(val);
//        food.remove(val);
//        totalItems--;
//        particularOrderPrice.remove(val);
//        ll.remove(val);
//        mMessageAdapter.notifyDataSetChanged();
//        adapter.notifyDataSetChanged();
//        Toast.makeText(this,""+sub,Toast.LENGTH_SHORT).show();
////        mFirebaseDatabase1 = FirebaseDatabase.getInstance();
////        mMessageDatabaseReference1 = mFirebaseDatabase1.getReference().child(name.getText().toString());
////        mMessageDatabaseReference.child(key.getText().toString()).child("foodListArrayList").child(key1.getText().toString()).child("status").setValue("Status:Available");
////
////        //  vwParentRow.setBackgroundColor();
////        mMessageDatabaseReference1.child(key.getText().toString()).child("foodListArrayList").child(key1.getText().toString()).child("status").setValue("Status:Available");
//
//        vwParentRow.refreshDrawableState();
//
//
//    }
//    public void goToUpi(View view) {
//        if(totalPrice>0) {
//            Uri uri = Uri.parse("upi://pay?pa=9572544000@upi&pn=Abhishek%20Kumar&tn=IITP%20FOOOD%20APP&am=" + totalPrice + "&cu=INR&url=https://mystar.co"); // missing 'http://' will cause crashed
//            //Log.d(TAG, "onClick: uri: "+uri);
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivityForResult(intent, 1);
//        }
//        else
//        {
//            Toast.makeText(this,"No item selected",Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
}
