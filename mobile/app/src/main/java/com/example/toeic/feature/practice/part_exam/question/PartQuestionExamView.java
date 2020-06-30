package com.example.toeic.feature.practice.part_exam.question;

import androidx.fragment.app.Fragment;

import com.example.base.BaseView;

public interface PartQuestionExamView extends BaseView {
    void notifyView();

    void endPart();
}
