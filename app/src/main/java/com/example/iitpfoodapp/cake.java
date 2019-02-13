package com.example.iitpfoodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class cake extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake);
        ArrayList<FoodItem> food=new ArrayList<FoodItem>();
        food.add(new FoodItem("BLACK FOREST","30/-",30));
        food.add(new FoodItem("MANGO","30/-",30));
        food.add(new FoodItem("PINEAPPLE","30/-",30));
        food.add(new FoodItem("STRAWBERRY","30/-",30));
        food.add(new FoodItem("CAKE PER POUND","250/-",250));

        FoodItemAdapter adapter=new FoodItemAdapter(this,food);
        ListView listView=findViewById(R.id.listCake);
        listView.setAdapter(adapter);

    }
}
