package com.vibs.vibsmovie.api;

import com.vibs.vibsmovie.models.ResponseMovies;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {

    @GET("popular")
    Observable<ResponseMovies> getMovieList(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

}
