package com.example.user.mycardapp.Domain;

import com.example.user.mycardapp.Data.NewsItem;

import io.reactivex.Observable;

public interface Interactor {

    Observable<NewsItem> getAllNews (String category);

}

