package com.example.user.mycardapp.Presentation.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.mycardapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class IntroFragment extends Fragment {

    private static final String ARGS_TEXT = "args:text";
    private static final String ARGS_PHOTOID = "args:photoId";
    private TextView text;
    private ImageView photo;

    public static IntroFragment newInstance (int photoId , String text) {
        IntroFragment introFragment = new IntroFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_PHOTOID , photoId);
        bundle.putString(ARGS_TEXT , text);
        introFragment.setArguments(bundle);

        return introFragment;
    }

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.intro_fragment , container , false);
        initViews(view);
        return view;
    }

    private void initViews (View view) {
        text = view.findViewById(R.id.description);
        photo = view.findViewById(R.id.sampleScreen);
    }

    @Override
    public void onViewCreated (@NonNull View view , @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view , savedInstanceState);
        if (getArguments() != null) {
            text.setText(getArguments().getString(ARGS_TEXT));
            photo.setImageResource(getArguments().getInt(ARGS_PHOTOID));
        }

    }
}
