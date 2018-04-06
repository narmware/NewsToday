package com.narmware.newstoday.adapter;

import android.app.Activity;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.narmware.newstoday.R;
import com.narmware.newstoday.activity.Home;
import com.narmware.newstoday.fragment.HomeFragment;
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
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View rowView=layoutInflater.inflate(R.layout.menulist,null);

        TextView mTextView=rowView.findViewById(R.id.mTitle);
        ImageView mImageView=rowView.findViewById(R.id.mIcon);

        mTextView.setText(mMenuList.get(position).getTitle());
        mImageView.setImageResource(mMenuList.get(position).getImage());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home.drawer.closeDrawer(GravityCompat.START);
            }
        });
        return rowView;
    }
}
