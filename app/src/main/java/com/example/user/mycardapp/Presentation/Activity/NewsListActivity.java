package com.example.user.mycardapp.Presentation.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Presentation.Adapter.Adapter;
import com.example.user.mycardapp.Presentation.Presenter.NewsContract;
import com.example.user.mycardapp.Presentation.Presenter.Presenter;
import com.example.user.mycardapp.R;

import java.util.ArrayList;

public class NewsListActivity extends AppCompatActivity implements NewsContract.INewsView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private NewsContract.INewsPresenter presenter;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_news_list );
        initPresenter ();
        presenter.attachView ( this );
        presenter.init ();

    }

    private void initPresenter () {
        if(Presenter.presenter!=null)
        presenter = Presenter.presenter;
        if ( presenter == null ) {
            presenter = new Presenter ();
        }
    }

    private void initRecycler ( ArrayList <NewsItem> newsItems ) {
        Adapter adapter = new Adapter ( newsItems , this );
        if ( getResources ().getConfiguration ().orientation == Configuration.ORIENTATION_PORTRAIT ) {
            LinearLayoutManager layoutManager = new LinearLayoutManager ( this );
            recyclerView.setLayoutManager ( layoutManager );
        } else {
            GridLayoutManager layoutManager = new GridLayoutManager ( this , 2 );
            recyclerView.setLayoutManager ( layoutManager );
        }
        recyclerView.setAdapter ( adapter );
    }

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {
        getMenuInflater ().inflate ( R.menu.menu_option , menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected ( MenuItem item ) {
        switch ( item.getItemId () ) {
            case R.id.about_activity:
                startActivity ( new Intent ( this , AboutActivity.class ) );
            default:
                return super.onOptionsItemSelected ( item );
        }

    }

    @Override
    public void initViews () {
        progressBar = findViewById ( R.id.progress_bar );
        recyclerView = findViewById ( R.id.news_list );
    }

    @Override
    public void showMwssage ( @NonNull String message ) {

    }

    @Override
    public void loadNews ( @NonNull ArrayList <NewsItem> news ) {
        initRecycler ( news );
    }

    @Override
    public void startLoading () {
        recyclerView.setVisibility ( View.GONE );
        progressBar.setVisibility ( View.VISIBLE );
    }

    @Override
    public void finishLoading () {
        recyclerView.setVisibility ( View.VISIBLE );
        progressBar.setVisibility ( View.GONE );
    }

    @Override
    protected void onDestroy () {
        super.onDestroy ();
        presenter.finish ();
        presenter.detachView ();
    }
}
