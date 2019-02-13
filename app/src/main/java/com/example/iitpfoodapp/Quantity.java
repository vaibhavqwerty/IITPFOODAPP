package com.example.iitpfoodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Quantity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);


        String food_name = getIntent().getStringExtra("EXTRA_FOOD_NAME");
        String food_price = getIntent().getStringExtra("EXTRA_FOOD_PRICE");
        int fprice = getIntent().getIntExtra("EXTAR_PRICE", 0);
        TextView fname = findViewById(R.id.itemName);
        fname.setText(food_name + "   " + food_price);



    }
    int quan=0;
    public void decrease(View view)
    {
        if(quan>0)
        {
            quan--;
            display();

        }
    }
    public void increase(View view)
    {
        if(quan<50)
        {
            quan++;
            display();

        }
    }
    private void display()
    {
        TextView t=findViewById(R.id.QuantityVal);
        t.setText(" "+ quan);
    }

}
