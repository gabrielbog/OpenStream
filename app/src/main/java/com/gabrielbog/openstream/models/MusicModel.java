package com.gabrielbog.openstream.models;

public class MusicModel {
    String title;
    String author;
    String link;
    Boolean isFavorite;

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
