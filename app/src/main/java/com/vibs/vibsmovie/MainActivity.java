package com.vibs.vibsmovie;


import android.os.Bundle;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.vibs.vibsmovie.adapter.MovieAdapter;
import com.vibs.vibsmovie.base.BaseActivity;
import com.vibs.vibsmovie.models.ResultsItem;
import com.vibs.vibsmovie.util.SpacesItemDecoration;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity {

    private MovieViewModel movieViewModel;

    private KProgressHUD progressHUD;

    private ArrayList<ResultsItem> movieList;

    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        movieList = new ArrayList<>();
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
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
        RecyclerView rvMovies = findViewById(R.id.rvMovies);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set the title
        getSupportActionBar().setTitle(getString(R.string.app_name));

        rvMovies.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        rvMovies.addItemDecoration(
                new SpacesItemDecoration(
                        (int)getResources().getDimension(R.dimen._8sdp)
                )
        );
        rvMovies.setHasFixedSize(true);

        movieAdapter = new MovieAdapter(movieList);

        rvMovies.setAdapter(movieAdapter);

    }

    @Override
    protected void observed() {
        super.observed();
        movieViewModel.movieResponse.observe(this, responseMovies -> {
            movieList.clear();
            movieList.addAll(responseMovies.getResults());
            movieAdapter.notifyDataSetChanged();
        });

        movieViewModel.isLoading.observe(this, aBoolean -> {
            if (aBoolean) {
                progressHUD.show();
            } else {
                progressHUD.dismiss();
            }
        });
    }

    private void getAllMovie(){
        movieViewModel.isLoadingDisplay(true);
        movieViewModel.getMovies(BuildConfig.API_KEY).observe(this, responseMovies -> {
            movieViewModel.isLoadingDisplay(false);
            movieViewModel.setMovieResponse(responseMovies);
        });
    }
}