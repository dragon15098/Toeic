package com.example.toeic.feature.exam.part_four_exam;

import com.example.toeic.data.model.GroupQuestion;
import com.example.toeic.data.network.Service;
import com.example.toeic.feature.exam.part_exam.group_question.PartGroupQuestionExamPresentImpl;
import com.example.toeic.feature.exam.part_three_exam.PartThreeExamView;

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

    private void getQuestionPartFourSuccess(List<GroupQuestion> groupQuestions) {
        this.groupQuestions = groupQuestions;
        partGroupQuestionExamView = (PartFourExamView) view;
        partGroupQuestionExamView.notifyView();

    }
}
