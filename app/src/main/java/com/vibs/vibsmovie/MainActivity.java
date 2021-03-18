package com.vibs.vibsmovie;


import android.os.Bundle;
import android.view.View;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.vibs.vibsmovie.base.BaseActivity;
import com.vibs.vibsmovie.databinding.ActivityMainBinding;
import com.vibs.vibsmovie.models.ResultsItem;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends BaseActivity {

    @Inject
    public MovieViewModel movieViewModel;

    private KProgressHUD progressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((VibsMovieApplication) getApplicationContext()).appComponent.inject(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (savedInstanceState == null) {
            movieViewModel.init();
        }
        activityBinding.setModel(movieViewModel);

        initData();

        initUI();

        observed();

        getAllMovie();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void initData() {
        super.initData();
        progressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
    }

    @Override
    protected void initUI() {
        super.initUI();

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set the title
        getSupportActionBar().setTitle(getString(R.string.app_name));

    }

    @Override
    protected void observed() {
        super.observed();

        movieViewModel.movieResponse.observe(this, responseMovies -> {
            if (responseMovies != null && responseMovies.getResults() != null && responseMovies.getResults().size() > 0) {
                movieViewModel.setMovieAdapter((ArrayList<ResultsItem>) responseMovies.getResults());
                movieViewModel.setEmptyList(View.GONE);
            } else {
                movieViewModel.setEmptyList(View.VISIBLE);
            }
        });

        movieViewModel.isLoading.observe(this, aBoolean -> {
            if (aBoolean) {
                progressHUD.show();
            } else {
                progressHUD.dismiss();
            }
        });
    }

    /**
     * Make API call to get movie list from Backend
     */
    private void getAllMovie() {
        movieViewModel.isLoadingDisplay(true);
        movieViewModel.getMovies(BuildConfig.API_KEY).observe(this, responseMovies -> {
            movieViewModel.isLoadingDisplay(false);
            movieViewModel.setMovieResponse(responseMovies);
        });
    }
}