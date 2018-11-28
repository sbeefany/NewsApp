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
import io.reactivex.annotations.NonNull;

public class MainActivityForApp extends AppCompatActivity implements MainActivityOfApp {
    private FragmentManager fragmentManager;
    private FrameLayout firstConteiner;
    private FrameLayout secondConteiner;
    private static boolean isMainMenu = false;

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
        }else{
            initViewAccordinOrientation(getResources().getConfiguration());
        }
    }

    @Override
    public void changeFragment (Fragment fragment) {

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (fragment instanceof NewsListFragment) {
                isMainMenu = true;
                replaceFragment(fragment , R.id.land_conteiner , false);
                replaceFragment(new EmptyFragment() , R.id.conteiner , false);
            } else {
                replaceFragment(fragment , R.id.conteiner , true);
                isMainMenu = false;
            }
        } else {
            if (fragment instanceof NewsListFragment) {
                isMainMenu = true;
                replaceFragment(fragment , R.id.land_conteiner , false);
                showMenu();
            } else {
                isMainMenu = false;
                replaceFragment(fragment , R.id.conteiner , true);
                showDetail();
            }
        }
    }

    private void replaceFragment (@NonNull Fragment fragment , @NonNull int conteiner , @NonNull boolean doesItToBackStack) {
        if (doesItToBackStack) {
            fragmentManager.beginTransaction()
                    .replace(conteiner , fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(conteiner , fragment)
                    .commit();
        }
    }

    @Override
    public void onBackPressed () {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT && !isMainMenu) {
            isMainMenu = true;
            showMenu();
        }
        if(isMainMenu){
            finish();
        }
        super.onBackPressed();
    }

    private void initViewAccordinOrientation (Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            if (isMainMenu && fragmentManager.getBackStackEntryCount() == 0) {
                replaceFragment(new EmptyFragment() , R.id.conteiner , false);
            }
            if(!isMainMenu&&fragmentManager.getBackStackEntryCount() == 0){
                showDetail();
                return;
            }
            firstConteiner.setVisibility(View.VISIBLE);
            secondConteiner.setVisibility(View.VISIBLE);
        } else {
            if (!isMainMenu) {
                showDetail();
            } else {
                showMenu();
            }
        }
    }

    private void showDetail () {
        secondConteiner.setVisibility(View.GONE);
        firstConteiner.setVisibility(View.VISIBLE);
    }

    private void showMenu () {
        secondConteiner.setVisibility(View.VISIBLE);
        firstConteiner.setVisibility(View.GONE);
    }
}
