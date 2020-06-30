package com.example.toeic.feature.practice.part_two_exam;

import com.example.toeic.feature.practice.part_exam.question.PartQuestionExamPresent;

public interface PartTwoExamPresent extends PartQuestionExamPresent {
    void getAllQuestionPartTwoByExamId(Integer id);
}
