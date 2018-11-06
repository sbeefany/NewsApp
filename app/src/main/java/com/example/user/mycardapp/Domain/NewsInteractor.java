package com.example.user.mycardapp.Domain;

import com.example.user.mycardapp.Data.NewsItem;

import io.reactivex.Observable;

public interface NewsInteractor {

    Observable<NewsItem> getAllNews (String category);

}

