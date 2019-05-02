package com.example.iitpfoodapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import static com.example.iitpfoodapp.history.mcounterAdapter;

public class historyList extends AppCompatActivity {
    private ListView OrderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        OrderView=findViewById(R.id.HistoryList);
//        final List<foodList> friendlyMessages = new ArrayList<>();
//         mMessageAdapter = new MessageAdapter(this, R.layout.final_list,friendlyMessages);
        OrderView.setAdapter(mcounterAdapter);
    }
}
