package com.example.toeic.feature.practice.part_one_exam;

import com.example.toeic.feature.practice.part_exam.question.PartQuestionExamPresent;

public interface PartOneExamPresent extends PartQuestionExamPresent {
    void getAllQuestionPartOneByExamId(Integer examId);

    String getExplainAnswerD();
}
