package com.example.toeic.feature.practice.part_five_exam;

import com.example.toeic.feature.practice.part_exam.question.PartQuestionExamPresent;

public interface PartFiveExamPresent extends PartQuestionExamPresent {
    void getAllQuestionPartFiveByExamId(Integer examId);

    String getQuestion();
}
