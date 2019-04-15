package com.example.iitpfoodapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.iitpfoodapp.Quantity.finalFood;

import static com.example.iitpfoodapp.Quantity.finalQuantity;
import static com.example.iitpfoodapp.Quantity.finalTotal;
import static com.example.iitpfoodapp.Quantity.particularOrderPrice;
import static com.example.iitpfoodapp.Quantity.totalItems;
import static com.example.iitpfoodapp.Quantity.totalPrice;

public class FinalOrderList extends AppCompatActivity {

    public static final String ANONYMOUS = "anonymous";
   public static  String mUsername=ANONYMOUS;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase1;
    private DatabaseReference mMessageDatabaseReference1;
    private ChildEventListener mChildEventListner;
    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private finalFoodList finalfoodlist;
    FirebaseAuth firebaseAuth;
    public static String userReference;
    public static MessageAdapter adapter;
public static ArrayList<foodList> food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_order_list);

            firebaseAuth = FirebaseAuth.getInstance();
//        LinearLayout rootView = findViewById(R.id.rootView);
//        for (int i = 0; i < totalItems; i++) {
//            TextView t = new TextView(FinalOrderList.this);
//            t.setText(finalList.get(i));
//            rootView.addView(t);
//        }
//        TextView grandTotal = findViewById(R.id.grandTotal);
//        grandTotal.setText("Grand Total: " + totalPrice + "/-");
        //********************

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss");
        final String dateToStr = format.format(today);
        userReference=dateToStr+firebaseAuth.getCurrentUser().getUid();
        food=new ArrayList<foodList>();
        for(int i=0;i<totalItems;i++) {
            food.add(new foodList(finalFood.get(i), mUsername, finalQuantity.get(i), finalTotal.get(i)," Status:",""+dateToStr+firebaseAuth.getCurrentUser().getUid()));
        }
        adapter=new MessageAdapter(this,R.layout.final_list,food);
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
                if(totalItems>0) {
                    Toast.makeText(FinalOrderList.this, "Order sent for verification", Toast.LENGTH_SHORT).show();
//                for (int i = 0; i < totalItems; i++) {
//                    foodList friendlyMessage = new foodList(finalFood.get(i), mUsername,finalQuantity.get(i),finalTotal.get(i));
//                    mMessageDatabaseReference.push().setValue(friendlyMessage);
//                    foodList friendlyMessage1 = new foodList(finalFood.get(i), mUsername,finalQuantity.get(i),finalTotal.get(i));
//                    mMessageDatabaseReference1.push().setValue(friendlyMessage);
//                }
                    finalfoodlist = new finalFoodList(food);
                    mMessageDatabaseReference.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).setValue(finalfoodlist);
                    mMessageDatabaseReference1.child(dateToStr + firebaseAuth.getCurrentUser().getUid()).setValue(finalfoodlist);
                    // mMessageDatabaseReference.push().setValue(finalfoodlist);
                    //  mMessageDatabaseReference1.push().setValue(finalfoodlist);
                    Intent ii = new Intent(FinalOrderList.this, currentStatus.class);
                    startActivity(ii);

                }
                else
                { Toast.makeText(FinalOrderList.this, "No item selected", Toast.LENGTH_SHORT).show();

                }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(FinalOrderList.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                //Toast.makeText(FinalOrderList.this, "No item selected"+position, Toast.LENGTH_SHORT).show();
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
                                adapter.notifyDataSetChanged();
                                Toast.makeText(FinalOrderList.this,""+sub,Toast.LENGTH_SHORT).show();

                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }
    @Override
    protected void onStart()
    {
        super.onStart();
        TextView tp=findViewById(R.id.grandTotal);
        tp.setText("Grand Total:"+totalPrice+"/-");
        //Toast.makeText(getApplicationContext(),"Now onStart() calls", Toast.LENGTH_LONG).show(); //onStart Called
    }



//    public void goToUpi(View view) {
//
//        Uri uri = Uri.parse("upi://pay?pa=9572544000@upi&pn=Abhishek%20Kumar&tn=IIIP%20FOOOD%20APP&am=" + totalPrice + "&cu=INR&url=https://mystar.co"); // missing 'http://' will cause crashed
//        //Log.d(TAG, "onClick: uri: "+uri);
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivityForResult(intent, 1);
//
//
//
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//        if (requestCode == 1) {
//            if (resultCode == RESULT_OK) {
//                //payment was successful
//                Toast.makeText(this,"hi",Toast.LENGTH_SHORT).show();
//            }else if (resultCode == RESULT_CANCELED) {
//                //payment was canceled
//                Toast.makeText(this,"hi",Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

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
//        TextView tp=findViewById(R.id.grandTotal);
//        tp.setText("Grand Total:"+totalPrice+"/-");
//        finalFood.remove(val);
//        finalQuantity.remove(val);
//    finalTotal.remove(val);
//        food.remove(val);
//        totalItems--;
//        particularOrderPrice.remove(val);
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

}