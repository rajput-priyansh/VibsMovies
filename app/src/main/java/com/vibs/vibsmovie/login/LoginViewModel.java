package com.vibs.vibsmovie.login;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Boolean> _isValidEmail = new MutableLiveData();
    public LiveData<Boolean> isValidEmail = _isValidEmail;

    private MutableLiveData<Boolean> _isValidPassword = new MutableLiveData();
    public LiveData<Boolean> isValidPassword = _isValidPassword;

    private MutableLiveData<Boolean> _isLoginClick = new MutableLiveData();
    public LiveData<Boolean> isLoginClick = _isLoginClick;

    private MutableLiveData<Boolean> _isLoginButtonEnable = new MutableLiveData();
    public LiveData<Boolean> isLoginButtonEnable = _isLoginButtonEnable;

    public void init() {
        _isLoginClick.setValue(false);
        _isLoginButtonEnable.setValue(false);
    }

    public void setIsValidEmail(Boolean data) {
        _isValidEmail.setValue(data);
    }

    public void setIsValidPassword(Boolean data) {
        _isValidPassword.setValue(data);
    }

    public void setIsLoginButtonEnable(Boolean data) {
        _isLoginButtonEnable.setValue(data);
    }

    public void onLoginClick() {
        _isLoginClick.setValue(true);
    }

}
