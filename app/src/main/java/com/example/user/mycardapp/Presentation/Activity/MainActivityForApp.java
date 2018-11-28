package com.example.user.mycardapp.Presentation.Activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.user.mycardapp.Presentation.Fragments.EmptyFragment;
import com.example.user.mycardapp.Presentation.Fragments.IntroMainFragment;
import com.example.user.mycardapp.Presentation.Fragments.NewsListFragment;
import com.example.user.mycardapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivityForApp extends AppCompatActivity implements MainActivityOfApp {
    private FragmentManager fragmentManager;
    private FrameLayout firstConteiner;
    private FrameLayout secondConteiner;
    private boolean isMainMenu = false;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_app);
        firstConteiner = findViewById(R.id.conteiner);
        secondConteiner = findViewById(R.id.land_conteiner);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.conteiner , new IntroMainFragment())
                    .commit();
        }
    }

    @Override
    public void changeFragment (Fragment fragment) {

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (fragment instanceof NewsListFragment) {
                isMainMenu=true;
                fragmentManager.beginTransaction()
                        .replace(R.id.land_conteiner , fragment)
                        .commit();
                fragmentManager.beginTransaction()
                        .replace(R.id.conteiner , new EmptyFragment())
                        .commit();
            } else {
                fragmentManager.beginTransaction()
                        .replace(R.id.conteiner , fragment)
                        .addToBackStack(null)
                        .commit();
                isMainMenu=false;
            }
        } else {
            if (fragment instanceof NewsListFragment) {
                isMainMenu = true;
                firstConteiner.setVisibility(View.GONE);
                secondConteiner.setVisibility(View.VISIBLE);
                fragmentManager.beginTransaction()
                        .replace(R.id.land_conteiner , fragment)
                        .commit();
            } else {
                isMainMenu = false;
                secondConteiner.setVisibility(View.GONE);
                firstConteiner.setVisibility(View.VISIBLE);
                fragmentManager.beginTransaction()
                        .replace(R.id.conteiner , fragment)
                        .addToBackStack(null)
                        .commit();
            }
        }
    }

    @Override
    public void onBackPressed () {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT && !isMainMenu) {
            isMainMenu = true;
            firstConteiner.setVisibility(View.GONE);
            secondConteiner.setVisibility(View.VISIBLE);
        }
        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged (Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            firstConteiner.setVisibility(View.VISIBLE);
            secondConteiner.setVisibility(View.VISIBLE);
        } else {
            if (!isMainMenu) {
                secondConteiner.setVisibility(View.GONE);
                firstConteiner.setVisibility(View.VISIBLE);
            } else {
                secondConteiner.setVisibility(View.VISIBLE);
                firstConteiner.setVisibility(View.GONE);
            }
        }
        super.onConfigurationChanged(newConfig);
    }
}
