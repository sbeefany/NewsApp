package com.example.user.mycardapp.Domain;

import com.example.user.mycardapp.Data.Network.NetworkRepository;
import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewsInteractorImpl implements Interactor {

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
