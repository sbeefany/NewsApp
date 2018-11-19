package com.example.user.mycardapp.Domain.DetailsInteractor;

import com.example.user.mycardapp.Data.NewsItem;

public interface DetailsInteractor {

    NewsItem getDetailsAboutOneNews (int id);

    void deleteNews (int id);

}
