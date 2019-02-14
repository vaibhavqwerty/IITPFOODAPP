package com.example.iitpfoodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.iitpfoodapp.Quantity.finalList;
import static com.example.iitpfoodapp.Quantity.totalItems;
import static com.example.iitpfoodapp.Quantity.totalPrice;

public class FinalOrderList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_order_list);
        LinearLayout rootView=findViewById(R.id.rootView);
    for(int i=0;i<totalItems;i++)
    {
        TextView t=new TextView(FinalOrderList.this);
        t.setText(finalList.get(i));
        rootView.addView(t);
    }
    TextView grandTotal=findViewById(R.id.grandTotal);
    grandTotal.setText("Grand Total: "+totalPrice+"/-");
    }
}
