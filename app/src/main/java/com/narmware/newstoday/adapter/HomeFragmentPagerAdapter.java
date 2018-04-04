package com.narmware.newstoday.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashwini Palve on 04/04/2018.
 */

public class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter
 {
     Context mContext;
     private final List<Fragment> mFragmentList = new ArrayList<>();
     private final List<String> mFragmentTitleList = new ArrayList<>();

     public HomeFragmentPagerAdapter(FragmentManager fm, Context mContext) {
         super(fm);
         this.mContext = mContext;
     }


     @Override
     public Fragment getItem(int position) {
         return mFragmentList.get(position);
     }

     @Override
     public int getCount() {
         return mFragmentList.size();
     }


     public void addFragment(Fragment fragment, String title) {
         mFragmentList.add(fragment);
         mFragmentTitleList.add(title);
     }



     @Override
     public CharSequence getPageTitle(int position) {
         return  mFragmentTitleList.get(position);

     }
 }

