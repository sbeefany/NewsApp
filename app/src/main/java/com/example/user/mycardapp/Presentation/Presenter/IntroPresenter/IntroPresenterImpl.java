package com.example.user.mycardapp.Presentation.Presenter.IntroPresenter;

import androidx.annotation.NonNull;

public class IntroPresenterImpl implements IntroPresenter {

    private static IntroPresenter instance;
    private IntroView view;

    private IntroPresenterImpl () {

    }

    public static synchronized IntroPresenter getInstance () {
        if (instance == null) {
            instance = new IntroPresenterImpl();
        }
        return instance;
    }

    @Override
    public void showIntro (int count) {
        if (view != null) {
            if (count % 2 == 0)
                view.toNewsActivity();
            else
                view.showIntro();
        }

    }

    @Override
    public void clickedGetStarted () {
        if (view != null)
            view.toNewsActivity();
    }

    @Override
    public void attachView (@NonNull IntroView view) {
        this.view = view;
    }

    @Override
    public void finish () {
        if (view != null) {
            view = null;
        }
    }

}
