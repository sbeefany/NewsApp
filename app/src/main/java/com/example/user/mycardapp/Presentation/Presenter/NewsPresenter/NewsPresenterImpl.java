package com.example.user.mycardapp.Presentation.Presenter.NewsPresenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Domain.NewsInteractor.NewsInteractor;
import com.example.user.mycardapp.Domain.NewsInteractor.NewsInteractorImpl;
import com.example.user.mycardapp.Presentation.StateError;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class NewsPresenterImpl implements NewsPresenter {

    private static NewsPresenter presenter;
    private Disposable disposable;
    private NewsView view;
    private NewsInteractor interactor;

    private NewsPresenterImpl (Context context) {
        interactor = new NewsInteractorImpl(context);
    }

    public static NewsPresenter createPresenter (Context context) {
        if (presenter == null) {
            presenter = new NewsPresenterImpl(context);
        }
        return presenter;
    }

    @Override
    public void init () {
        Log.d("View" , view.toString());
        if (view != null) {
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
        disposable.dispose();
    }

    @SuppressLint("CheckResult")
    @Override
    public void getNews (String category) {
        Observable<NewsItem> observable = interactor.getAllNews(category);
        if (view != null) {
            ArrayList<NewsItem> newsItems = new ArrayList<>();
            disposable = observable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            newsItems::add ,
                            throwable -> {
                                Log.e("NEWS_PRESENTER" , throwable.toString());
                                if (throwable instanceof IOException) {
                                    view.showStateError(StateError.NETWORKERROR);
                                    return;
                                }
                                view.showStateError(StateError.SERVERERROR);
                            } ,
                            () -> {
                                view.finishLoading();
                                view.loadNews(newsItems);
                            }
                    );
        }
    }

}
