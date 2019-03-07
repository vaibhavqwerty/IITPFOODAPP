package com.example.iitpfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class cake extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake);
        final ArrayList<FoodItem> food=new ArrayList<FoodItem>();
        food.add(new FoodItem("BLACK FOREST","30/-",30));
        food.add(new FoodItem("MANGO","30/-",30));
        food.add(new FoodItem("PINEAPPLE","30/-",30));
        food.add(new FoodItem("STRAWBERRY","30/-",30));
        food.add(new FoodItem("CAKE PER POUND","250/-",250));

        FoodItemAdapter adapter=new FoodItemAdapter(this,food);
        ListView listView=findViewById(R.id.listCake);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodItem f=food.get(position);
                Intent intent = new Intent(getBaseContext(), Quantity.class);
                intent.putExtra("EXTRA_FOOD_NAME", f.getFoodName());
                intent.putExtra("EXTRA_FOOD_PRICE", f.getFoodPrice());
                intent.putExtra("EXTRA_PRICE",f.getPrice());
                startActivity(intent);

            }
        });


    }
}
