package com.example.user.mycardapp.Presentation.Presenter;

import android.support.annotation.NonNull;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Domain.IInterator;
import com.example.user.mycardapp.Domain.Interator;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;

public class Presenter implements NewsContract.INewsPresenter {

    public static NewsContract.INewsPresenter presenter;
    private NewsContract.INewsView view;
    private IInterator interator;

    public Presenter () {
        this.interator = new Interator ();
    }

    @Override
    public void init () {
        if ( view != null ) {
            view.initViews ();
            view.startLoading ();
            getNews ();

        }
    }

    @Override
    public void attachView ( @NonNull NewsContract.INewsView view ) {
        this.view = view;
    }

    @Override
    public void detachView () {
        view = null;
    }

    @Override
    public void finish () {
        interator.dispose ();
        presenter = this;
    }

    @Override
    public void getNews () {
        interator.execute ( new MyObserver () );
    }

    private final class MyObserver extends DisposableObserver <NewsItem> {
        ArrayList <NewsItem> newsItems = new ArrayList <> ();

        @Override
        public void onNext ( NewsItem newsItem ) {
            newsItems.add ( newsItem );
        }

        @Override
        public void onError ( Throwable e ) {
            if ( Presenter.this.view != null ) {
                Presenter.this.view.finishLoading ();
                Presenter.this.view.showMwssage ( e.getMessage () );
            }
        }

        @Override
        public void onComplete () {
            if ( Presenter.this.view != null ) {
                Presenter.this.view.finishLoading ();
                Presenter.this.view.loadNews ( newsItems );
            }
        }
    }

    ;
}
