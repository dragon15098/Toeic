package com.example.toeic.feature.practice.part_three_exam;

import com.example.toeic.feature.practice.part_exam.group_question.PartGroupQuestionExamPresent;

public interface PartThreeExamPresent extends PartGroupQuestionExamPresent {
    void getAllQuestionPartThreeByExamId(Integer examId);

    String getExplain();

    void submitAnswer(Integer firstAnswer, Integer secondAnswer, Integer thirdAnswer);
}
