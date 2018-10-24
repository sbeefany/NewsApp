package com.example.user.mycardapp.Domain;

import android.support.annotation.NonNull;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;
import com.example.user.mycardapp.Data.RepositoryImpl;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewsInteractorImpl implements Interactor {

    private static final long DELAY = 2;
    private Repository repository;

    public NewsInteractorImpl () {
        repository = RepositoryImpl.getRepository();
    }

    @Override
    public Observable<NewsItem> getAllNews () {
        return repository.getNews()
                .delay(DELAY , TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void saveNewsToCache (@NonNull Observable<NewsItem> news) {
        repository.saveNewsToCache(news);
    }

}
