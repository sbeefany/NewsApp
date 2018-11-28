package com.example.user.mycardapp.Data;

import java.util.List;

import io.reactivex.Observable;

public interface Repository {

    Observable<NewsItem> getNews (String category);

    NewsItem getOneNews (int id);

    void saveAllNews (List<NewsItem> newsList , String category);

    void deleteAll (String category);

    void deleteOneNews (int id);

}
