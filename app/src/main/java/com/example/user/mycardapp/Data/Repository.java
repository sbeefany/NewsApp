package com.example.user.mycardapp.Data;

import io.reactivex.Observable;

public interface Repository {

    Observable<NewsItem> getNews (String category);

}
