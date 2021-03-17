package com.vibs.vibsmovie.api;


public class ApiRepository {

    private static ApiRepository apiGlobalRepository;

    public static ApiRepository getGlobalInstance(String baseUrl) {
        if (apiGlobalRepository == null) {
            apiGlobalRepository = new ApiRepository(baseUrl);
        }
        return apiGlobalRepository;
    }

    private ApiInterface apiInterface;

    public ApiRepository(String baseUrl) {
        apiInterface = ApiClient.createApiService(ApiClient.getRetroClient(baseUrl));
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

}
