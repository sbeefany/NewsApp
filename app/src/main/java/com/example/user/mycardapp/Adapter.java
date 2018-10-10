package com.example.user.mycardapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.mycardapp.Data.NewsItem;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter <Adapter.NewsViewHolder> {
    private ArrayList <NewsItem> news;
    private Context context;

    public Adapter ( ArrayList <NewsItem> news , Context context ) {
        this.news = news;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder ( @NonNull ViewGroup viewGroup , int i ) {
        View view = LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.news_item , viewGroup , false );
        NewsViewHolder viewHolder = new NewsViewHolder ( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder ( @NonNull NewsViewHolder viewHolder , int i ) {
        viewHolder.initData ( i , context , news );
        viewHolder.setupClickListener ( i , context , news );
    }

    @Override
    public int getItemCount () {
        return news.size ();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView category;
        TextView title;
        TextView description;
        TextView date;
        ImageView image;
        CardView cardView;

        public NewsViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            category = itemView.findViewById ( R.id.category );
            title = itemView.findViewById ( R.id.title );
            description = itemView.findViewById ( R.id.description );
            date = itemView.findViewById ( R.id.date );
            image = itemView.findViewById ( R.id.image );
            cardView = itemView.findViewById ( R.id.card_view );
        }

        public void initData ( int position , Context context , ArrayList <NewsItem> news ) {
            category.setText ( news.get ( position ).getCategory ().getName () );
            title.setText ( news.get ( position ).getTitle () );
            description.setText ( news.get ( position ).getPreviewText () );
            date.setText ( news.get ( position ).getPublishDate ().toString () );
            Glide.with ( context ).load ( news.get ( position ).getImageUrl () ).into ( image );

        }

        public void setupClickListener ( final int position , final Context context , final ArrayList <NewsItem> news ) {
            cardView.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick ( View view ) {
                    NewsDetailsActivity.toNewsDetailsActivity ( context , news.get ( position ).getImageUrl () , news.get ( position ).getCategory ().getName () , news.get ( position ).getTitle () , news.get ( position ).getFullText () , news.get ( position ).getPublishDate () );
                }
            } );
        }
    }
}
