package com.example.toeic.feature.practice.part_five_exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Answer;
import com.example.toeic.feature.exam.test.TestActivity;
import com.example.toeic.feature.practice.part_exam.question.PartQuestionExamFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartFiveExamFragment extends PartQuestionExamFragment implements PartFiveExamView {

    @BindView(R2.id.question)
    TextView question;

    @BindView(R2.id.radioButtonD)
    RadioButton radioButtonD;

    @OnClick(R2.id.nextQuestion)
    public void setUpNextQuestion() {
        if (isTesting()) {
            TestActivity activity = (TestActivity) getActivity();
            if (activity != null) {
                partQuestionExamPresent.submitAnswer(getCurrentAnswer());
                activity.getPresent().updatePoint(partQuestionExamPresent.getQuestionPoint());
            }
        }
        partQuestionExamPresent.nextQuestion();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_part_five_exam, container, false);
        ButterKnife.bind(this, view);
        getData();
        setUpPresent();
        hideButtonWhenTesting();
        return view;
    }

    private void setUpPresent() {
        partQuestionExamPresent = new PartFiveExamPresentImpl();
        partQuestionExamPresent.attachView(this);
        ((PartFiveExamPresent) partQuestionExamPresent).getAllQuestionPartFiveByExamId(exam.getId());
    }

    @Override
    public void setUpSubmitAnswer() {
        Integer currentAnswer = getCurrentAnswer();
        partQuestionExamPresent.submitAnswer(currentAnswer);
        showCorrectAnswer();
    }

    @Override
    public Integer getCurrentAnswer() {
        if (radioButtonD.isChecked()) {
            return Answer.AnswerIndex.FOUR.getValue();
        }
        return super.getCurrentAnswer();
    }

    @Override
    public void hideButtonWhenTesting() {
        if (isTesting()) {
            hideBackButton();
        }
    }

    @Override
    public void notifyView() {
        clearButtonGroup();
        showQuestion();
        showAnswer();
        resetTextColor();
    }

    private void clearButtonGroup() {
        radioGroup.clearCheck();
    }

    private void showQuestion() {
        question.setText(((PartFiveExamPresent) partQuestionExamPresent).getQuestion());

    }

    private void showAnswer() {
        PartFiveExamPresent partQuestionExamPresent = (PartFiveExamPresent) this.partQuestionExamPresent;
        radioButtonA.setText(partQuestionExamPresent.getExplainAnswerA());
        radioButtonB.setText(partQuestionExamPresent.getExplainAnswerB());
        radioButtonC.setText(partQuestionExamPresent.getExplainAnswerC());
        radioButtonD.setText(partQuestionExamPresent.getExplainAnswerD());
    }

    @Override
    public void showCorrectAnswer() {
        int correctAnswer = partQuestionExamPresent.getCorrectAnswer();
        if (Answer.AnswerIndex.FRIST.getValue() == correctAnswer) {
            radioButtonA.setTextColor(getResources().getColor(R.color.correctAnswer));
        } else if (Answer.AnswerIndex.SECOND.getValue() == correctAnswer) {
            radioButtonB.setTextColor(getResources().getColor(R.color.correctAnswer));
        } else if (Answer.AnswerIndex.THIRD.getValue() == correctAnswer) {
            radioButtonC.setTextColor(getResources().getColor(R.color.correctAnswer));
        } else if (Answer.AnswerIndex.FOUR.getValue() == correctAnswer) {
            radioButtonD.setTextColor(getResources().getColor(R.color.correctAnswer));
        }
    }

    private void resetTextColor() {
        int colorBlack = getResources().getColor(R.color.black);
        radioButtonA.setTextColor(colorBlack);
        radioButtonB.setTextColor(colorBlack);
        radioButtonC.setTextColor(colorBlack);
        radioButtonD.setTextColor(colorBlack);
    }

}
