package com.example.user.mycardapp.Data;

import android.support.annotation.NonNull;

import com.example.user.mycardapp.Data.Network.DTOModels.DTONewsModel;

import io.reactivex.Observable;

public interface Repository {

    Observable<NewsItem> getNews (String category);

}
