package com.example.user.mycardapp.Presentation.Presenter.DetailsPresenter;

public interface DetailsPresenter {

    void attachView (DetailsView view);

    void getData (int id);

    void detachView ();

    void deleteNews (int id);

}
