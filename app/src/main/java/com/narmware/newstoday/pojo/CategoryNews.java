package com.narmware.newstoday.pojo;

/**
 * Created by rohitsavant on 06/04/18.
 */

public class CategoryNews {
    int id;
    int featured_media;
    String date,slug,link;
    Excpert excerpt;
    Title title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Excpert getExcpert() {
        return excerpt;
    }

    public void setExcpert(Excpert excpert) {
        this.excerpt = excpert;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public int getFeatured_media() {
        return featured_media;
    }

    public void setFeatured_media(int featured_media) {
        this.featured_media = featured_media;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Excpert getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(Excpert excerpt) {
        this.excerpt = excerpt;
    }
}
