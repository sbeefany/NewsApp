package com.example.user.mycardapp.Data;

import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.Observable;

public class RepositoryImpl implements Repository {

    private static Repository repository;
    private Observable<NewsItem> news;

    private RepositoryImpl () {
    }

    public static Repository getRepository () {
        if ( repository == null ) {
            repository = new RepositoryImpl();
        }
        return repository;
    }

    @Override
    public Observable<NewsItem> getNews () {
        if ( news != null ) {
            Log.d("It's cache" , "it's cache");
            return news;
        } else {
            return Observable.fromIterable(DataUtils.generateNews());
        }
    }

    @Override
    public void saveNewsToCache (@NonNull Observable<NewsItem> news) {
        this.news = news;
    }

}
