package com.example.user.mycardapp.Data.DataBase;

import com.example.user.mycardapp.Data.NewsItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "news")
public class DBModel {

    @PrimaryKey
    @NonNull
    private int id;
    @NonNull
    private String category;
    @Nullable
    private String title;
    @Nullable
    private String date;
    @Nullable
    private String text;
    @Nullable
    private String imageUrl;
    @Nullable
    private String url;

    public DBModel () {

    }

    public DBModel (NewsItem newsItem) {
        id = newsItem.getId();
        category = newsItem.getCategory();
        title = newsItem.getTitle();
        date = newsItem.getPublishDate();
        text = newsItem.getText();
        imageUrl = newsItem.getImageUrl();
        url = newsItem.getUrl();
    }

    public String getImageUrl () {
        return imageUrl;
    }

    public void setImageUrl (String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getCategory () {
        return category;
    }

    public void setCategory (String category) {
        this.category = category;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getDate () {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getText () {
        return text;
    }

    public void setText (String text) {
        this.text = text;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

}
