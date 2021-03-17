package com.vibs.vibsmovie;


import com.vibs.vibsmovie.models.ResponseMovies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository = new MovieRepository();

    private MutableLiveData<ResponseMovies> _movieResponse = new MutableLiveData();
    public LiveData<ResponseMovies> movieResponse = _movieResponse;

    private MutableLiveData<Boolean> _isLoading = new MutableLiveData<Boolean>();
    public LiveData<Boolean> isLoading = _isLoading;

    public void setMovieResponse(ResponseMovies data) {
        _movieResponse.setValue(data);
    }

    public void isLoadingDisplay(Boolean flag) {
        _isLoading.setValue(flag);
    }

    public LiveData<ResponseMovies> getMovies(String apiKey) {
        return movieRepository.getMovies(apiKey);
    }
}
