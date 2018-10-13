package com.example.user.mycardapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.mycardapp.Data.NewsItem;

import java.util.ArrayList;

public class NewsViewHolder extends RecyclerView.ViewHolder {
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