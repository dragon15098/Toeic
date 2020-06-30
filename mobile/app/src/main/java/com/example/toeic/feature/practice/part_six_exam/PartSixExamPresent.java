package com.example.toeic.feature.practice.part_six_exam;

import com.example.toeic.data.model.Question;
import com.example.toeic.feature.practice.part_exam.group_question.PartGroupQuestionExamPresent;

public interface PartSixExamPresent extends PartGroupQuestionExamPresent {
    void getAllQuestionPartSixByExamId(Integer examId);

    String getParagraph();

    Question getFourthQuestion();

    void submitAnswer(Integer firstAnswer, Integer secondAnswer, Integer thirdAnswer, Integer fourAnswer);
}
