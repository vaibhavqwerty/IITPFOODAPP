package com.example.iitpfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class snacks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);
       final ArrayList<FoodItem> food = new ArrayList<FoodItem>();
        food.add(new FoodItem("SAMOSA","7/-",7));
        food.add(new FoodItem("KACHORI","7/-",7));
        food.add(new FoodItem("PANEER PATIES","20/-",20));
        food.add(new FoodItem("ALOO PATIES","15/-",15));
        food.add(new FoodItem("VEG. CUTLET","10/-",10));
        food.add(new FoodItem("PANEER BURGER","25/-",25));
        food.add(new FoodItem("VEG. BURGER","30/-",30));
        food.add(new FoodItem("MINI VEG. PIZZA","35/-",35));
        food.add(new FoodItem("MNI CHICKEN PIZZA","50/-",50));
        food.add(new FoodItem("PAPRI CHAT","25/-",25));
        food.add(new FoodItem("TIKKI CHAT","25/-",25));
        food.add(new FoodItem("SAMOSA CHAT","25/-",25));
        food.add(new FoodItem("VEG. PAKODA","60/-",60));
        food.add(new FoodItem("PANEER","70/-",70));
        food.add(new FoodItem("EGG PAKODA","70/-",70));
        food.add(new FoodItem("PAW BHAJI","35/-",35));
        FoodItemAdapter Adapter = new FoodItemAdapter(this,food);

        ListView listView =  findViewById(R.id.listSnacks);

        listView.setAdapter(Adapter);

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
