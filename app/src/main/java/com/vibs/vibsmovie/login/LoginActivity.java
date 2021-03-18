package com.vibs.vibsmovie.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vibs.vibsmovie.MainActivity;
import com.vibs.vibsmovie.R;
import com.vibs.vibsmovie.base.BaseActivity;
import com.vibs.vibsmovie.databinding.ActivityLoginBinding;
import com.vibs.vibsmovie.databinding.ActivityMainBinding;

import java.util.regex.Pattern;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;


public class LoginActivity extends BaseActivity {

    private LoginViewModel loginViewModel;

    private TextInputLayout tiEmail;
    private TextInputEditText tietEmail;

    private TextInputLayout tiPassword;
    private TextInputEditText tietPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActivityLoginBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        initData();

        if (savedInstanceState == null) {
            loginViewModel.init();
        }
        activityBinding.setModel(loginViewModel);

        initUI();

        observed();
    }

    @Override
    protected void initData() {
        super.initData();
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    protected void initUI() {
        super.initUI();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(getString(R.string.app_name));

        tiEmail = findViewById(R.id.tiEmail);
        tietEmail = findViewById(R.id.tietEmail);
        tiPassword = findViewById(R.id.tiPassword);
        tietPassword = findViewById(R.id.tietPassword);

        tietEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Empty Function
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Empty Function
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkValidEmail();
            }
        });

        tietPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Empty Function
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Empty Function
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkValidPassword();
            }
        });

        updateLoginButtonUI();
    }

    @Override
    protected void observed() {
        super.observed();

        loginViewModel.isValidEmail.observe(this, aBoolean -> {
            if (aBoolean) {
                tiEmail.setErrorEnabled(false);
            } else {
                tiEmail.setError(getString(R.string.error_email_invalid));
            }

            updateLoginButtonUI();
        });

        loginViewModel.isValidPassword.observe(this, aBoolean -> {
            if (aBoolean) {
                tiPassword.setErrorEnabled(false);
            } else {
                tiPassword.setError(getString(R.string.error_password_invalid));
            }

            updateLoginButtonUI();
        });

        loginViewModel.isLoginClick.observe(this, aBoolean -> {
            if (aBoolean) {
                //Goto Main Activity
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    /**
     * Validate the login button
     */
    private void updateLoginButtonUI() {
        loginViewModel.setIsLoginButtonEnable((loginViewModel.isValidEmail.getValue() != null ? loginViewModel.isValidEmail.getValue() : false)
                && (loginViewModel.isValidPassword.getValue() != null ? loginViewModel.isValidPassword.getValue() : false));
    }

    /**
     * Validate the email
     */
    private void checkValidEmail() {
        if (tietEmail.getText().toString().trim().isEmpty()) {
            loginViewModel.setIsValidEmail(false);
        } else {
            loginViewModel.setIsValidEmail(Pattern.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+", tietEmail.getText().toString().trim()));
        }
    }

    /**
     * Validate the password
     */
    private void checkValidPassword() {
        if (tietPassword.getText().toString().trim().isEmpty()) {
            loginViewModel.setIsValidPassword(false);
        } else
            loginViewModel.setIsValidPassword(tietPassword.getText().toString().trim().length() >= 8 && tietPassword.getText().toString().trim().length() <= 15);
    }
}
