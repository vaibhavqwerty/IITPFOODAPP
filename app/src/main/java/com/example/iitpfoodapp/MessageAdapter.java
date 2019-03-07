package com.example.iitpfoodapp;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<foodList> {
    public MessageAdapter(Context context, int resource, List<foodList> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.final_list, parent, false);
        }

        //ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView quantityTextView = (TextView) convertView.findViewById(R.id.quantityTextView);
        TextView totalPriceTextView = (TextView) convertView.findViewById(R.id.totalPriceTextView);

        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);

        foodList message = getItem(position);


        messageTextView.setVisibility(View.VISIBLE);
        //photoImageView.setVisibility(View.GONE);
        messageTextView.setText(message.getText());
        quantityTextView.setText(message.getQuantity());
        totalPriceTextView.setText(message.getTotalPrice());
        authorTextView.setText(message.getName());

        return convertView;
    }
}



