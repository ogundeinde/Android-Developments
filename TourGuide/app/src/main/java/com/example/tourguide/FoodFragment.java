package com.example.tourguide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class FoodFragment extends Fragment {

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.item_list, container, false);

        final ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(getString(R.string.subway), getString(R.string.address_1) , R.drawable.subway ));
        items.add(new Item(getString(R.string.burger), getString(R.string.address_2), R.drawable.burger));
        items.add(new Item(getString(R.string.sushi), getString(R.string.address_3), R.drawable.sushi));
        items.add(new Item(getString(R.string.nandos),getString(R.string.address_4) , R.drawable.nandos));
        items.add(new Item(getString(R.string.pizza), getString(R.string.address_5) , R.drawable.pizza ));

        ItemAdapter arrayAdapter = new ItemAdapter(getActivity(), items);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);

        return rootView;
    }
}