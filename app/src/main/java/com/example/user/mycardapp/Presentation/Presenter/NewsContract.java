package com.example.user.mycardapp.Presentation.Presenter;

import android.support.annotation.NonNull;

import com.example.user.mycardapp.Data.NewsItem;

import java.util.ArrayList;

public interface NewsContract {

    interface INewsView {

        void showMwssage ( @NonNull String message );

        void loadNews ( @NonNull ArrayList <NewsItem> news );

        void startLoading ();

        void finishLoading ();

        void initViews ();

    }

    interface INewsPresenter {

        void init ();

        void attachView ( @NonNull INewsView view );

        void detachView ();

        void finish ();

        void getNews ();

        ArrayList<NewsItem> getSavedInformation();
    }
}
