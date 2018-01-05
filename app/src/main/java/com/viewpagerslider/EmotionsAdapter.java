package com.viewpagerslider;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by aman on 2/1/18.
 */

public class EmotionsAdapter extends FragmentPagerAdapter {
    public EmotionsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position%2){
            case 0:return new EmotionsFragment();
            case 1:return new YellowFragment();
        }
        return new EmotionsFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
