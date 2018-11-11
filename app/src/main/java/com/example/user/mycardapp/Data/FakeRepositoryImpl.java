package com.example.user.mycardapp.Data;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;

public class FakeRepositoryImpl implements Repository {

    private static Repository repository = new FakeRepositoryImpl();
    private List<NewsItem> news;

    private FakeRepositoryImpl () {
    }

    public static Repository getRepository () {
        return repository;
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<NewsItem> getNews (String category) {
        if (news != null) {
            Log.d("It's cache" , "it's cache");
        } else {
             Observable.fromIterable(DataUtils.generateNews())
            .toList()
            .doOnSuccess(news->{
                this.news=news;
            });
        }
        return Observable.fromIterable(news);
    }

}
