package com.vibs.vibsmovie.api;


import javax.inject.Inject;

public class ApiRepository {
    private final ApiInterface apiInterface;

    @Inject
    public ApiRepository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

}
