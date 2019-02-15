package com.example.iitpfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   //Checking for Commit on the github
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login Account");

        TextView signUp=findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp =new Intent(MainActivity.this, signUp.class);
                startActivity(signUp);
            }
        });
        TextView signIn=findViewById(R.id.LOGIN);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn =new Intent(MainActivity.this, Restaurants.class);
                startActivity(signIn);

         TextView text = (TextView) findViewById(R.id.signingoogle);
                 text.setMovementMethod(LinkMovementMethod.getInstance());
            }
        });


    }
}
