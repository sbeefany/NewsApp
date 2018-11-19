package com.example.user.mycardapp.Data;

import com.example.user.mycardapp.Data.DataBase.DBModel;
import com.example.user.mycardapp.Data.Network.DTOModels.Multimedia;
import com.example.user.mycardapp.Data.Network.DTOModels.Result;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class NewsItem {

    @Nullable
    private String title;
    @Nullable
    private String imageUrl;
    @Nullable
    private String category;
    @Nullable
    private String publishDate;
    @Nullable
    private String text;
    @NonNull
    private String url;
    @NonNull
    private int id;

    public NewsItem (@NonNull Result dtoNewsModel , @NonNull String category) {
        String image = null;
        id = new Random().nextInt();
        url = dtoNewsModel.getUrl();
        title = dtoNewsModel.getTitle();
        this.category = category;
        publishDate = dtoNewsModel.getPublishedDate();
        text = dtoNewsModel.get_abstract();
        for (Multimedia multimedia : dtoNewsModel.getMultimedia()) {
            if (multimedia.getFormat().equals("Normal"))
                image = multimedia.getUrl();
        }
        this.imageUrl = image;
    }

    public NewsItem (@NonNull DBModel dbModel) {
        id = dbModel.getId();
        url = dbModel.getUrl();
        title = dbModel.getTitle();
        category = dbModel.getCategory();
        publishDate = dbModel.getDate();
        text = dbModel.getText();
        imageUrl = dbModel.getImageUrl();
    }

    @NonNull
    public String getUrl () {
        return url;
    }

    @Nullable
    public String getTitle () {
        return title;
    }

    @Nullable
    public String getImageUrl () {
        return imageUrl;
    }

    @Nullable
    public String getCategory () {
        return category;
    }

    @Nullable
    public String getPublishDate () {
        return publishDate;
    }

    @Nullable
    public String getText () {
        return text;
    }

    @NonNull
    @Override
    public String toString () {
        return "Url:" + getUrl()
                + " Title:" + getTitle()
                + " PreviewText:" + getText()
                + " PublishDate:" + getPublishDate()
                + " Category:" + getCategory()
                + " ImageUrl:" + getUrl();
    }

    public int getId () {
        return id;
    }

    @Override
    public boolean equals (@Nullable Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof NewsItem))
            return false;
        NewsItem newsItem = ( NewsItem ) obj;
        return newsItem.getUrl().equals(getUrl()) &&
                newsItem.getCategory().equals(getCategory()) &&
                newsItem.getPublishDate().equals(getPublishDate()) &&
                newsItem.getText().equals(getText()) &&
                newsItem.getTitle().equals(getTitle()) &&
                newsItem.getImageUrl().equals(getImageUrl());

    }

    @Override
    public int hashCode () {
        int result = 17;
        if (getImageUrl() != null)
            result = 31 * result + getImageUrl().hashCode();
        if (getTitle() != null)
            result = 31 * result + getTitle().hashCode();
        if (getText() != null)
            result = 31 * result + getText().hashCode();
        if (getPublishDate() != null)
            result = 31 * result + getPublishDate().hashCode();
        if (getCategory() != null)
            result = 31 * result + getCategory().hashCode();
        result = 31 * result + getUrl().hashCode();
        return result;
    }
}

