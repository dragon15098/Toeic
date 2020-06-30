package com.example.toeic.feature.practice.part_exam.group_question;

import com.example.base.BaseView;

public interface PartGroupQuestionExamView extends BaseView {
    void notifyView();

    void showCorrectAnswer();

    void showExplainAnswer();

    void endPart();
}
