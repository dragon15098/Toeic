package com.example.toeic.feature.exam.part_two_exam;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Answer;
import com.example.toeic.feature.exam.part_exam.question.PartQuestionExamActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartTwoExamActivity extends PartQuestionExamActivity implements PartTwoExamView {

    @BindView(R2.id.radioButtonA)
    RadioButton radioButtonA;

    @BindView(R2.id.radioButtonB)
    RadioButton radioButtonB;

    @BindView(R2.id.radioButtonC)
    RadioButton radioButtonC;

    @BindView(R2.id.mp3SeekBar)
    SeekBar mp3SeekBar;

    @BindView(R2.id.playMp3)
    Button play;

    @BindView(R2.id.radioGroup)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_two_exam);
        ButterKnife.bind(this);
        getData();
        setUpPresent();
    }

    private void setUpPresent() {
        partQuestionExamPresent = new PartTwoExamPresentImpl();
        partQuestionExamPresent.attachView(this);
        ((PartTwoExamPresent) partQuestionExamPresent).getAllQuestionPartTwoByExamId(exam.getId());
    }

    @Override
    public void notifyView() {
        setUpMp3(partQuestionExamPresent.getMp3Link());
        hideAllAnswer();
    }

    @Override
    public void showCorrectAnswer() {
        radioButtonA.setText(partQuestionExamPresent.getExplainAnswerA());
        radioButtonB.setText(partQuestionExamPresent.getExplainAnswerB());
        radioButtonC.setText(partQuestionExamPresent.getExplainAnswerC());
        changeCorrectAnswerColor();
    }

    private void changeCorrectAnswerColor() {
        int colorRes = getResources().getColor(R.color.correctAnswer);
        int correctAnswer = partQuestionExamPresent.getCorrectAnswer();
        if (correctAnswer == Answer.AnswerIndex.FRIST.getValue()) {
            radioButtonA.setTextColor(colorRes);
        } else if (correctAnswer == Answer.AnswerIndex.SECOND.getValue()) {
            radioButtonB.setTextColor(colorRes);
        } else if (correctAnswer == Answer.AnswerIndex.THIRD.getValue()) {
            radioButtonC.setTextColor(colorRes);
        }
    }

    @OnClick(R2.id.submitButton)
    public void setUpSubmitAnswer() {
        if (radioButtonA.isChecked()) {
            partQuestionExamPresent.submitAnswer(Answer.AnswerIndex.FRIST.getValue());
        }
        if (radioButtonB.isChecked()) {
            partQuestionExamPresent.submitAnswer(Answer.AnswerIndex.SECOND.getValue());
        }
        if (radioButtonC.isChecked()) {
            partQuestionExamPresent.submitAnswer(Answer.AnswerIndex.THIRD.getValue());
        }
    }

    private void hideAllAnswer() {
        radioButtonA.setText(R.string.A);
        radioButtonB.setText(R.string.B);
        radioButtonC.setText(R.string.C);
        resetTextColor();
        uncheckAllAnswer();
    }

    private void resetTextColor() {
        int colorBlack = getResources().getColor(R.color.black);
        radioButtonA.setTextColor(colorBlack);
        radioButtonB.setTextColor(colorBlack);
        radioButtonC.setTextColor(colorBlack);
    }

    private void uncheckAllAnswer() {
        radioGroup.clearCheck();
    }
}
