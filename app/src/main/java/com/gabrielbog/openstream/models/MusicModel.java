package com.gabrielbog.openstream.models;

import androidx.lifecycle.ViewModel;

public class MusicModel {

    private String title;
    private String author;
    private String link;
    private Boolean isFavorite;

    public MusicModel(String title, String author, String link)
    {
        this.title = title;
        this.author = author;
        this.link = link;
        this.isFavorite = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
