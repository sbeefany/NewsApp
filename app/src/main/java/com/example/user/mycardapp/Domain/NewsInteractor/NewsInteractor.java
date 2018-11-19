package com.example.user.mycardapp.Domain.NewsInteractor;

import com.example.user.mycardapp.Data.NewsItem;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public interface NewsInteractor {

    Observable<NewsItem> getAllNews (@NonNull String category,@NonNull Boolean fromDataBase);

    void clearDataBase(String category);

}

