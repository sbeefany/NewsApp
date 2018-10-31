package com.example.user.mycardapp.Data.Network;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

public class NetworkRepository implements Repository {

    private static Repository networkRep;
    private Map<String, Observable<NewsItem>> cache = new HashMap<>();

    private NetworkRepository () {

    }

    public static synchronized Repository getInstance () {
        if ( networkRep == null ) {
            networkRep = new NetworkRepository();
        }
        return networkRep;
    }

    @Override
    public Observable<NewsItem> getNews (String category) {
        if ( !cache.containsKey(category) ) {
            cache.put(category , NewsRestApi.getInstance()
                    .getApi()
                    .getNews(category)
                    .flatMap(dtoNewsModel -> Observable.fromIterable(dtoNewsModel.getResults()))
                    .map(NewsItem::new));
        }
        return cache.get(category);
    }

}
