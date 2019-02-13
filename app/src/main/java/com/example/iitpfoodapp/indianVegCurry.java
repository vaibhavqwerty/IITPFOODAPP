package com.example.iitpfoodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class indianVegCurry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_veg_curry);
        ArrayList<FoodItem> food= new ArrayList<FoodItem>();
        food.add(new FoodItem("MIX VEG.","70/-"));
        food.add(new FoodItem("VEG. KOFTA","100/-"));
        food.add(new FoodItem("MUTTER PANEER","120/-"));
        food.add(new FoodItem("ALOO JEERA /ALOO DUM","70/-"));
        food.add(new FoodItem("MUSHROOM DO PYAZA","125/-"));
        food.add(new FoodItem("MUSHROOM BT. MASALA","120/-"));
        food.add(new FoodItem("MUSHROOM KADHAI","125/-"));
        food.add(new FoodItem("MUSHROOM HANDI","130/-"));
        food.add(new FoodItem("MUSHROOM DEHATI","150/-"));
        food.add(new FoodItem("PANEER DO PYAZA","120/-"));
        food.add(new FoodItem("PANEER BT. MASALA","120"));
        food.add(new FoodItem("PANEER KADHAI","125/-"));
        food.add(new FoodItem("PANEER HANDI","130/-"));
        food.add(new FoodItem("PANEER DEHATI","150/-"));
        food.add(new FoodItem("MIX VEGETABLES","70/-"));
        food.add(new FoodItem("PANEER MAKHANI","140/-"));
        food.add(new FoodItem("PANEER MASALA","130/-"));
        food.add(new FoodItem("PANEER BHURJI","100/-"));
        food.add(new FoodItem("DAL TADKA/DAL FRY","60/-"));

        FoodItemAdapter adapter =new FoodItemAdapter(this,food);
        ListView listView=findViewById(R.id.listIndianVegCurry);
        listView.setAdapter(adapter);

    }
}
