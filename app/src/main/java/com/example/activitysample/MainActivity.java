package com.example.activitysample;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.activitysample.Fragments.FirstFragment;
import com.example.activitysample.Fragments.SecondFragment;
import com.example.activitysample.Fragments.ThirdFragment;
import com.example.activitysample.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new ThirdFragment());

        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case (R.id.places):
                        MainActivity.this.replaceFragment(new ThirdFragment());
                        break;
                    case (R.id.music):
                        MainActivity.this.replaceFragment(new SecondFragment());
                        break;
                    case (R.id.contacts):
                        MainActivity.this.replaceFragment(new FirstFragment());
                        break;
                }
                return true;
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment)
                .commit();

    }
}







//        adapter = new MyAdapter(this);
//        binding.pager.setAdapter(adapter);
//        binding.pager.setPageTransformer(new ZoomOutPageTransformer());

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.setReorderingAllowed(true);
//        transaction.replace(R.id.containerView, FirstFragment.class, null);
//        transaction.commit();
//
//        binding.btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.setReorderingAllowed(true);
//                transaction.replace(R.id.containerView, FirstFragment.class, null);
//                transaction.commit();
//            }
//        });
//
//        binding.btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.setReorderingAllowed(true);
//                transaction.replace(R.id.containerView, SecondFragment.class, null);
//                transaction.commit();
//            }
//        });
//
//        binding.btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.setReorderingAllowed(true);
//                transaction.replace(R.id.containerView, ThirdFragment.class, null);
//                transaction.commit();
//            }
//        });
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
////        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
//        //  Log.d("data", "onStartActivity", null);
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
////        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
//        //  Log.d("data", "onResumeActivity", null);
//    }
//
//    //
//    @Override
//    protected void onPause() {
//        super.onPause();
////        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
//        //  Log.d("data", "onPauseActivity", null);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
////        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
//        //   Log.d("data", "onStopActivity", null);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
////        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
//        //  Log.d("data", "onDestroyActivity", null);
//    }