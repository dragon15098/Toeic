package com.example.toeic.feature.practice.part_four_exam;

import com.example.toeic.feature.practice.part_exam.group_question.PartGroupQuestionExamPresent;

public interface PartFourExamPresent extends PartGroupQuestionExamPresent {
    void getAllQuestionPartFourByExamId(Integer examId);

    void submitAnswer(Integer firstAnswer, Integer secondAnswer, Integer thirdAnswer);

    String getExplain();
}
