package com.example.user.mycardapp.Domain;

import android.support.annotation.NonNull;

import com.example.user.mycardapp.Data.NewsItem;

import io.reactivex.Observable;

public interface Interactor {

    Observable<NewsItem> getAllNews ();

    void saveNewsToCache (@NonNull Observable<NewsItem> news);

}

