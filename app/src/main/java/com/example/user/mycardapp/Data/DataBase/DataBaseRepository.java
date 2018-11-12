package com.example.user.mycardapp.Data.DataBase;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import java.util.ArrayList;

public interface DataBaseRepository extends Repository {

    NewsItem getOneNews (int id);

    void saveAllNews (ArrayList<NewsItem> newsList);

    void saveOneNews (NewsItem oneNews);

}
