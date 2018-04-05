package com.narmware.newstoday.pojo;

/**
 * Created by rohitsavant on 04/04/18.
 */

public class News {
    int news_color;
    String news_title,news_desc,img_path;

    public News(String img_path, String news_title, String news_desc) {
        this.img_path = img_path;
        this.news_title = news_title;
        this.news_desc = news_desc;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
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
}
