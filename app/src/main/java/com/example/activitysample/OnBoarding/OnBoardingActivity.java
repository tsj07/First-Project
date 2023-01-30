package com.example.activitysample.OnBoarding;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.activitysample.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class OnBoardingActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    FragmentStateAdapter screenAdapter;
    DotsIndicator dotsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        viewPager2 = findViewById(R.id.viewPager);
        screenAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(screenAdapter);

        dotsIndicator = findViewById(R.id.dots_indicator);
        dotsIndicator.attachTo(viewPager2);

    }
}