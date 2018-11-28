package com.example.user.mycardapp.Data.DataSource;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.user.mycardapp.Data.DataSource.DTOModels.DBModel;
import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public class NetworkRepository implements Repository {

    private static Repository repository;
    private NewsDataBase dataBase;

    private NetworkRepository (@NonNull Context context) {
        dataBase = NewsDataBase.getInstance(context);
    }

    public synchronized static Repository getInstance (@NonNull Context context) {
        if (repository == null) {
            repository = new NetworkRepository(context);
        }
        return repository;
    }

    @SuppressLint("CheckResult")
    @Override
    public Observable<NewsItem> getNews (String category) {
        return dataBase.newsDao().getAllNews(category)
                .toObservable()
                .flatMap(dbModels -> {
                    if (dbModels.size() == 0) {
                        return NewsRestApi.getInstance()
                                .getApi()
                                .getNews(category.toLowerCase())
                                .flatMap(dtoNewsModel -> Observable.fromIterable(dtoNewsModel.getResults()))
                                .map(newsItem -> new NewsItem(newsItem , category));
                    } else {
                        return Observable.fromIterable(dbModels)
                                .map(NewsItem::new);
                    }
                });
    }

    @Override
    public NewsItem getOneNews (@NonNull int id) {
        return new NewsItem(dataBase.newsDao().getNewsById(id));
    }

    @Override
    public void saveAllNews (List<NewsItem> newsList , String category) {
        dataBase.newsDao().insertAll(convertToDBModelArray(newsList));
    }

    @Override
    public void deleteAll (@NonNull String category) {
        dataBase.newsDao().deleteAllFromOneCategory(category);
    }

    @Override
    public void deleteOneNews (int id) {
        dataBase.newsDao().delete(dataBase.newsDao().getNewsById(id));
    }

    private DBModel[] convertToDBModelArray (List<NewsItem> items) {
        List<DBModel> dbModels = new ArrayList<>();
        for (NewsItem item : items) {
            dbModels.add(new DBModel(item));
        }
        return dbModels.toArray(new DBModel[items.size()]);
    }


}
