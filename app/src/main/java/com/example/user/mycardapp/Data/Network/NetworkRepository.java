package com.example.user.mycardapp.Data.Network;

import android.annotation.SuppressLint;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import io.reactivex.Observable;

public class NetworkRepository implements Repository {

    private static Repository networkRep = new NetworkRepository();

    private NetworkRepository () {

    }

    public static Repository getInstance () {
        return networkRep;
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<NewsItem> getNews (String category) {
        return NewsRestApi.getInstance()
                .getApi()
                .getNews(category.toLowerCase())
                .flatMap(dtoNewsModel -> Observable.fromIterable(dtoNewsModel.getResults()))
                .map(newsItem -> new NewsItem(newsItem , category));
    }

}
