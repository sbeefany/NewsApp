package com.example.user.mycardapp.Domain.DetailsInteractor;

import android.content.Context;

import com.example.user.mycardapp.Data.DataBase.DataBaseRepository;
import com.example.user.mycardapp.Data.DataBase.DataBaseRepositoryImpl;
import com.example.user.mycardapp.Data.NewsItem;

public class DetailsInteractorImpl implements DetailsInteractor {
    private DataBaseRepository dataBaseRepository;

    public DetailsInteractorImpl (Context context) {
        dataBaseRepository = DataBaseRepositoryImpl.getInstance(context);
    }

    @Override
    public NewsItem getDetailsAboutOneNews (int id) {
        return dataBaseRepository.getOneNews(id);
    }

    @Override
    public void deleteNews (int id) {
        dataBaseRepository.deleteOneNews(id);
    }

}
