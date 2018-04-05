package com.narmware.newstoday.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.narmware.newstoday.R;
import com.narmware.newstoday.activity.DetailedNewsActivity;
import com.narmware.newstoday.customfonts.MyTextView;
import com.narmware.newstoday.pojo.News;

import java.util.List;


/**
 * Created by Lincoln on 31/03/16.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

     List<News> news;
    Context mContext;
    protected Dialog mNoConnectionDialog;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyTextView mthumb_title,mthumb_Desc;
       ImageView mImgFrame;
        News mItem;


        public MyViewHolder(View view) {
            super(view);
            mthumb_title= view.findViewById(R.id.news_title);
            mthumb_Desc= view.findViewById(R.id.news_desc);
            mImgFrame=view.findViewById(R.id.news_img);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(mContext, mItem.getNews_title(), Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(mContext, DetailedNewsActivity.class);
                    intent.putExtra("news_name","News name");
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public NewsAdapter(Context context, List<News> news) {
        this.mContext = context;
        this.news = news;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        News frame = news.get(position);


       /* Picasso.with(mContext)
                .load(frame.getPhoto_path())
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.mImgFrame);*/
        holder.mImgFrame.setBackgroundColor(frame.getNews_color());
        holder.mthumb_title.setText(frame.getNews_title());
        holder.mthumb_Desc.setText(frame.getNews_desc());
        holder.mItem=frame;
    }

    @Override
    public int getItemCount() {
        return news.size();
    }


}