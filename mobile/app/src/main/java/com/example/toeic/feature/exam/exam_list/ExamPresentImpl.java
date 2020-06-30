package com.example.toeic.feature.exam.exam_list;

import com.example.base.BasePresenterImpl;
import com.example.toeic.data.model.Exam;
import com.example.toeic.data.network.Service;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class ExamPresentImpl extends BasePresenterImpl implements ExamPresent {

    List<Exam> exams = new ArrayList<>();

    @Override
    public void getAllExam() {
        callApi(Service.callExamService()
                .getAllExam()
                .compose(Service.getRxSingleSchedulers().applySchedulers())
                .subscribeWith(new DisposableSingleObserver<List<Exam>>() {
                    @Override
                    public void onSuccess(List<Exam> exams) {
                        getAllExamSuccess(exams);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                })
        );

    }

    @Override
    public List<Exam> getExams() {
        return exams;
    }

    private void getAllExamSuccess(List<Exam> exams) {
        this.exams.clear();
        this.exams.addAll(exams);
        ExamView examView = (ExamView) view;
        examView.notifyView();
    }
}
