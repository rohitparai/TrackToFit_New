package com.swatitiwari.tracktofit.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageOnlineAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentTitles = new ArrayList<>();
    Context context;
    public ViewPageOnlineAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        this.context = context;
    }
    //add fragment to the viewpager
    public void addFragment(Fragment fragment, String title){
        fragments.add(fragment);
        fragmentTitles.add(title);

    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
       /* if (position==3){
            Toast.makeText(context, "Expired", Toast.LENGTH_SHORT).show();
        }*/
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
    //to setup title of the tab layout
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}
