package com.example.activitysample.OnBoarding;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.activitysample.MainActivity;
import com.example.activitysample.R;

public class SecondScreen extends Fragment {

    TextView tvSkip, tvNext;

    public SecondScreen() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second_screen, container, false);

        RelativeLayout relativeLayout = view.findViewById(R.id.layout2);
        AnimationDrawable drawable = (AnimationDrawable) relativeLayout.getBackground();
        drawable.setEnterFadeDuration(2000);
        drawable.setExitFadeDuration(2000);
        drawable.start();

//        tvNext = view.findViewById(R.id.tvNext);
        tvSkip = view.findViewById(R.id.tvSkip);

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

//        tvNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Not Working", Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;

    }
}