package com.example.user.mycardapp.Data.Network;

import android.util.Log;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import io.reactivex.Observable;

public class NetworkRepository implements Repository {

    private static Repository networkRep;
    private Observable<NewsItem> cache;

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
        if ( cache == null ) {
            cache = NewsRestApi.getInstance()
                    .getApi()
                    .getNews(category)
                    .flatMap(dtoNewsModel -> Observable.fromIterable(dtoNewsModel.getResults()))
                    .map(NewsItem::new)
                    .doOnError(throwable -> {
                        Log.e("Error!!!" , throwable.toString());
                    });
        }
        return cache;
    }

}
