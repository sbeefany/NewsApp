package com.example.user.mycardapp.Presentation.Presenter.DetailsPresenter;

import android.content.Context;

import com.example.user.mycardapp.Domain.DetailsInteractor.DetailsInteractor;
import com.example.user.mycardapp.Domain.DetailsInteractor.DetailsInteractorImpl;

public class DetailsPresenterImpl implements DetailsPresenter {

    private static DetailsPresenter instance;
    private DetailsView view;
    private DetailsInteractor detailsInteractor;

    private DetailsPresenterImpl (Context context) {
        detailsInteractor = new DetailsInteractorImpl(context);
    }

    public static synchronized DetailsPresenter getInstance (Context context) {
        if (instance == null) {
            instance = new DetailsPresenterImpl(context);
        }
        return instance;
    }

    @Override
    public void attachView (DetailsView view) {
        this.view = view;
    }

    @Override
    public void getData (int id) {
        if (view != null) {
            view.startLoading();
            view.showResults(detailsInteractor.getDetailsAboutOneNews(id));
            view.stopLoading();
        }
    }

    @Override
    public void detachView () {
        view = null;
    }

    @Override
    public void deleteNews (int id) {
        detailsInteractor.deleteNews(id);
    }
}
