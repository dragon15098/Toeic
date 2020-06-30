package com.example.toeic.feature.practice.part_five_exam;

import com.example.toeic.data.model.Question;
import com.example.toeic.data.network.Service;
import com.example.toeic.feature.practice.part_exam.question.PartQuestionExamPresentImpl;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class PartFiveExamPresentImpl extends PartQuestionExamPresentImpl implements PartFiveExamPresent {
    @Override
    public void getAllQuestionPartFiveByExamId(Integer examId) {
        callApi(Service.callQuestionService()
                .findPartFiveByExamId(examId)
                .compose(Service.getRxSingleSchedulers().applySchedulers())
                .subscribeWith(new DisposableSingleObserver<List<Question>>() {
                    @Override
                    public void onSuccess(List<Question> questions) {
                        getQuestionPartFiveSuccess(questions);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public String getQuestion() {
        return getCurrentQuestion().getQuestion();
    }

    private void getQuestionPartFiveSuccess(List<Question> questions) {
        this.questions = questions;
        partQuestionExamView = (PartFiveExamView) view;
        partQuestionExamView.notifyView();
    }
}
