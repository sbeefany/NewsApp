package com.example.user.mycardapp.Presentation.Presenter;

import android.support.annotation.NonNull;


public interface NewsPresenter {

    void init ();

    void attachView (@NonNull NewsView view);

    void detachView ();

    void finish ();

    void getNews (String category);

}
