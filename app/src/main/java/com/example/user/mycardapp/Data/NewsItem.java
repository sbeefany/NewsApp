package com.example.user.mycardapp.Data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.user.mycardapp.Data.Network.DTOModels.Multimedia;
import com.example.user.mycardapp.Data.Network.DTOModels.Result;

import java.util.Date;
import java.util.Random;


public class NewsItem {

    @Nullable
    private final String title;
    @Nullable
    private final String imageUrl;
    @Nullable
    private final Category category;
    @Nullable
    private final String publishDate;
    @Nullable
    private final String previewText;
    @NonNull
    private final String url;

    public NewsItem (String title , String imageUrl , Category category , Date publishDate , String previewText , String fullText) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.category = category;
        this.publishDate = publishDate.toString();
        this.previewText = previewText;
        this.url = "https//vk.com";
    }

    public NewsItem (@NonNull Result dtoNewsModel) {
        String image = null;
        url = dtoNewsModel.getUrl();
        title = dtoNewsModel.getTitle();
        category = initCategory(dtoNewsModel);
        publishDate = dtoNewsModel.getPublishedDate();
        previewText = dtoNewsModel.get_abstract();
        for (Multimedia multimedia : dtoNewsModel.getMultimedia()) {
            if (multimedia.getFormat().equals("Normal"))
                image = multimedia.getUrl();
        }
        this.imageUrl = image;

    }

    private Category initCategory (Result dtoNewsModel) {
        Random random = new Random();
        return new Category(random.nextInt() , dtoNewsModel.getSubsection());
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
    public Category getCategory () {
        return category;
    }

    @Nullable
    public String getPublishDate () {
        return publishDate;
    }

    @Nullable
    public String getPreviewText () {
        return previewText;
    }

    @NonNull
    @Override
    public String toString () {
        return "Url:" + getUrl()
                + " Title:" + getTitle()
                + " PreviewText:" + getPreviewText()
                + " PublishDate:" + getPublishDate()
                + " Category:" + getCategory().getName()
                + " ImageUrl:" + getUrl();
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
                newsItem.getPreviewText().equals(getPreviewText()) &&
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
        if (getPreviewText() != null)
            result = 31 * result + getPreviewText().hashCode();
        if (getPublishDate() != null)
            result = 31 * result + getPublishDate().hashCode();
        if (getCategory() != null)
            result = 31 * result + getCategory().hashCode();
        result = 31 * result + getUrl().hashCode();
        return result;
    }
}

