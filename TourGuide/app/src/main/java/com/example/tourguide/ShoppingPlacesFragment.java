package com.example.tourguide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class ShoppingPlacesFragment extends Fragment {


    public ShoppingPlacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.item_list, container, false);

        final ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(getString(R.string.primark), getString(R.string.address_1), R.drawable.shop ));
        items.add(new Item(getString(R.string.m_s), getString(R.string.address_2), R.drawable.shopp));
        items.add(new Item(getString(R.string.next), getString(R.string.address_3), R.drawable.shop));
        items.add(new Item(getString(R.string.lewin),getString(R.string.address_1) , R.drawable.shop));
        items.add(new Item(getString(R.string.lacoste), getString(R.string.address_2), R.drawable.shopp ));

        ItemAdapter arrayAdapter = new ItemAdapter(getActivity(), items);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);

        return rootView;
    }
}