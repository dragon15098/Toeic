package com.example.toeic.feature.exam.part_one_exam;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Answer;
import com.example.toeic.feature.exam.part_exam.question.PartQuestionExamActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.toeic.data.network.HttpHelper.SERVICE_RESOURCE;

public class PartOneExamActivity extends PartQuestionExamActivity implements PartOneExamView {

    @BindView(R2.id.imageView)
    ImageView imageView;

    @BindView(R2.id.radioButtonD)
    RadioButton radioButtonD;

    @BindView(R2.id.playMp3)
    Button play;

    @BindView(R2.id.radioGroup)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_one_exam);
        ButterKnife.bind(this);
        getData();
        setUpPresent();
        setUpSubmitAnswer();
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
        if (radioButtonD.isChecked()) {
            partQuestionExamPresent.submitAnswer(Answer.AnswerIndex.FOUR.getValue());
        }
    }

    private void setUpPresent() {
        partQuestionExamPresent = new PartOneExamPresentImpl();
        partQuestionExamPresent.attachView(this);
        ((PartOneExamPresent) partQuestionExamPresent).getAllQuestionPartOneByExamId(exam.getId());
    }


    @Override
    public void notifyView() {
        Glide.with(getBaseContext())
                .load(SERVICE_RESOURCE + partQuestionExamPresent.getUrlImage())
                .into(imageView);
        setUpMp3(partQuestionExamPresent.getMp3Link());
        hideAllAnswer();
    }

    private void hideAllAnswer() {
        radioButtonA.setText(R.string.A);
        radioButtonB.setText(R.string.B);
        radioButtonC.setText(R.string.C);
        radioButtonD.setText(R.string.D);
        resetTextColor();
        uncheckAllAnswer();
    }

    private void uncheckAllAnswer() {
        radioGroup.clearCheck();
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
        } else {
            radioButtonD.setTextColor(colorRes);
        }
    }

    private void resetTextColor() {
        int colorBlack = getResources().getColor(R.color.black);
        radioButtonA.setTextColor(colorBlack);
        radioButtonB.setTextColor(colorBlack);
        radioButtonC.setTextColor(colorBlack);
        radioButtonD.setTextColor(colorBlack);
    }

    @Override
    public void showCorrectAnswer() {
        radioButtonA.setText(partQuestionExamPresent.getExplainAnswerA());
        radioButtonB.setText(partQuestionExamPresent.getExplainAnswerB());
        radioButtonC.setText(partQuestionExamPresent.getExplainAnswerC());
        radioButtonD.setText(partQuestionExamPresent.getExplainAnswerD());
        changeCorrectAnswerColor();
    }
}
