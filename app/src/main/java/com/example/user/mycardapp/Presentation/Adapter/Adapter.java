package com.example.user.mycardapp.Presentation.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Presentation.ViewHolder.NewsViewHolder;
import com.example.user.mycardapp.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<NewsViewHolder> {

    private ArrayList<NewsItem> news;
    private Context context;

    public Adapter (ArrayList<NewsItem> news , Context context) {
        this.news = news;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup , int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item , viewGroup , false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull NewsViewHolder viewHolder , int i) {
        viewHolder.initData(i , context , news);
        viewHolder.setupClickListener(i , context , news);
    }

    @Override
    public int getItemCount () {
        return news.size();
    }

}
