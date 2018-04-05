package com.narmware.newstoday.pojo;

/**
 * Created by rohitsavant on 04/04/18.
 */

public class HomeNews {
    int news_color;
    String news_title,news_desc,news_name;

    public HomeNews(int news_color, String news_title, String news_desc,String news_name) {
        this.news_color = news_color;
        this.news_title = news_title;
        this.news_desc = news_desc;
        this.news_name=news_name;
    }

    public int getNews_color() {
        return news_color;
    }

    public void setNews_color(int news_color) {
        this.news_color = news_color;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_desc() {
        return news_desc;
    }

    public void setNews_desc(String news_desc) {
        this.news_desc = news_desc;
    }

    public String getNews_name() {
        return news_name;
    }

    public void setNews_name(String news_name) {
        this.news_name = news_name;
    }
}
