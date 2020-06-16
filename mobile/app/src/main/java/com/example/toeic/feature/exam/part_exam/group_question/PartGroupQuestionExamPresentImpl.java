package com.example.toeic.feature.exam.part_exam.group_question;

import com.example.base.BasePresenterImpl;
import com.example.toeic.data.model.GroupQuestion;
import com.example.toeic.data.model.Question;

import java.util.List;

public class PartGroupQuestionExamPresentImpl extends BasePresenterImpl implements PartGroupQuestionExamPresent {
    public PartGroupQuestionExamView partGroupQuestionExamView;
    public List<GroupQuestion> groupQuestions;

    private int currentQuestion = 0;

    private Boolean[][] result = new Boolean[10][3];

    @Override
    public Question getFirstQuestion() {
        return getCurrentGroupQuestion().getQuestions().get(GroupQuestion.GroupQuestionIndex.FIRST.getValue());
    }

    @Override
    public Question getSecondQuestion() {
        return getCurrentGroupQuestion().getQuestions().get(GroupQuestion.GroupQuestionIndex.SECOND.getValue());
    }

    @Override
    public Question getThirdQuestion() {
        return getCurrentGroupQuestion().getQuestions().get(GroupQuestion.GroupQuestionIndex.THIRD.getValue());
    }

    @Override
    public String getMp3Link() {
        return getCurrentGroupQuestion().getLinkMp3Resource();
    }

    @Override
    public void submitAnswer(Integer firstAnswer, Integer secondAnswer, Integer thirdAnswer) {
        checkFirstQuestion(firstAnswer);
        checkSecondQuestion(secondAnswer);
        checkThirdQuestion(thirdAnswer);
        partGroupQuestionExamView.showCorrectAnswer();
        partGroupQuestionExamView.showExplainAnswer();
    }

    @Override
    public void nextQuestion() {
        currentQuestion++;
        partGroupQuestionExamView.notifyView();
    }

    @Override
    public void backQuestion() {
        if (currentQuestion > 0) {
            currentQuestion--;
            partGroupQuestionExamView.notifyView();
        }
    }

    private void checkFirstQuestion(Integer answer) {
        GroupQuestion currentGroupQuestion = getCurrentGroupQuestion();
        Question question = currentGroupQuestion.getQuestions().get(GroupQuestion.GroupQuestionIndex.FIRST.getValue());
        if (answer != null && answer.equals(question.getCorrectAnswer())) {
            Boolean[] booleans = result[currentQuestion];
            if (booleans == null) {
                booleans = new Boolean[3];
                result[currentQuestion] = booleans;
            }
            booleans[0] = true;
        }
    }

    private void checkSecondQuestion(Integer answer) {
        GroupQuestion currentGroupQuestion = getCurrentGroupQuestion();
        Question question = currentGroupQuestion.getQuestions().get(GroupQuestion.GroupQuestionIndex.SECOND.getValue());
        if (answer != null && answer.equals(question.getCorrectAnswer())) {
            Boolean[] booleans = result[currentQuestion];
            if (booleans == null) {
                booleans = new Boolean[3];
                result[currentQuestion] = booleans;
            }
            booleans[1] = true;
        }
    }

    private void checkThirdQuestion(Integer answer) {
        GroupQuestion currentGroupQuestion = getCurrentGroupQuestion();
        Question question = currentGroupQuestion.getQuestions().get(GroupQuestion.GroupQuestionIndex.THIRD.getValue());
        if (answer != null && answer.equals(question.getCorrectAnswer())) {
            Boolean[] booleans = result[currentQuestion];
            if (booleans == null) {
                booleans = new Boolean[3];
                result[currentQuestion] = booleans;
            }
            booleans[2] = true;
        }
    }

    @Override
    public GroupQuestion getCurrentGroupQuestion() {
        return groupQuestions.get(currentQuestion);
    }

}
