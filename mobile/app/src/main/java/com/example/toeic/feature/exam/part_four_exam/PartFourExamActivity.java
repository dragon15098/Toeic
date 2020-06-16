package com.example.toeic.feature.exam.part_four_exam;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.toeic.R;
import com.example.toeic.data.model.Answer;
import com.example.toeic.feature.exam.part_exam.group_question.PartGroupQuestionExamActivity;
import com.example.toeic.feature.exam.part_three_exam.PartThreeExamPresent;
import com.example.toeic.feature.exam.part_three_exam.PartThreeExamPresentImpl;

import butterknife.ButterKnife;

public class PartFourExamActivity extends PartGroupQuestionExamActivity implements PartFourExamView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_four_exam);
        ButterKnife.bind(this);
        getData();
        setUpPresent();
    }

    private void setUpPresent() {
        partGroupQuestionExamPresent = new PartFourExamPresentImpl();
        partGroupQuestionExamPresent.attachView(this);
        ((PartFourExamPresent) partGroupQuestionExamPresent).getAllQuestionPartFourByExamId(exam.getId());
    }

    @Override
    public void setUpSubmitAnswer() {
        Integer firstAnswer = null;
        Integer secondAnswer = null;
        Integer thirdAnswer = null;
        if (radioButtonOneA.isChecked()) {
            firstAnswer = Answer.AnswerIndex.FRIST.getValue();
        } else if (radioButtonOneB.isChecked()) {
            firstAnswer = Answer.AnswerIndex.SECOND.getValue();
        } else if (radioButtonOneC.isChecked()) {
            firstAnswer = Answer.AnswerIndex.THIRD.getValue();
        } else if (radioButtonOneD.isChecked()) {
            firstAnswer = Answer.AnswerIndex.FOUR.getValue();
        }
        if (radioButtonTwoA.isChecked()) {
            secondAnswer = Answer.AnswerIndex.FRIST.getValue();
        } else if (radioButtonTwoB.isChecked()) {
            secondAnswer = Answer.AnswerIndex.SECOND.getValue();
        } else if (radioButtonTwoC.isChecked()) {
            secondAnswer = Answer.AnswerIndex.THIRD.getValue();
        } else if (radioButtonTwoD.isChecked()) {
            secondAnswer = Answer.AnswerIndex.FOUR.getValue();
        }
        if (radioButtonThreeA.isChecked()) {
            thirdAnswer = Answer.AnswerIndex.FRIST.getValue();
        } else if (radioButtonThreeB.isChecked()) {
            thirdAnswer = Answer.AnswerIndex.SECOND.getValue();
        } else if (radioButtonThreeC.isChecked()) {
            thirdAnswer = Answer.AnswerIndex.THIRD.getValue();
        } else if (radioButtonThreeD.isChecked()) {
            thirdAnswer = Answer.AnswerIndex.FOUR.getValue();
        }
        partGroupQuestionExamPresent.submitAnswer(firstAnswer, secondAnswer, thirdAnswer);
    }

    @Override
    public void notifyView() {
        clearButtonGroup();
        showFirstGroupQuestion();
        showSecondQuestion();
        showThirdQuestion();
        setUpMp3(partGroupQuestionExamPresent.getMp3Link());
    }

    @Override
    public void showCorrectAnswer() {
        showFirstCorrectAnswer();
        showSecondCorrectAnswer();
        showThirdCorrectAnswer();
    }

    @Override
    public void showExplainAnswer() {

    }
}
