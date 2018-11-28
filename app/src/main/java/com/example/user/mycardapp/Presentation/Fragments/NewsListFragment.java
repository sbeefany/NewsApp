package com.example.user.mycardapp.Presentation.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewsListFragment extends Fragment implements NewsView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private NewsPresenter presenter;
    private ImageView badSmile;
    private Button reload;
    private Categories category;
    private Spinner categories;
    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_news_list , container , false);
        initViews(view);
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            category = initCategory(savedInstanceState);
        }
        presenter = NewsPresenterImpl.createPresenter(getContext());
        presenter.attachView(this);
        presenter.init();

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

        reload.setOnClickListener(view1 -> {
            presenter.getNews(category.toString());
        });
        floatingActionButton.setOnClickListener(view1 -> {
            presenter.getNews(category.toString());
        });
        return view;
    }

    private Categories initCategory (@NonNull Bundle savedInstanceState) {
        return !Objects.requireNonNull(savedInstanceState.getString(getString(R.string.categories_key_for_bundle))).isEmpty() ?
                Categories.valueOf(savedInstanceState.getString(getString(R.string.categories_key_for_bundle))) :
                Categories.Home;
    }

    @Override
    public void onStart () {
        super.onStart();
        if (category != null) {
            presenter.getNews(category.toString());
        }
    }

    private void initRecycler (@NonNull List<NewsItem> newsItems) {
        Adapter adapter = new Adapter(( ArrayList<NewsItem> ) newsItems , getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState (@NonNull Bundle outState) {
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

    private void initViews (View view) {
        categories = Objects.requireNonNull(getActivity()).findViewById(R.id.spinner);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView = view.findViewById(R.id.news_list);
        badSmile = view.findViewById(R.id.bad_smile);
        reload = view.findViewById(R.id.try_reload);
        floatingActionButton = view.findViewById(R.id.load_data_button);
        initSpinner();
    }

    private void initSpinner () {
        categories.setVisibility(View.VISIBLE);
        ArrayAdapter<Categories> adapter = new ArrayAdapter<>(
                Objects.requireNonNull(getContext()) , android.R.layout.simple_list_item_1 , Categories.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(adapter);
        if (category != null) {
            Log.d("NEWS_LIST_FRAGMENT" , String.valueOf(adapter.getPosition(category)));
            categories.setSelection(adapter.getPosition(category));
        }
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
        Toast.makeText(getContext() , message , Toast.LENGTH_LONG).show();
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
    public void onStop () {
        presenter.finish();
        presenter.detachView();
        categories.setVisibility(View.GONE);
        super.onStop();
    }

}
