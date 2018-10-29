package com.example.user.mycardapp.Presentation.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.user.mycardapp.R;

import java.util.Objects;

public class NewsDetailsActivity extends AppCompatActivity {

    private String url;
    private WebView webView;

    public static void toNewsDetailsActivity (@NonNull Context context , @NonNull String uri) {
        Intent intent = new Intent(context , NewsDetailsActivity.class);
        intent.putExtra("url" , uri);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        getDataFromIntent();
        initToolBar();
        initViews();
        showDataInWeb(url);

    }

    private void getDataFromIntent () {
        url = getIntent().getStringExtra("url");

    }

    private void initViews () {
        webView = findViewById(R.id.webView);
    }


    private void initToolBar () {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Detail");
    }

    private void showDataInWeb(String uri){
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl(uri);
    }

}
