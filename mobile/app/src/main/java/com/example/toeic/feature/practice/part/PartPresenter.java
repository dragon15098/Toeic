package com.example.toeic.feature.practice.part;

import com.example.base.BasePresenter;
import com.example.toeic.data.model.Exam;

import java.util.List;

interface PartPresenter extends BasePresenter {
    void getAllExam();

    List<Exam> getData();
}
