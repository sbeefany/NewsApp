package com.example.user.mycardapp.Data;

import com.example.user.mycardapp.Data.DataBase.DBModel;
import com.example.user.mycardapp.Data.Network.DTOModels.Multimedia;
import com.example.user.mycardapp.Data.Network.DTOModels.Result;

import java.util.Random;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

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
    public NewsItem (@NonNull Result dtoNewsModel,@NonNull String category) {
        String image = null;
        id = new Random().nextInt();
        url = dtoNewsModel.getUrl();
        title = dtoNewsModel.getTitle();
        this.category = category;
        publishDate = dtoNewsModel.getPublishedDate();
        text = dtoNewsModel.get_abstract();
        for (Multimedia multimedia : dtoNewsModel.getMultimedia()) {
            if ( multimedia.getFormat().equals("Normal") )
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

    public String getUrl () {
        return url;
    }

    public String getTitle () {
        return title;
    }

    public String getImageUrl () {
        return imageUrl;
    }

    public String getCategory () {
        return category;
    }

    public String getPublishDate () {
        return publishDate;
    }

    public String getText () {
        return text;
    }

    public int getId () {
        return id;
    }

}

