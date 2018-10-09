package com.example.user.mycardapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.mycardapp.Data.DataUtils;
import com.example.user.mycardapp.Data.NewsItem;

import java.util.ArrayList;

public class NewsListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        initViews();
        initRecycler();

    }

    private void initRecycler() {
        adapter = new Adapter((ArrayList<NewsItem>) DataUtils.generateNews(), this);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
        } else {
            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(layoutManager);
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_activity:
                startActivity(new Intent(this, AboutActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void initViews() {
        recyclerView = findViewById(R.id.news_list);
    }
}
