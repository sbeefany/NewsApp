package com.example.user.mycardapp.Presentation.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.mycardapp.Presentation.Activity.MainActivityOfApp;
import com.example.user.mycardapp.Presentation.Adapter.ViewPagerAdapter;
import com.example.user.mycardapp.Presentation.Presenter.IntroPresenter.IntroPresenter;
import com.example.user.mycardapp.Presentation.Presenter.IntroPresenter.IntroPresenterImpl;
import com.example.user.mycardapp.Presentation.Presenter.IntroPresenter.IntroView;
import com.example.user.mycardapp.R;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;

import static android.content.Context.MODE_PRIVATE;

public class IntroMainFragment extends Fragment implements IntroView {
    private Button getStarted;
    private IntroPresenter presenter;
    private SharedPreferences sharedPreferences;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private MainActivityOfApp activityOfApp;

    @Override
    public void onAttach (Context context) {
        super.onAttach(context);
        if (getActivity() instanceof MainActivityOfApp)
            activityOfApp = ( MainActivityOfApp ) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_intro , container , false);
        sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("counter" , MODE_PRIVATE);
        initViews(view);

        presenter = IntroPresenterImpl.getInstance();
        presenter.attachView(this);
        presenter.showIntro(getCountFropSharedPref());

        getStarted.setOnClickListener(view1 -> {
            presenter.clickedGetStarted();
        });
        return view;
    }

    private void initViews (View view) {
        getStarted = view.findViewById(R.id.button_get_started);
        viewPager = view.findViewById(R.id.view_pager);
        circleIndicator = view.findViewById(R.id.indicator);
    }

    @Override
    public void toNewsActivity () {
        saveCount(getCountFropSharedPref() + 1);
        activityOfApp.changeFragment(new NewsListFragment());
    }

    @SuppressLint("CommitPrefEdits")
    private void saveCount (int count) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("counter" , count);
        editor.apply();
    }

    @Override
    public void showIntro () {
        PagerAdapter adapter = new ViewPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
        (( ViewPagerAdapter ) adapter).addFragment(com.example.user.mycardapp.Presentation.Fragments.IntroFragment.newInstance(R.drawable.intro , getResources().getString(R.string.intro_text)));
        (( ViewPagerAdapter ) adapter).addFragment(com.example.user.mycardapp.Presentation.Fragments.IntroFragment.newInstance(R.drawable.intro , getResources().getString(R.string.intro_text_details)));
        (( ViewPagerAdapter ) adapter).addFragment(com.example.user.mycardapp.Presentation.Fragments.IntroFragment.newInstance(R.drawable.avatarka , getResources().getString(R.string.example_about_person)));
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
    }

    private int getCountFropSharedPref () {
        return sharedPreferences.getInt("counter" , 1);
    }

    @Override
    public void onDestroy () {
        activityOfApp = null;
        presenter.finish();
        super.onDestroy();
    }

}
