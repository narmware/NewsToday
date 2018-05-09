package com.narmware.newstoday.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.alexvasilkov.foldablelayout.FoldableListLayout;
import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.narmware.newstoday.R;
import com.narmware.newstoday.activity.DetailedNewsActivity;
import com.narmware.newstoday.customfonts.MyTextView;
import com.narmware.newstoday.helpers.Constants;
import com.narmware.newstoday.pojo.HomeNews;
import com.squareup.picasso.Picasso;

import org.sufficientlysecure.htmltextview.HtmlTextView;

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
    @BindView(R.id.news_desc)protected HtmlTextView mTxtDesc;
    @BindView(R.id.news_name)protected MyTextView mTxtName;
    @BindView(R.id.news_img)protected ImageView mImgNews;
    @BindView(R.id.news_date)protected MyTextView mTxtDate;
    @BindView(R.id.likeBtn)protected LikeButton mBtnLike;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_news, parent, false);

        ButterKnife.bind(this,itemView);

        final MyTextView mTxtLike=itemView.findViewById(R.id.txt_like);

          Glide.with(mContext)
                .load(homeNews.get(position).getImg_path())
                /*.fit()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)*/
                .into(mImgNews);
try {
    //mImgNews.setBackgroundColor(homeNews.get(position).getNews_color());
    mTxtTitle.setText(homeNews.get(position).getNews_title());
    // mTxtDesc.setText(homeNews.get(position).getNews_desc());
    mTxtName.setText("# " + homeNews.get(position).getNews_name());
    mTxtDate.setText(homeNews.get(position).getNews_date());
   // mTxtDesc.setText(homeNews.get(position).getNews_desc());
    mTxtDesc.setHtml(homeNews.get(position).getNews_desc());
}catch(Exception e)
{
    e.printStackTrace();
}

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext,homeNews.get(position).getNews_link(),Toast.LENGTH_SHORT).show();
                Log.d("condata",homeNews.get(position).getNews_content());


                Intent intent=new Intent(mContext, DetailedNewsActivity.class);
                intent.putExtra(Constants.NEWS_NAME,homeNews.get(position).getNews_name());
                intent.putExtra(Constants.NEWS_LINK,homeNews.get(position).getNews_link());

                mContext.startActivity(intent);

            }
        });

        mBtnLike.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mTxtLike.setText("1 Likes");

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                mTxtLike.setText("0 Likes");
            }
        });
        return itemView;
    }
}
