package com.narmware.newstoday.pojo;

/**
 * Created by Ashwini Palve on 05/04/2018.
 */

public class HomeMenu
{

    private String title;
    private int image;


    public HomeMenu(String title, int image) {
        this.title = title;
        this.image = image;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
