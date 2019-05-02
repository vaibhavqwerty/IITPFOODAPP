package com.example.iitpfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.iitpfoodapp.currentStatus.random;

public class OrderCodePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_code_page);
        setTitle("Order Code");
        TextView tt=findViewById(R.id.ordercoding);
        tt.setText("Your ORDER CODE is:"+random);
        Button bb=findViewById(R.id.retres);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(OrderCodePage.this,Restaurants.class);
                startActivity(i);
            }
        });
    }
}
