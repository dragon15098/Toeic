package com.example.toeic.feature.exam.part_exam.group_question;

import com.example.base.BasePresenter;
import com.example.toeic.data.model.GroupQuestion;
import com.example.toeic.data.model.Question;

public interface PartGroupQuestionExamPresent extends BasePresenter {
    Question getFirstQuestion();

    Question getSecondQuestion();

    Question getThirdQuestion();

    GroupQuestion getCurrentGroupQuestion();

    String getMp3Link();

    void submitAnswer(Integer firstAnswer, Integer secondAnswer, Integer thirdAnswer);

    void nextQuestion();

    void backQuestion();

}
