package com.example.iitpfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Quantity extends AppCompatActivity {
  public static ArrayList<String> finalFood = new ArrayList<String>();
    public static ArrayList<String> finalQuantity = new ArrayList<String>();
    public static ArrayList<String> finalTotal = new ArrayList<String>();
    public static int totalPrice=0;
    public static int totalItems=0;
    int fprice;
    String food_name;
    String food_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);


         food_name = getIntent().getStringExtra("EXTRA_FOOD_NAME");
       food_price = getIntent().getStringExtra("EXTRA_FOOD_PRICE");
        fprice = getIntent().getIntExtra("EXTRA_PRICE", 0);
        TextView fname = findViewById(R.id.itemName);
        fname.setText(food_name + "   " + food_price);

TextView orderMore=findViewById(R.id.orderMore);
orderMore.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(Quantity.this,AryaCanteen.class);
        startActivity(i);
    }
});


final TextView finalOrderList=findViewById(R.id.finalOrderList);
finalOrderList.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(Quantity.this,FinalOrderList.class);
        startActivity(i);
    }
});


    }
    int quan=0;
    public void AddToBasket(View view)
    {  totalItems++;
        //finalList.add(" "+food_name+" ("+food_price+")  Qty:"+quan+"   Total:"+quan*fprice+"/-");
        finalFood.add(" "+food_name+" ("+food_price+") ");
        finalQuantity.add(" Qty: "+quan+" ");
        finalTotal.add(" Total:"+quan*fprice+"/-");
        totalPrice=totalPrice+(quan*fprice);
        Toast.makeText(Quantity.this,""+food_name+" Quantity:"+quan+" Added to basket",Toast.LENGTH_SHORT).show();
    }
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
