package com.example.toeic.feature.exam.part_two_exam;

import com.example.toeic.data.model.Question;
import com.example.toeic.data.network.Service;
import com.example.toeic.feature.exam.part_exam.question.PartQuestionExamPresentImpl;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class PartTwoExamPresentImpl extends PartQuestionExamPresentImpl implements PartTwoExamPresent {

    @Override
    public void getAllQuestionPartTwoByExamId(Integer examId) {
        callApi(Service.callQuestionService()
                .findPartTwoByExamId(examId)
                .compose(Service.getRxSingleSchedulers().applySchedulers())
                .subscribeWith(new DisposableSingleObserver<List<Question>>() {
                    @Override
                    public void onSuccess(List<Question> questions) {
                        getQuestionPartTwoSuccess(questions);
                        partQuestionExamView = (PartTwoExamView) view;
                        partQuestionExamView.notifyView();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    private void getQuestionPartTwoSuccess(List<Question> questions) {
        this.questions = questions;
        partQuestionExamView = (PartTwoExamView) view;
        partQuestionExamView.notifyView();
    }

}
