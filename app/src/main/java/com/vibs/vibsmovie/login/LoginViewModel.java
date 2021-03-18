package com.vibs.vibsmovie.login;



import androidx.databinding.ObservableBoolean;
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

    public ObservableBoolean isLoginButtonEnable;

    /**
     * Init Member
     */
    public void init() {
        isLoginButtonEnable = new ObservableBoolean();
        _isLoginClick.setValue(false);
        isLoginButtonEnable.set(false);
    }

    /**
     * Set _isValidEmail member
     * @param data
     */
    public void setIsValidEmail(Boolean data) {
        _isValidEmail.setValue(data);
    }

    /**
     * Set _isValidPassword member
     * @param data
     */
    public void setIsValidPassword(Boolean data) {
        _isValidPassword.setValue(data);
    }

    /**
     * set isLoginButtonEnable member
     * @param data
     */
    public void setIsLoginButtonEnable(Boolean data) {
        isLoginButtonEnable.set(data);
    }

    /**
     * Perform Login button click event
     */
    public void onLoginClick() {
        _isLoginClick.setValue(true);
    }

}
