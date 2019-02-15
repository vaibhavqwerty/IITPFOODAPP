package com.example.iitpfoodapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    public void goToUpi(View view) {

        Uri uri = Uri.parse("upi://pay?pa=9572544000@upi&pn=Abhishek%20Kumar&tn=IIIP%20FOOOD%20APP&am="+totalPrice+"&cu=INR&url=https://mystar.co"); // missing 'http://' will cause crashed
        //Log.d(TAG, "onClick: uri: "+uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivityForResult(intent, 1);


    }
}
