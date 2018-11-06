package com.example.user.mycardapp.Domain;

import android.content.Context;

import com.example.user.mycardapp.Data.DataBase.DataBaseRepository;
import com.example.user.mycardapp.Data.DataBase.DataBaseRepositoryImpl;
import com.example.user.mycardapp.Data.Network.NetworkRepository;
import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewsInteractorImpl implements NewsInteractor {

    private Repository networkRepository;
    private DataBaseRepository dataBaseRepository;
    private Map<String, Boolean> isNetwork;


    public NewsInteractorImpl (Context context) {
        networkRepository = NetworkRepository.getInstance();
        dataBaseRepository = DataBaseRepositoryImpl.getInstance(context);
        isNetwork = new HashMap<>();
    }

    @Override
    public Observable<NewsItem> getAllNews (String category) {
        if ( isNetwork.get(category) == null || isNetwork.get(category)) {
            isNetwork.put(category,false);
            return networkRepository.getNews(category)
                    .subscribeOn(Schedulers.io());
        } else {
            return dataBaseRepository.getNews(category)
                    .subscribeOn(Schedulers.io());
        }
    }

}
