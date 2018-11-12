package com.example.user.mycardapp.Presentation.Presenter.DetailsPresenter;

import com.example.user.mycardapp.Data.NewsItem;

public interface DetailsView {

    void startLoading ();

    void stopLoading ();

    void showResults (NewsItem newsItem);

}
