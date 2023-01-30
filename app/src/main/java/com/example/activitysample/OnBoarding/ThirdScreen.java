package com.example.activitysample.OnBoarding;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.activitysample.MainActivity;
import com.example.activitysample.R;

public class ThirdScreen extends Fragment {

    AppCompatButton btnGetStarted;

    public ThirdScreen() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_third_screen, container, false);

        RelativeLayout relativeLayout = view.findViewById(R.id.layout3);
        AnimationDrawable drawable = (AnimationDrawable) relativeLayout.getBackground();
        drawable.setEnterFadeDuration(2000);
        drawable.setExitFadeDuration(2000);
        drawable.start();

        btnGetStarted = view.findViewById(R.id.btnGetStarted);
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;

    }
}