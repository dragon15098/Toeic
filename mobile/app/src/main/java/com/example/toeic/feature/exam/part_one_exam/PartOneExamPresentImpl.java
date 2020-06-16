package com.example.toeic.feature.exam.part_one_exam;

import com.example.toeic.data.model.Question;
import com.example.toeic.data.network.Service;
import com.example.toeic.feature.exam.part_exam.question.PartQuestionExamPresentImpl;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class PartOneExamPresentImpl extends PartQuestionExamPresentImpl implements PartOneExamPresent {

    @Override
    public void getAllQuestionPartOneByExamId(Integer examId) {
        callApi(Service.callQuestionService()
                .findPartOneByExamId(examId)
                .compose(Service.getRxSingleSchedulers().applySchedulers())
                .subscribeWith(new DisposableSingleObserver<List<Question>>() {
                    @Override
                    public void onSuccess(List<Question> questions) {
                        getQuestionPartOneSuccess(questions);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    private void getQuestionPartOneSuccess(List<Question> questions) {
        this.questions = questions;
        partQuestionExamView = (PartOneExamView) view;
        partQuestionExamView.notifyView();
    }
}
