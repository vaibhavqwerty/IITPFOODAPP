package com.example.iitpfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class snacks extends AppCompatActivity {

    private EditText filterText;
    private FoodItemAdapter Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);
        filterText = (EditText) findViewById(R.id.editText);
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
        Adapter = new FoodItemAdapter(this,food);

        final ListView listView =  findViewById(R.id.listSnacks);

        listView.setAdapter(Adapter);

  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FoodItem f=Adapter.getItem(position);
        filterText.setText(f.getFoodName());
        Intent intent = new Intent(getBaseContext(), Quantity.class);
        intent.putExtra("EXTRA_FOOD_NAME", f.getFoodName());
        intent.putExtra("EXTRA_FOOD_PRICE", f.getFoodPrice());
        intent.putExtra("EXTRA_PRICE",f.getPrice());
        startActivity(intent);

      }
});


        filterText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                snacks.this.Adapter.getFilter().filter(s);
                //MainActivity.this.listAdapter.getFilter();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

}
