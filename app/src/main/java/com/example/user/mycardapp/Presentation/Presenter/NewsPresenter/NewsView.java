package com.example.user.mycardapp.Presentation.Presenter.NewsPresenter;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Presentation.StateError;

import java.util.List;

import androidx.annotation.NonNull;

public interface NewsView {

    void loadNews (@NonNull List<NewsItem> news);

    void startLoading ();

    void finishLoading ();

    void initViews ();

    void showStateError (@NonNull StateError error);

}

