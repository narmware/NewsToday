package com.narmware.newstoday;

import android.app.Application;

import javax.inject.Singleton;

/**
 * Created by rohitsavant on 05/03/18.
 */

@Singleton
public class MyApplication extends Application {

    public static final String URL_SERVER="http://demo.narmware.com/ash/news/wp-json/wp/v2/";
    public static final String URL_CAT_NEWS=URL_SERVER+"posts/";
    public static final String URL_NEWS_IMAGE=URL_SERVER+"media/";

}
