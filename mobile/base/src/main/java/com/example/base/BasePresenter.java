package com.example.base;

import android.view.View;

import io.reactivex.observers.DisposableSingleObserver;

public interface BasePresenter {
    void attachView(BaseView view);
    void detachView();
    void callApi(DisposableSingleObserver subscriber);
}
