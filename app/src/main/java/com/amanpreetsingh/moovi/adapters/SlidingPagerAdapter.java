package com.amanpreetsingh.moovi.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.amanpreetsingh.moovi.fragments.MoviesFragment;
import com.amanpreetsingh.moovi.fragments.TVFragment;

/**
 * Created by Admin on 1/27/2016.
 */
public class SlidingPagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs = 2;
    Context mContext;

    public SlidingPagerAdapter(Context context, FragmentManager fm){
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("SlidingPagerAdapter", "getItem called for position "+position);
        if (position == 0){
            return new MoviesFragment();
        }
        else if (position == 2){
            return new TVFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "MOVIES";
        }
        else if (position == 1) {
            return "TV SHOWS";
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
