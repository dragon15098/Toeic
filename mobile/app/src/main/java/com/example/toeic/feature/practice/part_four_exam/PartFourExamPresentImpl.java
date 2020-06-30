package com.example.toeic.feature.practice.part_four_exam;

import com.example.toeic.data.model.GroupQuestion;
import com.example.toeic.data.network.Service;
import com.example.toeic.feature.practice.part_exam.group_question.PartGroupQuestionExamPresentImpl;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class PartFourExamPresentImpl extends PartGroupQuestionExamPresentImpl implements PartFourExamPresent {
    @Override
    public void getAllQuestionPartFourByExamId(Integer examId) {
        callApi(Service.callGroupQuestionService()
                .findPartFourByExamId(examId)
                .compose(Service.getRxSingleSchedulers().applySchedulers())
                .subscribeWith(new DisposableSingleObserver<List<GroupQuestion>>() {
                    @Override
                    public void onSuccess(List<GroupQuestion> groupQuestions) {
                        getQuestionPartFourSuccess(groupQuestions);
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

    private void getQuestionPartFourSuccess(List<GroupQuestion> groupQuestions) {
        this.groupQuestions = groupQuestions;
        partGroupQuestionExamView = (PartFourExamView) view;
        partGroupQuestionExamView.notifyView();
    }
}
