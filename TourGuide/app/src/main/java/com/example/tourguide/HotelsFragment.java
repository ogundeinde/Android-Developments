package com.example.tourguide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class HotelsFragment extends Fragment {

    public HotelsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.item_list, container, false);

        final ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(getString(R.string.four), getString(R.string.address_1) , R.drawable.hotel ));
        items.add(new Item(getString(R.string.london), getString(R.string.address_2), R.drawable.hotel));
        items.add(new Item(getString(R.string.royal), getString(R.string.address_3), R.drawable.hotelll));
        items.add(new Item(getString(R.string.oriental),getString(R.string.address_4), R.drawable.hotel));
        items.add(new Item(getString(R.string.place), getString(R.string.address_5) , R.drawable.hotel ));

        ItemAdapter arrayAdapter = new ItemAdapter(getActivity(), items);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);

        return rootView;
    }
}