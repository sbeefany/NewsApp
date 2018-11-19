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
import java.util.List;

import androidx.annotation.NonNull;
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

<<<<<<< HEAD:app/src/main/java/com/example/user/mycardapp/Presentation/Presenter/NewsPresenter/NewsPresenterImpl.java
    public static NewsPresenter createPresenter (Context context) {
        if (presenter == null) {
            presenter = new NewsPresenterImpl(context);
=======
    public static NewsPresenter createPresenter () {
        if (presenter == null) {
            presenter = new NewsPresenterImpl();
>>>>>>> hw4:app/src/main/java/com/example/user/mycardapp/Presentation/Presenter/NewsPresenterImpl.java
        }
        return presenter;
    }

    @Override
    public void init () {
        Log.d("View" , view.toString());
        if (view != null) {
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
<<<<<<< HEAD:app/src/main/java/com/example/user/mycardapp/Presentation/Presenter/NewsPresenter/NewsPresenterImpl.java
    public void getNews (String category , Boolean fromDataBase) {
        Observable<NewsItem> observable = interactor.getAllNews(category , fromDataBase);
        if (view != null) {
            ArrayList<NewsItem> newsItems = new ArrayList<>();
=======
    public void getNews (String category) {
        Observable<NewsItem> observable = interactor.getAllNews(category);
        if (view != null) {
            List<NewsItem> newsItems = new ArrayList<>();
>>>>>>> hw4:app/src/main/java/com/example/user/mycardapp/Presentation/Presenter/NewsPresenterImpl.java
            disposable = observable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            newsItems::add ,
                            throwable -> {
                                Log.e("Exception!!!" , throwable.toString());
                                if (throwable instanceof IOException) {
<<<<<<< HEAD:app/src/main/java/com/example/user/mycardapp/Presentation/Presenter/NewsPresenter/NewsPresenterImpl.java
                                    view.showStateError(StateError.NetworkError);
=======
                                    view.showStateError(StateError.NETWORKERROR);
                                    view.showMessage(throwable.getMessage());
>>>>>>> hw4:app/src/main/java/com/example/user/mycardapp/Presentation/Presenter/NewsPresenterImpl.java
                                    return;
                                }
                                view.showStateError(StateError.SERVERERROR);
                            } ,
                            () -> {
                                if (newsItems.size() == 0)
                                    getNews(category , false);
                                else {
                                    view.finishLoading();
                                    view.loadNews(newsItems);
                                }
                            }
                    );
        }
    }

    @Override
    public void clearDataBase (String category) {
        interactor.clearDataBase(category);
    }

}
