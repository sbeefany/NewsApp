package com.example.user.mycardapp.Data.DataBase;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import java.util.List;

public interface DataBaseRepository extends Repository {

    NewsItem getOneNews (int id);

    void saveAllNews (List<NewsItem> newsList , String category);

    void saveOneNews (NewsItem oneNews);

    void deleteAll (String category);

    void deleteOneNews (int id);

}
