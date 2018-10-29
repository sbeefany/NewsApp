package com.example.user.mycardapp.Data;

import com.example.user.mycardapp.Data.Network.DTOModels.Multimedia;
import com.example.user.mycardapp.Data.Network.DTOModels.Result;

import java.util.Date;
import java.util.Random;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

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
    @Nullable
    private final String fullText;
    @NonNull
    private final String url;

    public NewsItem (String title , String imageUrl , Category category , Date publishDate , String previewText , String fullText) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.category = category;
        this.publishDate = publishDate.toString();
        this.previewText = previewText;
        this.fullText = fullText;
        this.url = "https//vk.com";
    }

    public NewsItem (@NonNull Result dtoNewsModel) {
        String image = null;
        url = dtoNewsModel.getUrl();
        title = dtoNewsModel.getTitle();
        category = initCategory(dtoNewsModel);
        publishDate = dtoNewsModel.getPublishedDate();
        previewText = dtoNewsModel.get_abstract();
        fullText = null;
        for (Multimedia multimedia : dtoNewsModel.getMultimedia()) {
            if ( multimedia.getFormat().equals("Normal") )
                image = multimedia.getUrl();
        }
        this.imageUrl = image;

    }

    private Category initCategory (Result dtoNewsModel) {
        Random random = new Random();
        return new Category(random.nextInt() , dtoNewsModel.getSubsection());
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

    public Category getCategory () {
        return category;
    }

    public String getPublishDate () {
        return publishDate;
    }

    public String getPreviewText () {
        return previewText;
    }

    public String getFullText () {
        return fullText;
    }

}

