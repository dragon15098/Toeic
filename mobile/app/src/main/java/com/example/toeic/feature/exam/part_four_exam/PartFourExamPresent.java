package com.example.toeic.feature.exam.part_four_exam;

import com.example.toeic.feature.exam.part_exam.group_question.PartGroupQuestionExamPresent;

public interface PartFourExamPresent extends PartGroupQuestionExamPresent {
    void getAllQuestionPartFourByExamId(Integer examId);

}
