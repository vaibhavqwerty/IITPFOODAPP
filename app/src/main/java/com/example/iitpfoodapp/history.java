package com.example.iitpfoodapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.iitpfoodapp.FinalOrderList.mUsername;
//import static com.example.iitpfoodapp.MainActivity.UserUniqueId;

public class history extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabaseH;
    private DatabaseReference mMessageDatabaseReferenceH;
    private ChildEventListener mChildEventListnerH;
    private ArrayAdapter<String> nameAdapterH;
    private ListView mMessageListViewH;
    public static CounterAdapter mcounterAdapter;
    public static ArrayList<finalFoodList> listOfListH;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mMessageListViewH=findViewById(R.id.historyList);
        setTitle("Payment History");
        listOfListH=new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabaseH=FirebaseDatabase.getInstance();
        mMessageDatabaseReferenceH =mFirebaseDatabaseH.getReference().child(firebaseAuth.getCurrentUser().getUid()).child("History");

        final List<String> nameList=new ArrayList<>();
        nameAdapterH = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);

        mMessageListViewH.setAdapter(nameAdapterH);
        mMessageListViewH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String  f=nameList.get(position);
                Intent intent = new Intent(getBaseContext(), historyList.class);
//        intent.putExtra("EXTRA_FOOD_NAME", f.getFoodName());
//        intent.putExtra("EXTRA_FOOD_PRICE", f.getFoodPrice());
//        intent.putExtra("EXTRA_PRICE",f.getPrice());
                mcounterAdapter = new CounterAdapter(history.this, R.layout.counter_list, listOfListH.get(position).getFoodListArrayList());
                startActivity(intent);

            }
        });


        mChildEventListnerH= new ChildEventListener() {
            int ii=0;
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                finalFoodList f=dataSnapshot.getValue(finalFoodList.class);
                String order=dataSnapshot.child("OrderCode").getValue().toString();
                String dd=dataSnapshot.child("Date and Time").getValue().toString();
                String pp=dataSnapshot.child("Price").getValue().toString();

                //  Toast.makeText(getApplicationContext(),dataSnapshot.getKey(),Toast.LENGTH_SHORT).show();
                foodList f1=dataSnapshot.getValue(foodList.class);
                nameAdapterH.add(order+"\nDate and Time: "+dd+"\nTotal Price: "+pp+"/-");
                listOfListH.add(f);
//                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
//                    String key=childSnapshot.getKey();
//                    Log.i(TAG,key);
//                }
//                mMessageAdapter.add(f.getFoodListArrayList().get(0));
//                friendlyMessages = f.getFoodListArrayList();
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
        mMessageDatabaseReferenceH.addChildEventListener(mChildEventListnerH);

    }
}
