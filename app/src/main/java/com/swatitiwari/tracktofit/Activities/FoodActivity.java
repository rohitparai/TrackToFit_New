package com.swatitiwari.tracktofit.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.swatitiwari.tracktofit.R;
import com.swatitiwari.tracktofit.fragments.NonVegFood;
import com.swatitiwari.tracktofit.fragments.VegFood;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {
    ViewPager viewpagerFood;
    TabLayout tabs;
    VegFood vegFood;
    NonVegFood nonVegFood;
    ImageView imBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        imBack = findViewById(R.id.imBack);
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodActivity.this, MainActivity.class));
                finish();
            }
        });
        init();
    }
    public void init(){
        viewpagerFood= findViewById(R.id.viewPager);
        tabs= findViewById(R.id.tabs);
        vegFood = new VegFood();
        nonVegFood = new NonVegFood();
        tabs.setupWithViewPager(viewpagerFood);
        ViewPageFoodAdapter viewPagerAdapter = new ViewPageFoodAdapter(getSupportFragmentManager(), 0,this);
        //add fragments and set the adapter
        viewPagerAdapter.addFragment(vegFood, "Veg Food");
        viewPagerAdapter.addFragment(nonVegFood, "NonVeg Food");
        viewpagerFood.setAdapter(viewPagerAdapter);
        viewpagerFood.setOffscreenPageLimit(3);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpagerFood.setCurrentItem(tab.getPosition());
              /*  if (tab.getPosition() == 0) {

                }
                if (tab.getPosition() == 1) {

                }
               */
                Log.e("TAG", "onTabSelected: " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
    public static class ViewPageFoodAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitles = new ArrayList<>();
        Context context;
        public ViewPageFoodAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
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
}