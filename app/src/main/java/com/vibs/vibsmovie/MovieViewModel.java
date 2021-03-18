package com.vibs.vibsmovie;


import android.view.View;

import com.vibs.vibsmovie.adapter.MovieAdapter;
import com.vibs.vibsmovie.models.ResponseMovies;
import com.vibs.vibsmovie.models.ResultsItem;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private final MovieRepository movieRepository;

    @Inject
    public MovieViewModel(MovieRepository repository) {
        this.movieRepository = repository;
    }

    private MovieAdapter adapter;

    private MutableLiveData<Boolean> _isLoading = new MutableLiveData();
    public LiveData<Boolean> isLoading = _isLoading;

    public ObservableInt emptyList;

    private MutableLiveData<ResponseMovies> _movieResponse = new MutableLiveData();
    public LiveData<ResponseMovies> movieResponse = _movieResponse;

    public void init() {
        adapter = new MovieAdapter(R.layout.item_movie, this);
        isLoadingDisplay(false);
        emptyList = new ObservableInt(View.GONE);
    }

    public void setEmptyList(int data) {
        emptyList.set(data);
    }

    public void setMovieResponse(ResponseMovies data) {
        _movieResponse.setValue(data);
    }

    public void isLoadingDisplay(Boolean flag) {
        _isLoading.setValue(flag);
    }

    public ResultsItem getMovieAt(Integer index) {
        if (movieResponse.getValue() != null && movieResponse.getValue().getResults() != null &&
                index != null &&
                movieResponse.getValue().getResults().size() > index) {
            return movieResponse.getValue().getResults().get(index);
        }
        return null;
    }

    public LiveData<ResponseMovies> getMovies(String apiKey) {
        return movieRepository.getMovies(apiKey);
    }

    public void setMovieAdapter(ArrayList<ResultsItem> movies) {
        this.adapter.setMovies(movies);
        this.adapter.notifyDataSetChanged();
    }

    public MovieAdapter getAdapter() {
        return adapter;
    }
}
