package com.example.toeic.feature.practice.part_seven_exam;

import com.example.toeic.data.model.GroupQuestion;
import com.example.toeic.data.model.Question;
import com.example.toeic.data.network.Service;
import com.example.toeic.feature.practice.part_exam.group_question.PartGroupQuestionExamPresentImpl;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class PartSevenExamPresentImpl extends PartGroupQuestionExamPresentImpl implements PartSevenExamPresent {
    @Override
    public void getAllQuestionPartSevenByExamId(Integer examId) {
        callApi(Service.callGroupQuestionService()
                .findPartSevenByExamId(examId)
                .compose(Service.getRxSingleSchedulers().applySchedulers())
                .subscribeWith(new DisposableSingleObserver<List<GroupQuestion>>() {
                    @Override
                    public void onSuccess(List<GroupQuestion> groupQuestions) {
                        getQuestionPartSevenSuccess(groupQuestions);
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
    public Question getFifthQuestion() {
        List<Question> questions = getCurrentGroupQuestionIndex().getQuestions();
        if (exitsFifthQuestion(questions)) {
            return questions.get(GroupQuestion.GroupQuestionIndex.FIFTH.getValue());
        }
        return null;
    }

    @Override
    public void submitAnswer(Integer firstAnswer, Integer secondAnswer, Integer thirdAnswer, Integer fourthAnswer, Integer fifthAnswer) {
        setSubmitted();
        checkFirstQuestion(firstAnswer);
        checkSecondQuestion(secondAnswer);
        checkThirdQuestion(thirdAnswer);
        checkFourthQuestion(fourthAnswer);
        checkFifthQuestion(fifthAnswer);
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

    private void checkFifthQuestion(Integer fifthAnswer) {
        List<Question> questions = getCurrentGroupQuestionIndex().getQuestions();
        if (exitsFifthQuestion(questions)) {
            GroupQuestion currentGroupQuestion = getCurrentGroupQuestionIndex();
            Question question = currentGroupQuestion.getQuestions().get(GroupQuestion.GroupQuestionIndex.FIFTH.getValue());
            checkAnswer(fifthAnswer, question);
        }
    }

    private boolean exitsFifthQuestion(List<Question> questions) {
        return questions.size() >= 5;
    }

    private void getQuestionPartSevenSuccess(List<GroupQuestion> groupQuestions) {
        this.groupQuestions = groupQuestions;
        partGroupQuestionExamView = (PartSevenExamView) view;
        partGroupQuestionExamView.notifyView();
    }
}
