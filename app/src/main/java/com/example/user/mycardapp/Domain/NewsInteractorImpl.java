package com.example.user.mycardapp.Domain;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.user.mycardapp.Data.DataBase.DataBaseRepository;
import com.example.user.mycardapp.Data.DataBase.DataBaseRepositoryImpl;
import com.example.user.mycardapp.Data.Network.NetworkRepository;
import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewsInteractorImpl implements NewsInteractor {

    private Repository networkRepository;
    private DataBaseRepository dataBaseRepository;

    public NewsInteractorImpl (Context context) {
        networkRepository = NetworkRepository.getInstance();
        dataBaseRepository = DataBaseRepositoryImpl.getInstance(context);
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<NewsItem> getAllNews (String category , Boolean fromDataBase) {
        if (fromDataBase) {
            return dataBaseRepository.getNews(category)
                    .subscribeOn(Schedulers.io());
        } else {
            return networkRepository.getNews(category)
                    .subscribeOn(Schedulers.io());
        }
    }

}
