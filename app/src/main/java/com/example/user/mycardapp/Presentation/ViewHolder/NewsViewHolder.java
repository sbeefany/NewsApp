package com.example.user.mycardapp.Presentation.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.mycardapp.Data.NewsItem;
import com.example.user.mycardapp.Presentation.Activity.MainActivityOfApp;
import com.example.user.mycardapp.Presentation.Fragments.NewsDetailsFragment;
import com.example.user.mycardapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private TextView category;
    private TextView title;
    private TextView description;
    private TextView date;
    private ImageView image;
    private CardView cardView;

    public NewsViewHolder (@NonNull View itemView) {
        super(itemView);
        category = itemView.findViewById(R.id.category);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
        date = itemView.findViewById(R.id.date);
        image = itemView.findViewById(R.id.image);
        cardView = itemView.findViewById(R.id.card_view);
    }

    public void initData (int position , Context context , ArrayList<NewsItem> news) {
        category.setText(news.get(position).getCategory());
        title.setText(news.get(position).getTitle());
        description.setText(news.get(position).getText());
        date.setText(news.get(position).getPublishDate().toString());
        Glide.with(context).load(news.get(position).getImageUrl()).into(image);
    }

    public void setupClickListener (final int position , final Context context , final ArrayList<NewsItem> news) {
        cardView.setOnClickListener(view -> {
            if (context instanceof MainActivityOfApp) {
                MainActivityOfApp activity = ( MainActivityOfApp ) context;
                activity.changeFragment(NewsDetailsFragment.newInstanse(news.get(position).getId()));
            }
        });
    }

}