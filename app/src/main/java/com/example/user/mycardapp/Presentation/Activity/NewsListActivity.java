package com.example.user.mycardapp.Presentation.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
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
import com.example.user.mycardapp.Presentation.Presenter.NewsPresenter.NewsPresenter;
import com.example.user.mycardapp.Presentation.Presenter.NewsPresenter.NewsPresenterImpl;
import com.example.user.mycardapp.Presentation.Presenter.NewsPresenter.NewsView;
import com.example.user.mycardapp.Presentation.StateError;
import com.example.user.mycardapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewsListActivity extends AppCompatActivity implements NewsView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NewsPresenter presenter;
    private ImageView badSmile;
    private Button reload;
    private Categories category;
    private Spinner categories;
    private FloatingActionButton floatingActionButton;

    public static void toNewsListActivity (Activity activity) {
        Intent intent = new Intent(activity , NewsListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            category = !savedInstanceState.getString(getString(R.string.categories_key_for_bundle)).isEmpty() ?
                    Categories.valueOf(savedInstanceState.getString(getString(R.string.categories_key_for_bundle))) :
                    Categories.Home;
        }
        setContentView(R.layout.activity_news_list);
        presenter = NewsPresenterImpl.createPresenter(getApplicationContext());
        presenter.attachView(this);
        presenter.init();

        reload.setOnClickListener(view -> {
            presenter.getNews(category.toString() , false);
        });
        floatingActionButton.setOnClickListener(view -> {
            presenter.getNews(category.toString() , false);
        });
    }

    @Override
    protected void onStart () {
        super.onStart();
        if (category != null) {
            presenter.getNews(category.toString() , true);
        }
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
        categories = ( Spinner ) item.getActionView();
        ArrayAdapter<Categories> adapter = new ArrayAdapter<>(
                this , android.R.layout.simple_list_item_1 , Categories.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected (AdapterView<?> adapterView , View view , int i , long l) {
                category = Categories.values()[i];
                presenter.getNews(category.toString() , true);
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
        floatingActionButton = findViewById(R.id.load_data_button);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void showStateError (@NonNull StateError error) {
        switch ( error ) {
            case SERVERERROR: {
                finishLoading();
                badSmile.setVisibility(View.VISIBLE);
                reload.setVisibility(View.VISIBLE);
                categories.setVisibility(View.GONE);
                floatingActionButton.setVisibility(View.GONE);
                showMessage(getString(R.string.TechnicalProblemsMessage));
                recyclerView.setVisibility(View.GONE);
                break;
            }
            case NETWORKERROR: {
                finishLoading();
                badSmile.setVisibility(View.VISIBLE);
                reload.setVisibility(View.VISIBLE);
                categories.setVisibility(View.GONE);
                floatingActionButton.setVisibility(View.GONE);
                showMessage(getString(R.string.NetworkErrorMessage));
                recyclerView.setVisibility(View.GONE);
                break;
            }
            default:
                new IllegalStateException("unknown state,sorry");
        }
    }

    private void showMessage (@NonNull String message) {
        Toast.makeText(this , message , Toast.LENGTH_LONG).show();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void loadNews (@NonNull List<NewsItem> news) {
        recyclerView.setVisibility(View.VISIBLE);
        floatingActionButton.setVisibility(View.VISIBLE);
        badSmile.setVisibility(View.GONE);
        reload.setVisibility(View.GONE);
        categories.setVisibility(View.VISIBLE);
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
