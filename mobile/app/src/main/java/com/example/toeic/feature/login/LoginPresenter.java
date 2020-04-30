package com.example.toeic.feature.login;

import com.example.base.BasePresenter;

public interface LoginPresenter<V extends LoginView> extends BasePresenter {
    void onClickButtonLogin(String username, String password);
}
