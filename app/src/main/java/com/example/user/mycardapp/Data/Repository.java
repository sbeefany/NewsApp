package com.example.user.mycardapp.Data;

import android.support.annotation.NonNull;

import io.reactivex.Observable;

public interface Repository {

    Observable<NewsItem> getNews ();

    void saveNewsToCache (@NonNull Observable<NewsItem> news);

}
