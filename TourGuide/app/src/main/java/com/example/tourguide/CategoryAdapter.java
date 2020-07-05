package com.example.tourguide;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    private  Context mContext;
    public CategoryAdapter(Context context, FragmentManager fm){
        super(fm);
        this.mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new HistoricalPlacesFragment();
        }
        else if(position == 1){
            return new FoodFragment();
        }
        else if(position == 2){
            return new ShoppingPlacesFragment();
        }
        else {
            return new HotelsFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.history_places);
        } else if (position == 1) {
            return mContext.getString(R.string.food);
        } else if (position == 2) {
            return mContext.getString(R.string.shopping_place);
        } else {
            return mContext.getString(R.string.hotels);
        }
    }
}
