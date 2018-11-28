package com.example.user.mycardapp.Presentation.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.mycardapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EmptyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.empty_fragment , container , false);
    }
}
