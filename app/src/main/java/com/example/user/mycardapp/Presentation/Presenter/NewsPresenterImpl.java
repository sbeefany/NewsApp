package com.example.user.mycardapp.Presentation.Presenter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Domain.Interactor;
import com.example.user.mycardapp.Domain.NewsInteractorImpl;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class NewsPresenterImpl implements NewsPresenter {

    private static NewsPresenter presenter;
    private Disposable disposable;
    private NewsView view;
    private Interactor interactor;

    private NewsPresenterImpl () {
        interactor = new NewsInteractorImpl();
    }

    public static NewsPresenter createPresenter () {
        if ( presenter == null ) {
            presenter = new NewsPresenterImpl();
        }
        return presenter;
    }

    @Override
    public void init () {
        Log.d("View" , view.toString());
        if ( view != null ) {
            view.initViews();
            view.startLoading();
            getNews();
        }
    }

    @Override
    public void attachView (@NonNull NewsView view) {
        this.view = view;
    }

    @Override
    public void detachView () {
        view = null;
    }

    @Override
    public void finish () {
        view.finishLoading();
        disposable.dispose();
    }

    @SuppressLint("CheckResult")
    @Override
    public void getNews () {
        Observable<NewsItem> observable = interactor.getAllNews();
        saveNewsToCache(observable);
        if ( NewsPresenterImpl.this.view != null ) {
            ArrayList<NewsItem> newsItems = new ArrayList<>();
            disposable = observable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(newsItems::add ,
                            throwable -> {
                                NewsPresenterImpl.this.view.finishLoading();
                                NewsPresenterImpl.this.view.showMwssage(throwable.getMessage());
                            } ,
                            () -> {
                                NewsPresenterImpl.this.view.finishLoading();
                                NewsPresenterImpl.this.view.loadNews(newsItems);
                            }
                    );
        }
    }

    @Override
    public void saveNewsToCache (@NonNull Observable<NewsItem> news) {
        interactor.saveNewsToCache(news);
    }

}
