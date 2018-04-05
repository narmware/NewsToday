package com.narmware.newstoday.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.narmware.newstoday.pojo.HomeMenu;

import java.util.ArrayList;

/**
 * Created by Ashwini Palve on 05/04/2018.
 */

public class HomeMenuAdapter extends BaseAdapter
{

    private final Activity mContext;
    private ArrayList<HomeMenu> mMenuList;

    public HomeMenuAdapter(Activity mContext, ArrayList<HomeMenu> mMenuList) {
        this.mContext = mContext;
        this.mMenuList = mMenuList;
    }

    @Override
    public int getCount() {
        return mMenuList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {/*
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View rowView=layoutInflater.inflate()
*/
        return null;
    }
}
