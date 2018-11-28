package com.example.user.mycardapp.Domain.DetailsInteractor;

import android.content.Context;

import com.example.user.mycardapp.Data.DataSource.NetworkRepository;
import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Data.Repository;

public class DetailsInteractorImpl implements DetailsInteractor {
    private Repository dataBaseRepository;

    public DetailsInteractorImpl (Context context) {
        dataBaseRepository = NetworkRepository.getInstance(context);
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
