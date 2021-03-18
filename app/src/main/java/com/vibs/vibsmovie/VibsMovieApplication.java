package com.vibs.vibsmovie;

import android.app.Application;

import com.vibs.vibsmovie.api.ApiClient;
import com.vibs.vibsmovie.login.LoginActivity;

import dagger.Component;

@Component(modules = ApiClient.class)
interface ApplicationComponent {
//    void inject(LoginActivity loginActivity);
    void inject(MainActivity mainActivity);
}

public class VibsMovieApplication extends Application {


    public ApplicationComponent appComponent = DaggerApplicationComponent.create();
}
