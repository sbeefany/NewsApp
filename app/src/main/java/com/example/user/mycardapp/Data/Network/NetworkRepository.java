package com.example.user.mycardapp.Data.Network;

import android.annotation.SuppressLint;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;

public class NetworkRepository implements Repository {

    private static Repository networkRep = new NetworkRepository();
    private Map<String, List<NewsItem>> cache = new ConcurrentHashMap<>();

    private NetworkRepository () {

    }

    public static Repository getInstance () {
        return networkRep;
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<NewsItem> getNews (String category) {
        if (!cache.containsKey(category)) {
            NewsRestApi.getInstance()
                    .getApi()
                    .getNews(category)
                    .flatMap(dtoNewsModel -> Observable.fromIterable(dtoNewsModel.getResults()))
                    .map(NewsItem::new)
                    .toList()
                    .doOnSuccess(news -> {
                        cache.put(category , news);
                    });
        }
        return Observable.fromIterable(Objects.requireNonNull(cache.get(category)));
    }

}
