package com.example.user.mycardapp.Presentation.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Presentation.Presenter.DetailsPresenter.DetailsPresenter;
import com.example.user.mycardapp.Presentation.Presenter.DetailsPresenter.DetailsPresenterImpl;
import com.example.user.mycardapp.Presentation.Presenter.DetailsPresenter.DetailsView;
import com.example.user.mycardapp.R;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class NewsDetailsActivity extends AppCompatActivity implements DetailsView {

    private ImageView image;
    private TextView titleTextView;
    private TextView dateTextView;
    private TextView descriptionTextView;
    private DetailsPresenter presenter;
    private ConstraintLayout parentView;

    public static void toNewsDetailsActivity (@NonNull Context context , @NonNull int id) {
        Intent intent = new Intent(context , NewsDetailsActivity.class);
        intent.putExtra("id" , id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        initViews();
        presenter = DetailsPresenterImpl.getInstance(this);
        presenter.attachView(this);
        presenter.getData(getIntent().getIntExtra("id" , 0));
    }

    private void initViews () {
        image = findViewById(R.id.image);
        titleTextView = findViewById(R.id.title);
        descriptionTextView = findViewById(R.id.description);
        dateTextView = findViewById(R.id.date);
        parentView = findViewById(R.id.detailsParentView);
    }

    private void initToolBar (@NonNull String category) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(category);
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

    @SuppressLint("ResourceAsColor")
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details , menu);
        MenuItem item = menu.findItem(R.id.delete);
        Button delete = ( Button ) item.getActionView();
        delete.setBackgroundColor(R.color.lightPrimaryColor);
        delete.setText(getString(R.string.delete));
        delete.setTextColor(R.color.text_icons);
        delete.setOnClickListener(view -> {
            presenter.deleteNews(getIntent().getIntExtra("id" , 0));
            onBackPressed();
        });
        return true;
    }

    @Override
    protected void onDestroy () {
        presenter.detachView();
        super.onDestroy();
    }

}
