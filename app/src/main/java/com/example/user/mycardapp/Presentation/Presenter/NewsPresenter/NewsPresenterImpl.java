package com.example.user.mycardapp.Presentation.Presenter.NewsPresenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Domain.NewsInteractor;
import com.example.user.mycardapp.Domain.NewsInteractorImpl;
import com.example.user.mycardapp.Presentation.StateError;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class NewsPresenterImpl implements NewsPresenter {

    private static NewsPresenter presenter;
    private Disposable disposable;
    private NewsView view;
    private NewsInteractor interactor;
    private Context context;

    private NewsPresenterImpl (Context context) {
        interactor = new NewsInteractorImpl(context);
    }

    public static NewsPresenter createPresenter (Context context) {
        if ( presenter == null ) {
            presenter = new NewsPresenterImpl(context);
        }
        return presenter;
    }

    @Override
    public void init () {
        Log.d("View" , view.toString());
        if ( view != null ) {
            view.initViews();
            view.startLoading();
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
    public void getNews (String category) {
        Observable<NewsItem> observable = interactor.getAllNews(category);
        if ( view != null ) {
            ArrayList<NewsItem> newsItems = new ArrayList<>();
            disposable = observable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(newsItems::add ,
                            throwable -> {
                                Log.e("Exception!!!" , throwable.toString());
                                if ( throwable instanceof IOException ) {
                                    view.showStateError(StateError.NetworkError);
                                    view.showMessage(throwable.getMessage());
                                    return;
                                }
                                view.showStateError(StateError.ServerError);
                            } ,
                            () -> {
                                view.finishLoading();
                                view.loadNews(newsItems);
                            }
                    );
        }
    }

}
