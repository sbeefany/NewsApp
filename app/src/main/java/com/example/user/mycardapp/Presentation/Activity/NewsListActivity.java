package com.example.user.mycardapp.Presentation.Activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Presentation.Adapter.Adapter;
import com.example.user.mycardapp.Presentation.Categories;
import com.example.user.mycardapp.Presentation.Presenter.NewsPresenter;
import com.example.user.mycardapp.Presentation.Presenter.NewsPresenterImpl;
import com.example.user.mycardapp.Presentation.Presenter.NewsView;
import com.example.user.mycardapp.Presentation.StateError;
import com.example.user.mycardapp.R;

import java.util.ArrayList;
import java.util.List;

public class NewsListActivity extends AppCompatActivity implements NewsView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NewsPresenter presenter;
    private ImageView badSmile;
    private Button reload;
    private Categories category;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            category = !savedInstanceState.getString(getString(R.string.categories_key_for_bundle)).isEmpty() ?
                    Categories.valueOf(savedInstanceState.getString(getString(R.string.categories_key_for_bundle))) :
                    Categories.HOME;
        }
        setContentView(R.layout.activity_news_list);
        presenter = NewsPresenterImpl.createPresenter();
        presenter.attachView(this);
        presenter.init();
    }

    private void initRecycler (@NonNull List<NewsItem> newsItems) {
        Adapter adapter = new Adapter(( ArrayList<NewsItem> ) newsItems , this);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
        } else {
            GridLayoutManager layoutManager = new GridLayoutManager(this , 2);
            recyclerView.setLayoutManager(layoutManager);
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option , menu);
        MenuItem item = menu.findItem(R.id.spinner);
        Spinner categories = ( Spinner ) item.getActionView();
        ArrayAdapter<Categories> adapter = new ArrayAdapter<>(
                this , android.R.layout.simple_list_item_1 , Categories.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected (AdapterView<?> adapterView , View view , int i , long l) {
                category = Categories.values()[i];
                presenter.getNews(category.toString());
            }

            @Override
            public void onNothingSelected (AdapterView<?> adapterView) {
                //nothing
            }
        });
        categories.setAdapter(adapter);
        if (category != null) {
            Log.d("positionItem" , String.valueOf(adapter.getPosition(category)));
            categories.setSelection(adapter.getPosition(category));
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        outState.putString(getString(R.string.categories_key_for_bundle) , category.toString());
        Log.d("It's save method" , category.toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch ( item.getItemId() ) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void initViews () {
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.news_list);
        badSmile = findViewById(R.id.bad_smile);
        reload = findViewById(R.id.try_reload);
    }

    @Override
    public void showStateError (StateError error) {
        switch ( error ) {
            case SERVERERROR: {
                finishLoading();
                badSmile.setVisibility(View.VISIBLE);
                reload.setVisibility(View.VISIBLE);
                break;
            }
            case NETWORKERROR: {
                finishLoading();
                badSmile.setVisibility(View.VISIBLE);
                reload.setVisibility(View.VISIBLE);
                break;
            }
            default:
                new Exception(new IllegalStateException("unknown state,sorry"));
        }
    }

    @Override
    public void showMessage (@NonNull String message) {
        Toast.makeText(this , message , Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadNews (@NonNull List<NewsItem> news) {
        initRecycler(news);
    }

    @Override
    public void startLoading () {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoading () {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy () {
        presenter.finish();
        presenter.detachView();
        super.onDestroy();
    }

}
