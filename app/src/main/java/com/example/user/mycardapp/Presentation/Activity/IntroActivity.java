package com.example.user.mycardapp.Presentation.Activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.example.user.mycardapp.Presentation.Presenter.IntroPresenter.IntroPresenter;
import com.example.user.mycardapp.Presentation.Presenter.IntroPresenter.IntroPresenterImpl;
import com.example.user.mycardapp.Presentation.Presenter.IntroPresenter.IntroView;
import com.example.user.mycardapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity implements IntroView {
    private IntroPresenter presenter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        sharedPreferences = getSharedPreferences("counter" , MODE_PRIVATE);
        Button get_started = findViewById(R.id.button_get_started);
        presenter = IntroPresenterImpl.getInstance();
        presenter.attachView(this);
        presenter.showIntro(getCountFropSharedPref());

        get_started.setOnClickListener(view -> {
            presenter.clickedGetStarted();
        });
    }

    @Override
    public void toNewsActivity () {
        saveCount(getCountFropSharedPref() + 1);
        NewsListActivity.toNewsListActivity(this);
    }

    @SuppressLint("CommitPrefEdits")
    private void saveCount (int count) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counter" , count);
        editor.apply();
    }

    @Override
    public void showIntro () {
        //
    }

    private int getCountFropSharedPref () {
        return sharedPreferences.getInt("counter" , 1);
    }

    @Override
    protected void onDestroy () {
        presenter.finish();
        super.onDestroy();
    }
}
