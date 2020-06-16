package com.example.toeic.feature.exam.part_one_exam;

import com.example.toeic.feature.exam.part_exam.question.PartQuestionExamPresent;

public interface PartOneExamPresent extends PartQuestionExamPresent {
    void getAllQuestionPartOneByExamId(Integer examId);

    String getExplainAnswerD();
}
