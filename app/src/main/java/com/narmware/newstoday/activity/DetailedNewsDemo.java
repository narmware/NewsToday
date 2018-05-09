package com.narmware.newstoday.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.narmware.newstoday.R;

public class DetailedNewsDemo extends AppCompatActivity {
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_news_demo);

        toolbar =  findViewById(R.id.toolbar);
        collapsingToolbarLayout =  findViewById(R.id.CollapsingToolbarLayout);
        appBarLayout=findViewById(R.id.appBarLayout);

        //setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        toolbar.setTitle("News Title");
        // setSupportActionBar(toolbar);
         collapsingToolbarLayout.setTitleEnabled(false);


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
        {
            boolean isVisible = true;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
            {

                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar.setTitle("Title");
                    isVisible = true;
                } else if(isVisible) {
                    toolbar.setTitle("");
                    isVisible = false;
                }

                }
                });







        //  collapsingToolbarLayout.setTitle("Collapsing Toolbar");




    }
}
