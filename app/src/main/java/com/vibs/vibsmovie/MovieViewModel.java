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
    public ObservableInt emptyList;

    private MutableLiveData<Boolean> _isLoading = new MutableLiveData();
    public LiveData<Boolean> isLoading = _isLoading;

    private MutableLiveData<ResponseMovies> _movieResponse = new MutableLiveData();
    public LiveData<ResponseMovies> movieResponse = _movieResponse;

    /**
     * Init the members
     */
    public void init() {
        adapter = new MovieAdapter(R.layout.item_movie, this);
        isLoadingDisplay(false);
        emptyList = new ObservableInt(View.GONE);
    }

    /**
     * Set value of Emptyset member
     * @param data
     */
    public void setEmptyList(int data) {
        emptyList.set(data);
    }

    /**
     * Set value of movieResponse member
     * @param data
     */
    public void setMovieResponse(ResponseMovies data) {
        _movieResponse.setValue(data);
    }

    /**
     * Set value of isLoading member
     * @param flag
     */
    public void isLoadingDisplay(Boolean flag) {
        _isLoading.setValue(flag);
    }

    /**
     * Set value of adapter member and notify
     * @param movies
     */
    public void setMovieAdapter(ArrayList<ResultsItem> movies) {
        this.adapter.setMovies(movies);
        this.adapter.notifyDataSetChanged();
    }

    /**
     * Return the movie object by index
     * @param index
     * @return
     */
    public ResultsItem getMovieAt(Integer index) {
        if (movieResponse.getValue() != null && movieResponse.getValue().getResults() != null &&
                index != null &&
                movieResponse.getValue().getResults().size() > index) {
            return movieResponse.getValue().getResults().get(index);
        }
        return null;
    }

    /**
     * Return the adapter member
     * @return
     */
    public MovieAdapter getAdapter() {
        return adapter;
    }

    /**
     * Make API call to get Movie list
     * @param apiKey
     * @return
     */
    public LiveData<ResponseMovies> getMovies(String apiKey) {
        return movieRepository.getMovies(apiKey);
    }
}
