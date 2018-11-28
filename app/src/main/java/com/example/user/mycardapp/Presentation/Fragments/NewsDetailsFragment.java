package com.example.user.mycardapp.Presentation.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Presentation.Presenter.DetailsPresenter.DetailsPresenter;
import com.example.user.mycardapp.Presentation.Presenter.DetailsPresenter.DetailsPresenterImpl;
import com.example.user.mycardapp.Presentation.Presenter.DetailsPresenter.DetailsView;
import com.example.user.mycardapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class NewsDetailsFragment extends Fragment implements DetailsView {

    private ImageView image;
    private TextView titleTextView;
    private TextView dateTextView;
    private TextView descriptionTextView;
    private DetailsPresenter presenter;
    private ConstraintLayout parentView;
    private FloatingActionButton deleteButton;

    public static NewsDetailsFragment newInstanse (@NonNull int id) {
        NewsDetailsFragment newsDetailsFragment = new NewsDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id" , id);
        newsDetailsFragment.setArguments(bundle);

        return newsDetailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_news_details , container , false);
        initViews(view);
        presenter = DetailsPresenterImpl.getInstance(getContext());
        presenter.attachView(this);
        if (getArguments() != null)
            presenter.getData(getArguments().getInt("id" , 0));
        else
            throw new NullPointerException("Вы не передали аргументы");
        deleteButton.setOnClickListener(view1 -> {
            presenter.deleteNews(getArguments().getInt("id" , 0));

        });
        return view;
    }

    private void initViews (View view) {
        image = view.findViewById(R.id.image);
        titleTextView = view.findViewById(R.id.title);
        descriptionTextView = view.findViewById(R.id.description);
        dateTextView = view.findViewById(R.id.date);
        parentView = view.findViewById(R.id.detailsParentView);
        deleteButton = view.findViewById(R.id.delete_button);
    }

    private void initToolBar (@NonNull String category) {

    }

    @Override
    public void startLoading () {
        parentView.setVisibility(View.GONE);
    }

    @Override
    public void stopLoading () {
        parentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResults (@NonNull NewsItem newsItem) {
        Glide.with(this).load(newsItem.getImageUrl()).into(image);
        titleTextView.setText(newsItem.getTitle());
        descriptionTextView.setText(newsItem.getText());
        dateTextView.setText(newsItem.getPublishDate());
        if (newsItem.getCategory() != null) {
            initToolBar(newsItem.getCategory());
        } else {
            initToolBar("Details");
        }
    }

    @Override
    public void onDestroy () {
        presenter.detachView();
        super.onDestroy();
    }

}
