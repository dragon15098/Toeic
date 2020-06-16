package com.example.toeic.feature.exam.part_two_exam;

import com.example.toeic.feature.exam.part_exam.question.PartQuestionExamPresent;

public interface PartTwoExamPresent extends PartQuestionExamPresent {
    void getAllQuestionPartTwoByExamId(Integer id);
}
