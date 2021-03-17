package com.vibs.vibsmovie;

import com.vibs.vibsmovie.login.LoginRepository;
import com.vibs.vibsmovie.login.LoginViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

class MovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private LoginRepository repository;

    public MovieViewModelFactory(LoginRepository repository) {
        super();
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel();
//        return (T) new LoginViewModel(repository);
    }
}