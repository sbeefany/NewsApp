package com.example.user.mycardapp.Domain;

import com.example.user.mycardapp.Data.FakeRepositoryImpl;
import com.example.user.mycardapp.Data.Network.DTOModels.Result;
import com.example.user.mycardapp.Data.Network.NetworkRepository;
import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewsInteractorImpl implements Interactor {

    private static final long DELAY = 2;
    private Repository repository;

    public NewsInteractorImpl () {
        repository = NetworkRepository.getInstance();
    }

    @Override
    public Observable<NewsItem> getAllNews (String category) {
        return repository.getNews(category)
                .subscribeOn(Schedulers.io());
    }

}
