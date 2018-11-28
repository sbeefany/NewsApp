package com.example.user.mycardapp.Domain.NewsInteractor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.user.mycardapp.Data.DataSource.NetworkRepository;
import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewsInteractorImpl implements NewsInteractor {

    private Repository networkRepository;

    public NewsInteractorImpl (Context context) {
        networkRepository = NetworkRepository.getInstance(context);
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<NewsItem> getAllNews (@NonNull String category) {
        Log.d("check" , "checkDataBaseWorking");
        return networkRepository.getNews(category)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void clearDataBase (String category) {
        networkRepository.deleteAll(category);
    }
}
