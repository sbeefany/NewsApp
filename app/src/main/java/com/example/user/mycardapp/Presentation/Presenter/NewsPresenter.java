package com.example.user.mycardapp.Presentation.Presenter;

import android.support.annotation.NonNull;

import com.example.user.mycardapp.Data.NewsItem;

import io.reactivex.Observable;


public interface NewsPresenter {

    void init ();

    void attachView (@NonNull NewsView view);

    void detachView ();

    void finish ();

    void getNews ();

    void saveNewsToCache (@NonNull Observable<NewsItem> news);

}
