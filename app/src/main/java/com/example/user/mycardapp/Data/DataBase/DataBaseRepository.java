package com.example.user.mycardapp.Data.DataBase;

import com.example.user.mycardapp.Data.Network.DTOModels.DTONewsModel;
import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public interface DataBaseRepository  extends Repository{

    NewsItem getOneNews(int id);

    void saveAllNews(ArrayList<NewsItem> newsList);

    void saveOneNews(NewsItem oneNews);

}
