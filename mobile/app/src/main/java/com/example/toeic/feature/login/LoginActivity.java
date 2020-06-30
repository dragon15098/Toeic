package com.example.toeic.feature.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.base.BaseActivity;
import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.feature.home.HomeActivity;
import com.example.toeic.feature.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {
    @BindView(R2.id.txtUserName)
    EditText txtUserName;
    @BindView(R2.id.txtPassword)
    EditText txtPassword;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setUpPresenter();
    }

    private void setUpPresenter() {
        loginPresenter = new LoginPresenterImpl();
        loginPresenter.attachView(this);
    }


    @OnClick(R2.id.btnLogin)
    public void onButtonClick(View view) {
        loginPresenter.onClickButtonLogin(txtUserName.getText().toString(), txtPassword.getText().toString());
    }

    @OnClick(R2.id.btnRegister)
    public void onClickButtonRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
