package com.example.iitpfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class rotiRoll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roti_roll);
        setTitle("Roti/Roll/Paratha");
        final ArrayList<FoodItem> food=new ArrayList<FoodItem>();
        food.add(new FoodItem("PLAIN PARATHA","15/-",15));
        food.add(new FoodItem("ALOO PARATHA","20/-",20));
        food.add(new FoodItem("ONION PARATHA","20/-",20 ));
        food.add(new FoodItem("PANEER PARATHA","35/-",35));
        food.add(new FoodItem("METHI PARAHTA","20/-",20));
        food.add(new FoodItem("LACCHA PARATHA","20/-",20));
        food.add(new FoodItem("VEG. ROLL","25/-",25));
        food.add(new FoodItem("PANEER ROLL","35/-",35));
        food.add(new FoodItem("EGG ROLL","30/-",30));
        food.add(new FoodItem("CHICKEN ROLL","50/-",50));


        FoodItemAdapter adapter=new FoodItemAdapter(this,food);
        ListView listView=findViewById(R.id.listRoll);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.final_list, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){


            case R.id.final_menu:
                Intent i=new Intent(rotiRoll.this,FinalOrderList.class);
                startActivity(i);
                return true;





            default:
                return super.onOptionsItemSelected(item);
        }}
}
