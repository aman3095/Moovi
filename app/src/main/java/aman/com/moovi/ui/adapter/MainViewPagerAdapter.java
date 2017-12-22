package aman.com.moovi.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import aman.com.moovi.Utils;
import aman.com.moovi.ui.main.ProgramListFragment;

/**
 * Created by amanpreetsingh on 12/21/17.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    int mTabCount;

    public MainViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        mTabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ProgramListFragment movie_fragment = new ProgramListFragment();
                Bundle movie_args = new Bundle();
                movie_args.putString(Utils.PROGRAM_TYPE, Utils.MOVIE);
                movie_fragment.setArguments(movie_args);
                return movie_fragment;
            case 1:
                ProgramListFragment tv_fragment = new ProgramListFragment();
                Bundle tv_args = new Bundle();
                tv_args.putString(Utils.PROGRAM_TYPE, Utils.TV_SHOW);
                tv_fragment.setArguments(tv_args);
                return tv_fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }
}
