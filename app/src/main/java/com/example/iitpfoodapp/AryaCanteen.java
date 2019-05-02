package com.example.iitpfoodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AryaCanteen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arya_canteen);
        setTitle("Arya Services");
        TextView snacks=findViewById(R.id.snacks);
        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AryaCanteen.this,snacks.class);
                startActivity(i);
            }
        });
        TextView cake=findViewById(R.id.cake);
        cake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AryaCanteen.this,cake.class);
                startActivity(i);

            }
        });

        TextView indianVegCurry=findViewById(R.id.indianVegCurry);
        indianVegCurry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AryaCanteen.this,indianVegCurry.class);
                startActivity(i);

            }
        });


        TextView rollRoti=findViewById(R.id.rotiRoll);
        rollRoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AryaCanteen.this,rotiRoll.class);
                startActivity(i);

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
                Intent i=new Intent(AryaCanteen.this,FinalOrderList.class);
                startActivity(i);
                return true;





            default:
                return super.onOptionsItemSelected(item);
        }}
}
