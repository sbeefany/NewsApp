package com.example.user.mycardapp.Presentation.Presenter.IntroPresenter;

import android.support.annotation.NonNull;

public interface IntroPresenter {

   void showIntro(int count);

   void clickedGetStarted();

   void attachView(@NonNull IntroView view);

   void finish();

}
