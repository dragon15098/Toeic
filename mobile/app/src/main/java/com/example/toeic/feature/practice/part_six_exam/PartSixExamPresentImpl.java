package com.example.toeic.feature.practice.part_six_exam;

import com.example.toeic.data.model.GroupQuestion;
import com.example.toeic.data.model.Question;
import com.example.toeic.data.network.Service;
import com.example.toeic.feature.practice.part_exam.group_question.PartGroupQuestionExamPresentImpl;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class PartSixExamPresentImpl extends PartGroupQuestionExamPresentImpl implements PartSixExamPresent {
    @Override
    public void getAllQuestionPartSixByExamId(Integer examId) {
        callApi(Service.callGroupQuestionService()
                .findPartSixByExamId(examId)
                .compose(Service.getRxSingleSchedulers().applySchedulers())
                .subscribeWith(new DisposableSingleObserver<List<GroupQuestion>>() {
                    @Override
                    public void onSuccess(List<GroupQuestion> groupQuestions) {
                        getQuestionPartSixSuccess(groupQuestions);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public String getParagraph() {
        return getCurrentGroupQuestionIndex().getResourceParagraph();
    }

    @Override
    public Question getFourthQuestion() {
        List<Question> questions = getCurrentGroupQuestionIndex().getQuestions();
        if (exitsFourthQuestion(questions)) {
            return questions.get(GroupQuestion.GroupQuestionIndex.FOURTH.getValue());
        }
        return null;
    }

    @Override
    public void submitAnswer(Integer firstAnswer, Integer secondAnswer, Integer thirdAnswer, Integer fourthAnswer) {
        setSubmitted();
        checkFirstQuestion(firstAnswer);
        checkSecondQuestion(secondAnswer);
        checkThirdQuestion(thirdAnswer);
        checkFourthQuestion(fourthAnswer);
        partGroupQuestionExamView.showCorrectAnswer();
        partGroupQuestionExamView.showExplainAnswer();
    }

    private void checkFourthQuestion(Integer fourthAnswer) {
        List<Question> questions = getCurrentGroupQuestionIndex().getQuestions();
        if (exitsFourthQuestion(questions)) {
            GroupQuestion currentGroupQuestion = getCurrentGroupQuestionIndex();
            Question question = currentGroupQuestion.getQuestions().get(GroupQuestion.GroupQuestionIndex.FOURTH.getValue());
            checkAnswer(fourthAnswer, question);
        }
    }

    private boolean exitsFourthQuestion(List<Question> questions) {
        return questions.size() >= 4;
    }

    private void getQuestionPartSixSuccess(List<GroupQuestion> groupQuestions) {
        this.groupQuestions = groupQuestions;
        partGroupQuestionExamView = (PartSixExamView) view;
        partGroupQuestionExamView.notifyView();
    }
}
