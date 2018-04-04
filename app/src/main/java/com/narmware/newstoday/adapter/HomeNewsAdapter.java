package com.narmware.newstoday.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.alexvasilkov.foldablelayout.FoldableListLayout;
import com.narmware.newstoday.R;
import com.narmware.newstoday.customfonts.MyTextView;
import com.narmware.newstoday.pojo.HomeNews;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rohitsavant on 04/04/18.
 */

public class HomeNewsAdapter extends BaseAdapter
{

    ArrayList<HomeNews> homeNews;
    Context mContext;
    @BindView(R.id.news_title)protected MyTextView mTxtTitle;
    @BindView(R.id.news_desc)protected MyTextView mTxtDesc;
    @BindView(R.id.news_img)protected ImageView mImgNews;

    public HomeNewsAdapter(ArrayList<HomeNews> homeNews, Context mContext) {
        this.homeNews = homeNews;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return homeNews.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_news, parent, false);

        ButterKnife.bind(this,itemView);

        mImgNews.setBackgroundColor(homeNews.get(position).getNews_color());
        mTxtTitle.setText(homeNews.get(position).getNews_title());
        mTxtDesc.setText(homeNews.get(position).getNews_desc());

        return itemView;
    }
}
