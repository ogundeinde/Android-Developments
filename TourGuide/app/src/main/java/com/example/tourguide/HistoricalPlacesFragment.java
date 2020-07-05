package com.example.tourguide;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoricalPlacesFragment extends Fragment {

    public HistoricalPlacesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.item_list, container, false);

        final ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(getString(R.string.tower), getString(R.string.address_1) , R.drawable.tower ));
        items.add(new Item(getString(R.string.square), getString(R.string.address_2), R.drawable.square));
        items.add(new Item(getString(R.string.big_ben), getString(R.string.address_3), R.drawable.big_ben));
        items.add(new Item(getString(R.string.abbey),getString(R.string.address_4) , R.drawable.abbey));
        items.add(new Item(getString(R.string.palace), getString(R.string.address_5) , R.drawable.palace ));

        ItemAdapter arrayAdapter = new ItemAdapter(getActivity(), items);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(arrayAdapter);

        return rootView;
    }
}