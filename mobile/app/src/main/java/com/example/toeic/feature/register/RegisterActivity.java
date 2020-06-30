package com.example.toeic.feature.register;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.base.BaseActivity;
import com.example.toeic.R;
import com.example.toeic.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @BindView(R2.id.txtUserName)
    EditText userName;

    @BindView(R2.id.txtPassword)
    EditText password;

    @BindView(R2.id.txtConfirmPassword)
    EditText confirmPassword;

    RegisterPresent registerPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setUpPresent();
    }

    private void setUpPresent() {
        registerPresent = new RegisterPresentImpl();
        registerPresent.attachView(this);
    }

    @OnClick(R2.id.btnRegister)
    public void onRegister() {
        registerPresent.register(userName.getText().toString(), password.getText().toString(), confirmPassword.getText().toString());
    }

    @OnClick(R2.id.btnBack)
    public void onBack() {
        onBackPressed();
    }


    @Override
    public void onRegisterSuccess() {
        Toasty.success(this, R.string.register_success, Toast.LENGTH_SHORT, true).show();
        Handler handler = new Handler();
        handler.postDelayed(this::onBackPressed, 1000);
    }

    @Override
    public void onRegisterFalse(String error) {
        Toasty.error(this, error, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void onPasswordConfirmNotMatch() {
        Toasty.warning(this, R.string.confirm_password_not_match, Toast.LENGTH_SHORT, true).show();
    }
}
