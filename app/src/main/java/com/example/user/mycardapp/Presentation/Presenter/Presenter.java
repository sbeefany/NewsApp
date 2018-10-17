package com.example.user.mycardapp.Presentation.Presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Domain.IInterator;
import com.example.user.mycardapp.Domain.Interator;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;

public class Presenter implements NewsContract.INewsPresenter {

    public static NewsContract.INewsPresenter presenter;
    private ArrayList <NewsItem> news;
    private MyObserver observer;
    private NewsContract.INewsView view;
    private IInterator interator;

    public Presenter () {
        this.interator = new Interator ();
    }

    @Override
    public void init () {
        Log.e ( "View" , view.toString () );
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
        view.finishLoading ();
        presenter = this;
    }

    @Override
    public void getNews () {
        observer = new MyObserver ();
        interator.execute ( observer );
    }

    @Override
    public ArrayList <NewsItem> getSavedInformation () {
        return news;
    }

    private final class MyObserver extends DisposableObserver <NewsItem> {
        private ArrayList <NewsItem> newsItems = new ArrayList <> ();

        @Override
        public void onNext ( NewsItem newsItem ) {
            newsItems.add ( newsItem );
            Log.e ( "CHECK" , "CHECK" );
        }

        @Override
        public void onError ( Throwable e ) {
            if ( Presenter.this.view != null ) {
                Presenter.this.view.finishLoading ();
                Presenter.this.view.showMwssage ( e.getMessage () );
            } else {
                observer.dispose ();
            }
        }

        @Override
        public void onComplete () {
            if ( Presenter.this.view != null ) {
                Presenter.this.view.finishLoading ();
                Presenter.this.view.loadNews ( newsItems );
            } else {
                Presenter.this.news = newsItems;
                observer.dispose ();
            }
        }
    }

    ;
}
