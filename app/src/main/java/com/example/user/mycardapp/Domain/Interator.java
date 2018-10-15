package com.example.user.mycardapp.Domain;

import android.support.annotation.NonNull;

import com.example.user.mycardapp.Data.DataUtils;
import com.example.user.mycardapp.Data.NewsItem;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Interator implements IInterator {
    private static final long DELAY= 2;
    private CompositeDisposable disposables;

    public Interator () {
        this.disposables = new CompositeDisposable ();
    }

    @Override
    public void execute ( @NonNull DisposableObserver observer ) {
        Observable <NewsItem> observable = Observable.fromIterable ( DataUtils.generateNews () )
                .delay ( DELAY, TimeUnit.SECONDS )
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () );
        addDisposable ( observable.subscribeWith ( observer ) );
    }

    private void addDisposable ( @NonNull Disposable disposable ) {
        disposables.add ( disposable );
    }

    @Override
    public void dispose () {
        disposables.dispose ();
    }
}
