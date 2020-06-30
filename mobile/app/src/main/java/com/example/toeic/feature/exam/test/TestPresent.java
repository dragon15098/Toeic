package com.example.toeic.feature.exam.test;

import com.example.base.BasePresenter;
import com.example.toeic.data.model.Exam;
import com.example.toeic.data.model.GroupQuestionPoint;
import com.example.toeic.data.model.Question;
import com.example.toeic.data.model.QuestionPoint;
import com.example.toeic.data.model.Result;

public interface TestPresent extends BasePresenter {
    Result getResult();

    void updatePoint(QuestionPoint questionPoint);

    void updatePoint(GroupQuestionPoint groupQuestionPoint);

    int getCurrentPart();

    void updateCurrentPart();

    void setExam(Exam exam);

}
