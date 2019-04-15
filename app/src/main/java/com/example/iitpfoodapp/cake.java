package com.example.iitpfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class cake extends AppCompatActivity {
    private FoodItemAdapter adapter;
    private EditText filterText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake);
        filterText=(EditText)findViewById(R.id.editText);
        final ArrayList<FoodItem> food=new ArrayList<FoodItem>();
        food.add(new FoodItem("BLACK FOREST","30/-",30));
        food.add(new FoodItem("MANGO","30/-",30));
        food.add(new FoodItem("PINEAPPLE","30/-",30));
        food.add(new FoodItem("STRAWBERRY","30/-",30));
        food.add(new FoodItem("CAKE PER POUND","250/-",250));

         adapter=new FoodItemAdapter(this,food);
        ListView listView=findViewById(R.id.listCake);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodItem f=adapter.getItem(position);
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
                cake.this.adapter.getFilter().filter(s);
                //MainActivity.this.listAdapter.getFilter();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


    }
}
