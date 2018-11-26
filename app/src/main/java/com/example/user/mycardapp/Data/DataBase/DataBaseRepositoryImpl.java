package com.example.user.mycardapp.Data.DataBase;

import android.content.Context;

import com.example.user.mycardapp.Data.NewsItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public class DataBaseRepositoryImpl implements DataBaseRepository {

    private static DataBaseRepository instance;
    private NewsDataBase dataBase;

    private DataBaseRepositoryImpl (@NonNull Context context) {
        dataBase = NewsDataBase.getInstance(context);
    }

    public synchronized static DataBaseRepository getInstance (@NonNull Context context) {
        if (instance == null) {
            instance = new DataBaseRepositoryImpl(context);
        }
        return instance;
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
    public void saveOneNews (NewsItem oneNews) {
        dataBase.newsDao().insert(new DBModel(oneNews));
    }

    @Override
    public void deleteAll (@NonNull String category) {
        dataBase.newsDao().deleteAllFromOneCategory(category);
    }

    @Override
    public void deleteOneNews (int id) {
        dataBase.newsDao().delete(dataBase.newsDao().getNewsById(id));
    }

    @Override
    public Observable<NewsItem> getNews (String category) {
        return dataBase.newsDao().getAllNews(category)
                .toObservable()
                .flatMap(Observable::fromIterable)
                .map(NewsItem::new);
    }

    private DBModel[] convertToDBModelArray (List<NewsItem> items) {
        List<DBModel> dbModels = new ArrayList<>();
        for (NewsItem item : items
                ) {
            dbModels.add(new DBModel(item));
        }
        return dbModels.toArray(new DBModel[items.size()]);
    }
}
