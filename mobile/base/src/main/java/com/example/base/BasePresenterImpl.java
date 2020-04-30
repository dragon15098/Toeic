package com.example.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

public class BasePresenterImpl implements BasePresenter {
    public BaseView view;
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(BaseView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
        view = null;
    }

    @Override
    public void callApi(DisposableSingleObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observer);
    }

}
