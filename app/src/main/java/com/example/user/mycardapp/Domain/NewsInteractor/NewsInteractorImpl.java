package com.example.user.mycardapp.Domain.NewsInteractor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.user.mycardapp.Data.DataBase.DataBaseRepository;
import com.example.user.mycardapp.Data.DataBase.DataBaseRepositoryImpl;
import com.example.user.mycardapp.Data.Network.NetworkRepository;
import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import androidx.annotation.NonNull;
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
    public Observable<NewsItem> getAllNews (@NonNull String category , @NonNull Boolean fromDataBase) {
        if (fromDataBase) {
            return dataBaseRepository.getNews(category)
                    .subscribeOn(Schedulers.io());
        } else {
            Log.d("check" , "checkDataBaseWorking");
            return networkRepository.getNews(category)
                    .toList()
                    .doOnSuccess(newsItems -> {
                        dataBaseRepository.deleteAll(category);
                        dataBaseRepository.saveAllNews(newsItems , category);
                    })
                    .toObservable()
                    .flatMap(Observable::fromIterable)
                    .subscribeOn(Schedulers.io());
        }
    }

    @Override
    public void clearDataBase (String category) {
        dataBaseRepository.deleteAll(category);
    }
}
