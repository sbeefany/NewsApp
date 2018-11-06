package com.example.user.mycardapp.Data.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.user.mycardapp.Data.NewsItem;

import java.util.Random;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

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
    private String fullDescription;
    @Nullable
    private String date;
    @Nullable
    private String shortDescription;
    @Nullable
    private String imageUrl;
    @Nullable
    private String url;

    public DBModel () {

    }

    public DBModel (NewsItem newsItem) {
        id = new Random().nextInt();
        category = newsItem.getCategory();
        title = newsItem.getTitle();
        fullDescription = newsItem.getFullText();
        date = newsItem.getPublishDate();
        shortDescription = newsItem.getPreviewText();
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

    public String getFullDescription () {
        return fullDescription;
    }

    public void setFullDescription (String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getDate () {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getShortDescription () {
        return shortDescription;
    }

    public void setShortDescription (String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

}
