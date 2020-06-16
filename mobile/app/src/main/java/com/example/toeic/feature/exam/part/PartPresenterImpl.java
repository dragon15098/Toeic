package com.example.toeic.feature.exam.part;

import com.example.base.BasePresenterImpl;
import com.example.toeic.data.model.Exam;
import com.example.toeic.data.network.Service;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

class PartPresenterImpl extends BasePresenterImpl implements PartPresenter {
    public List<Exam> data = new ArrayList<>();

    @Override
    public void getAllExam() {
        callApi(Service.
                callExamService()
                .getAllExam()
                .compose(Service.getRxSingleSchedulers().applySchedulers())
                .subscribeWith(new DisposableSingleObserver<List<Exam>>() {
                    @Override
                    public void onSuccess(List<Exam> exams) {
                        data.clear();
                        data.addAll(exams);
                        PartView partOneView = (PartView) view;
                        partOneView.notifyRecycleView();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public List<Exam> getData() {
        return data;
    }
}
