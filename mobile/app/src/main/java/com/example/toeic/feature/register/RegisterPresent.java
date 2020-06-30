package com.example.toeic.feature.register;

import com.example.base.BasePresenter;

public interface RegisterPresent extends BasePresenter {
    void register(String username, String password, String confirmPassword);
}
