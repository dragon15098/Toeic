package com.example.toeic.feature.exam.exam_list;

import com.example.base.BasePresenter;
import com.example.toeic.data.model.Exam;

import java.util.List;

public interface ExamPresent extends BasePresenter {
    void getAllExam();

    List<Exam> getExams();
}
