package com.vibs.vibsmovie.login;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {


    private MutableLiveData<Boolean> _isValidEmail = new MutableLiveData();
    public LiveData<Boolean> isValidEmail = _isValidEmail;

    private MutableLiveData<Boolean> _isValidPassword = new MutableLiveData();
    public LiveData<Boolean> isValidPassword = _isValidPassword;

    public void setIsValidEmail(Boolean data) {
        _isValidEmail.setValue(data);
    }

    public void setIsValidPassword(Boolean data) {
        _isValidPassword.setValue(data);
    }

}
