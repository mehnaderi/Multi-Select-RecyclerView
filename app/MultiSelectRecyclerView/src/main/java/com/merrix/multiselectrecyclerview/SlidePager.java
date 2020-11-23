package com.merrix.multiselectrecyclerview;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class SlidePager extends FragmentStatePagerAdapter
{
    private List<Fragment> fragmentList;
    private Activity activity;
    private final int marginDp;
    private int fullWidth;
    public SlidePager(FragmentManager fm, List<Fragment> fragmentList, Activity activity, int marginDp)
    {
        super(fm);
        this.fragmentList = fragmentList;
        this.activity = activity;
        this.marginDp = marginDp;
        fullWidth = GeneralFunctions.getWidthInDP(activity);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public float getPageWidth(int position) {

        float newWidth = (float) (fullWidth - marginDp)/fullWidth;
        return newWidth;
    }
}
