package com.picsapp.moatazbadawy.pojo4k.Adapter.Viewpager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.picsapp.moatazbadawy.pojo4k.Fragment.HomeFragment.HomeCategorieFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.HomeFragment.HomeFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.HomeFragment.HomeTopFragment;
import com.picsapp.moatazbadawy.pojo4k.Fragment.HomeFragment.HomeWallpapersFragment;
import com.picsapp.moatazbadawy.pojo4k.R;

public class ViewpagerHomeAdapter extends FragmentStatePagerAdapter {

    private HomeFragment mContext;

    public ViewpagerHomeAdapter(HomeFragment context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            String[] chooseImages = new String[0];
            return new HomeTopFragment();
        } else if (position == 1) {
            return new HomeCategorieFragment();
        } else {
            return new HomeWallpapersFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.home_top);
        } else if (position == 1) {
            return mContext.getString(R.string.home_more);
        } else {
            return mContext.getString(R.string.home_wallpaper);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}