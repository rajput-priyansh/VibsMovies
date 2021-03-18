package com.vibs.vibsmovie;


import com.vibs.vibsmovie.api.ApiRepository;
import com.vibs.vibsmovie.models.ResponseMovies;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import rx.Observer;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

public class MovieRepository {
    private final ApiRepository apiRepository;

    @Inject
    public MovieRepository(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    public LiveData<ResponseMovies> getMovies(String apiKey) {
        MutableLiveData<ResponseMovies> liveData = new MutableLiveData<ResponseMovies>();

        apiRepository.getApiInterface().getMovieList(apiKey, "en-US", 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseMovies>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.setValue(null);
                    }

                    @Override
                    public void onNext(ResponseMovies responseMovies) {
                        liveData.setValue(responseMovies);
                    }
                });

        return liveData;
    }
}
