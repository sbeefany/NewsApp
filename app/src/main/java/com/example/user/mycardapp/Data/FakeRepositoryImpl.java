package com.example.user.mycardapp.Data;

import android.util.Log;

import io.reactivex.Observable;

public class FakeRepositoryImpl implements Repository {

    private static Repository repository;
    private Observable<NewsItem> news;

    private FakeRepositoryImpl () {
    }

    public static Repository getRepository () {
        if ( repository == null ) {
            repository = new FakeRepositoryImpl();
        }
        return repository;
    }

    @Override
    public Observable<NewsItem> getNews (String category) {
        if ( news != null ) {
            Log.d("It's cache" , "it's cache");
        } else {
            news = Observable.fromIterable(DataUtils.generateNews());
        }
        return news;
    }

}
