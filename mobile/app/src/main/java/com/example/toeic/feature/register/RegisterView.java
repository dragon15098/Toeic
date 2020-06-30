package com.example.toeic.feature.register;

import com.example.base.BaseView;

public interface RegisterView extends BaseView {
    void onRegisterSuccess();

    void onRegisterFalse(String error);
    void onPasswordConfirmNotMatch();

}
