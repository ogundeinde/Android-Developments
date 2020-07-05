package com.example.tourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(@NonNull Context context, ArrayList<Item> item) {
        super(context, 0, item);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item currentItem = getItem(position);
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }

        TextView textView = (TextView) listItemView.findViewById(R.id.name);
        textView.setText(currentItem.getItemName());

        TextView textView1 = (TextView) listItemView.findViewById(R.id.description);
        textView1.setText(currentItem.getItemDescription());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView);
        imageView.setImageResource(currentItem.getItemImage());

        return listItemView;
    }
}
