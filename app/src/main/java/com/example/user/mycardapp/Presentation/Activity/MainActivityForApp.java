package com.example.user.mycardapp.Presentation.Activity;

import android.os.Bundle;

import com.example.user.mycardapp.Presentation.Fragments.IntroMainFragment;
import com.example.user.mycardapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivityForApp extends AppCompatActivity implements MainActivityOfApp {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_app);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.conteiner , new IntroMainFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void changeFragment (Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.conteiner , fragment)
                .addToBackStack(null)
                .commit();
    }
}
