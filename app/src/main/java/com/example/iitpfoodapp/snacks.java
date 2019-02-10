package com.example.iitpfoodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class snacks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);
        ArrayList<FoodItem> food = new ArrayList<FoodItem>();
        food.add(new FoodItem("SAMOSA/KACHORI","7/-"));
        food.add(new FoodItem("PANEER PATIES","20/-"));
        food.add(new FoodItem("ALOO PATIES","15/-"));
        food.add(new FoodItem("VEG. CUTLET","10/-"));
        food.add(new FoodItem("PANEER BURGER","25/-"));
        food.add(new FoodItem("VEG. BURGER","30/-"));
        food.add(new FoodItem("MINI VEG. PIZZA","35/-"));
        food.add(new FoodItem("MNI CHICKEN PIZZA","50/-"));
        food.add(new FoodItem("CHAT(Tikki/Papri/Samosa)","25/-"));
        food.add(new FoodItem("VEG. PAKODA","60/-"));
        food.add(new FoodItem("PANEER/EGG PAKODA","70/-"));
        food.add(new FoodItem("PAW BHAJI","35/-"));
        FoodItemAdapter Adapter = new FoodItemAdapter(this,food);

        ListView listView =  findViewById(R.id.listSnacks);

        listView.setAdapter(Adapter);




    }

}
