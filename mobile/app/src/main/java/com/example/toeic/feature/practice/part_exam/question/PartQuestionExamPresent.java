package com.example.toeic.feature.practice.part_exam.question;

import com.example.base.BasePresenter;
import com.example.toeic.data.model.QuestionPoint;

public interface PartQuestionExamPresent extends BasePresenter {

    String getUrlImage();

    String getMp3Link();

    void submitAnswer(Integer index);

    String getExplainAnswerA();

    String getExplainAnswerB();

    String getExplainAnswerC();

    String getExplainAnswerD();

    int getCorrectAnswer();

    QuestionPoint getQuestionPoint();

    void nextQuestion();

    void backQuestion();


}
