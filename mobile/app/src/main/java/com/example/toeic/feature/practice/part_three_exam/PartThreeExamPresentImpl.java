package com.example.toeic.feature.practice.part_three_exam;

import com.example.toeic.data.model.GroupQuestion;
import com.example.toeic.data.network.Service;
import com.example.toeic.feature.practice.part_exam.group_question.PartGroupQuestionExamPresentImpl;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class PartThreeExamPresentImpl extends PartGroupQuestionExamPresentImpl implements PartThreeExamPresent {

    @Override
    public void getAllQuestionPartThreeByExamId(Integer examId) {
        callApi(Service.callGroupQuestionService()
                .findPartThreeByExamId(examId)
                .compose(Service.getRxSingleSchedulers().applySchedulers())
                .subscribeWith(new DisposableSingleObserver<List<GroupQuestion>>() {
                    @Override
                    public void onSuccess(List<GroupQuestion> groupQuestions) {
                        getQuestionPartThreeSuccess(groupQuestions);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void submitAnswer(Integer firstAnswer, Integer secondAnswer, Integer thirdAnswer) {
        setSubmitted();
        checkFirstQuestion(firstAnswer);
        checkSecondQuestion(secondAnswer);
        checkThirdQuestion(thirdAnswer);
        partGroupQuestionExamView.showCorrectAnswer();
        partGroupQuestionExamView.showExplainAnswer();
    }

    @Override
    public String getExplain() {
        return getCurrentGroupQuestionIndex().getExplainAnswer();
    }

    private void getQuestionPartThreeSuccess(List<GroupQuestion> groupQuestions) {
        this.groupQuestions = groupQuestions;
        partGroupQuestionExamView = (PartThreeExamView) view;
        partGroupQuestionExamView.notifyView();
    }
}
