package com.example.toeic.feature.exam.test;

import com.example.base.BasePresenterImpl;
import com.example.toeic.data.model.Exam;
import com.example.toeic.data.model.GroupQuestionPoint;
import com.example.toeic.data.model.QuestionPoint;
import com.example.toeic.data.model.Result;
import com.example.toeic.data.network.Service;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class TestPresentImpl extends BasePresenterImpl implements TestPresent {

    Result result = Result.builder()
            .partOneScore(QuestionPoint.builder().build())
            .partTwoScore(QuestionPoint.builder().build())
            .partThreeScore(GroupQuestionPoint.builder().build())
            .partFourScore(GroupQuestionPoint.builder().build())
            .partFiveScore(QuestionPoint.builder().build())
            .partSixScore(GroupQuestionPoint.builder().build())
            .partSevenScore(GroupQuestionPoint.builder().build())
            .build();
    int currentPart = 0;

    List<Integer> correctAnswers = new ArrayList<>();

    Exam exam;

    @Override
    public Result getResult() {
        return result;
    }

    @Override
    public void updatePoint(QuestionPoint questionPoint) {
        switch (currentPart) {
            case 1:
                result.setPartOneScore(questionPoint);
                break;
            case 2:
                result.setPartTwoScore(questionPoint);
                break;
            case 5:
                result.setPartFiveScore(questionPoint);
                break;
        }
    }

    @Override
    public void updatePoint(GroupQuestionPoint groupQuestionPoint) {
        switch (currentPart) {
            case 3:
                result.setPartThreeScore(groupQuestionPoint);
                break;
            case 4:
                result.setPartFourScore(groupQuestionPoint);
                break;
            case 6:
                result.setPartSixScore(groupQuestionPoint);
                break;
            case 7:
                result.setPartSevenScore(groupQuestionPoint);
                break;
        }
    }

    @Override
    public int getCurrentPart() {
        return currentPart;
    }

    @Override
    public void updateCurrentPart() {
        currentPart++;
    }

    @Override
    public void setExam(Exam exam) {
        this.exam = exam;
    }


}
