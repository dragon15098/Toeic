package com.example.toeic.feature.exam.part_exam.question;

import com.example.base.BasePresenter;

public interface PartQuestionExamPresent extends BasePresenter {

    String getUrlImage();

    String getMp3Link();

    void submitAnswer(Integer index);

    String getExplainAnswerA();

    String getExplainAnswerB();

    String getExplainAnswerC();

    String getExplainAnswerD();

    int getCorrectAnswer();

    void nextQuestion();

    void backQuestion();
}
