package com.example.user.mycardapp.Presentation.Presenter;

import android.support.annotation.NonNull;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Presentation.StateError;

import java.util.List;

public interface NewsView {

    void showMessage (@NonNull String message);

    void loadNews (@NonNull List<NewsItem> news);

    void startLoading ();

    void finishLoading ();

    void initViews ();

    void showStateError (StateError error);

}

