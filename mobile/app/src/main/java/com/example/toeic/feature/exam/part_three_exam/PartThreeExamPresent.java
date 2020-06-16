package com.example.toeic.feature.exam.part_three_exam;

import com.example.toeic.feature.exam.part_exam.group_question.PartGroupQuestionExamPresent;
import com.example.toeic.feature.exam.part_exam.question.PartQuestionExamPresent;

public interface PartThreeExamPresent extends PartGroupQuestionExamPresent {
    void getAllQuestionPartThreeByExamId(Integer examId);

    String getExplain();
}
