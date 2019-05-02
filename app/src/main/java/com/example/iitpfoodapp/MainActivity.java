package com.example.iitpfoodapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

import static com.example.iitpfoodapp.FinalOrderList.mUsername;

public class MainActivity extends AppCompatActivity {
   //Checking for Commit on the github
//private String mUsername;

       List<AuthUI.IdpConfig> providers = Arrays.asList(
               new AuthUI.IdpConfig.EmailBuilder().build(),
               new AuthUI.IdpConfig.GoogleBuilder().build());
   public static final int RC_SIGN_IN=1;
private FirebaseAuth mFirebaseAuth;
private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("Users");
//Authentication

        mFirebaseAuth=FirebaseAuth.getInstance();

        Button con =findViewById(R.id.con);
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               verifyUserExistence();




            }
        });



mAuthStateListener= new FirebaseAuth.AuthStateListener() {
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null)
        {

            //mUsername=user.getDisplayName();
          //user is signed in
           Toast.makeText(MainActivity.this,"You are now signed in", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //user is signed out
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false)
                            .setAvailableProviders(providers)
                            .setTheme(R.style.AppTheme)
                            .build(),
                    RC_SIGN_IN);

        }
    }
};

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;

            case R.id.history_menu:
                Intent i=new Intent(MainActivity.this,history.class);
                startActivity(i);
                return true;


            case R.id.Update_History:
                Intent iI=new Intent(MainActivity.this,SettingActivity.class);
                startActivity(iI);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==RC_SIGN_IN){
            if(resultCode==RESULT_OK){
                Toast.makeText(this,"Signed in!",Toast.LENGTH_SHORT).show();
            }
            else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this,"Sign in Canceled",Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    @Override
    protected void onPause(){
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }

    private void verifyUserExistence()
    {
        String currentUserId = mFirebaseAuth.getCurrentUser().getUid();
        //UserUniqueId=currentUserId;
        mMessageDatabaseReference.child(currentUserId).child("info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if((dataSnapshot.child("name").exists()))
                {
                    //sentToResturentsActivity();
                    Toast.makeText(MainActivity.this,"Welcome" , Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(MainActivity.this,Restaurants.class);
                    startActivity(i);
                }
                else
                {
                   // sentToSettingActivity();
                    Intent i =new Intent(MainActivity.this,SettingActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
