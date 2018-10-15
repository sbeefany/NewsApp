package com.example.user.mycardapp.Presentation.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.mycardapp.R;

import java.util.Date;
import java.util.Objects;

public class NewsDetailsActivity extends AppCompatActivity {

    private String title;
    private String description;
    private Date date;
    private ImageView image;
    private TextView titleTextView;
    private TextView dateTextView;
    private TextView descriptionTextView;
    private String imageUrl;
    private String category;

    public static void toNewsDetailsActivity ( @NonNull Context context , @NonNull String imageUrl ,
                                               @NonNull String category , @NonNull String title ,
                                               @NonNull String description , @NonNull Date date ) {
        Intent intent = new Intent ( context , NewsDetailsActivity.class );
        intent.putExtra ( "imageUrl" , imageUrl );
        intent.putExtra ( "category" , category );
        intent.putExtra ( "title" , title );
        intent.putExtra ( "description" , description );
        intent.putExtra ( "date" , date );
        context.startActivity ( intent );
    }

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_news_details );
        getDataFromIntent ();
        initToolBar ();
        initViews ();
        dataToView ();
    }

    private void getDataFromIntent () {
        imageUrl = getIntent ().getStringExtra ( "imageUrl" );
        category = getIntent ().getStringExtra ( "category" );
        title = getIntent ().getStringExtra ( "title" );
        description = getIntent ().getStringExtra ( "description" );
        date = ( Date ) getIntent ().getSerializableExtra ( "date" );
    }

    private void initViews () {
        image = findViewById ( R.id.image );
        titleTextView = findViewById ( R.id.title );
        descriptionTextView = findViewById ( R.id.description );
        dateTextView = findViewById ( R.id.date );
    }

    private void dataToView () {
        Glide.with ( this ).load ( imageUrl ).into ( image );
        titleTextView.setText ( title );
        descriptionTextView.setText ( description );
        dateTextView.setText ( date.toString () );
    }

    private void initToolBar () {
        Objects.requireNonNull ( getSupportActionBar () ).setTitle ( category );
    }
}
