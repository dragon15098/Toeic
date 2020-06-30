package com.example.toeic.feature.practice.part_seven_exam;

import com.example.toeic.data.model.Question;
import com.example.toeic.feature.practice.part_exam.group_question.PartGroupQuestionExamPresent;

public interface PartSevenExamPresent extends PartGroupQuestionExamPresent {
    void getAllQuestionPartSevenByExamId(Integer examId);

    String getParagraph();

    Question getFourthQuestion();

    Question getFifthQuestion();

    void submitAnswer(Integer firstAnswer, Integer secondAnswer, Integer thirdAnswer, Integer fourAnswer, Integer fifthAnswer);
}
