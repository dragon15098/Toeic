package com.example.toeic.feature.practice.part_exam.group_question;

import com.example.base.BasePresenter;
import com.example.toeic.data.model.GroupQuestion;
import com.example.toeic.data.model.GroupQuestionPoint;
import com.example.toeic.data.model.Question;

public interface PartGroupQuestionExamPresent extends BasePresenter {
    Question getFirstQuestion();

    Question getSecondQuestion();

    Question getThirdQuestion();

    GroupQuestion getCurrentGroupQuestionIndex();

    String getMp3Link();

    void nextQuestion();

    void backQuestion();

    String getUrlImage();

    GroupQuestionPoint getGroupQuestionPoint();

}
