package com.example.toeic.feature.login;

import android.util.Log;

import com.example.base.BasePresenterImpl;
import com.example.toeic.data.model.User;
import com.example.toeic.data.network.HttpHelper;
import com.example.toeic.data.network.LoginResponse;
import com.example.toeic.data.network.Service;

import io.reactivex.observers.DisposableSingleObserver;

public class LoginPresenterImpl extends BasePresenterImpl implements LoginPresenter {
    private LoginView loginView;

    @Override
    public void onClickButtonLogin(String username, String password) {
        callApi(Service
                .callUserService()
                .login(new User(username, password))
                .compose(Service.getRxSingleSchedulers().applySchedulers())
                .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse loginResponse) {
                        loginSuccess(loginResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e.getMessage() != null) {
                            Log.e("ERROR", e.getMessage());
                        }
                    }
                }));
    }

    private void loginSuccess(LoginResponse loginResponse) {
        HttpHelper.initRetrofit(loginResponse.accessToken);
        loginView = (LoginView) view;
        loginView.loginSuccess();
    }
}
