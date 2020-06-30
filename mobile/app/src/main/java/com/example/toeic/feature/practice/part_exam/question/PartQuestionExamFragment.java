package com.example.toeic.feature.practice.part_exam.question;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.base.BaseFragment;
import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Answer;
import com.example.toeic.data.model.Exam;
import com.example.toeic.data.model.Part;
import com.example.toeic.data.model.Result;
import com.example.toeic.feature.exam.test.TestActivity;
import com.example.toeic.feature.practice.part_main.PartMainActivity;
import com.example.toeic.feature.result.ResultActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.toeic.ultis.Constraints.EXAM;
import static com.example.toeic.ultis.Constraints.PART;
import static com.example.toeic.ultis.Constraints.RESULT;

public abstract class PartQuestionExamFragment extends BaseFragment implements PartQuestionExamView {
    private static final int RESQUEST_CODE = 1111;
    public Exam exam;

    @BindView(R2.id.radioGroup)
    public RadioGroup radioGroup;

    @BindView(R2.id.radioButtonA)
    public RadioButton radioButtonA;

    @BindView(R2.id.radioButtonB)
    public RadioButton radioButtonB;

    @BindView(R2.id.radioButtonC)
    public RadioButton radioButtonC;

    @BindView(R2.id.backQuestion)
    public Button backQuestion;

    public PartQuestionExamPresent partQuestionExamPresent;

    @OnClick(R2.id.backQuestion)
    public void setUpBackQuestion() {
        partQuestionExamPresent.backQuestion();
    }

    @BindView(R2.id.submitButton)
    public Button submitButton;

    @OnClick(R2.id.submitButton)
    public abstract void setUpSubmitAnswer();


    public abstract void hideButtonWhenTesting();

    public void getData() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Intent intent = activity.getIntent();
            exam = (Exam) intent.getSerializableExtra(EXAM);
        }
    }

    @Override
    public abstract void notifyView();

    protected void hideBackButton() {
        backQuestion.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);
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

    protected boolean isTesting() {
        FragmentActivity activity = getActivity();
        return activity instanceof TestActivity;
    }

    private void resetTextColor() {
        int colorBlack = getResources().getColor(R.color.black);
        radioButtonA.setTextColor(colorBlack);
        radioButtonB.setTextColor(colorBlack);
        radioButtonC.setTextColor(colorBlack);
    }

    public abstract void showCorrectAnswer();

    @Override
    public void endPart() {
        FragmentActivity activity = getActivity();
        if (activity instanceof PartMainActivity) {
            showResult();
        } else if (activity instanceof TestActivity) {
            nextPart();
        }
    }

    public Integer getCurrentAnswer() {
        if (radioButtonA.isChecked()) {
            return Answer.AnswerIndex.FRIST.getValue();
        }
        if (radioButtonB.isChecked()) {
            return Answer.AnswerIndex.SECOND.getValue();
        }
        if (radioButtonC.isChecked()) {
            return Answer.AnswerIndex.THIRD.getValue();
        }
        return -1;
    }

    private void showResult() {
        PartMainActivity activity = (PartMainActivity) getActivity();
        if (activity != null) {
            Part.PartIndex partIndex = activity.getPartIndex();
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(PART, partIndex.getValue());
            switch (partIndex.getValue()) {
                case 1:
                    bundle.putSerializable(RESULT, Result.builder().partOneScore(partQuestionExamPresent.getQuestionPoint()).build());
                    break;
                case 2:
                    bundle.putSerializable(RESULT, Result.builder().partTwoScore(partQuestionExamPresent.getQuestionPoint()).build());
                    break;
                case 5:
                    bundle.putSerializable(RESULT, Result.builder().partFiveScore(partQuestionExamPresent.getQuestionPoint()).build());
                    break;
            }
            intent.putExtras(bundle);
            startActivityForResult(intent, RESQUEST_CODE);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESQUEST_CODE == requestCode) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.onBackPressed();
            }
        }
    }

    private void nextPart() {
        TestActivity activity = (TestActivity) getActivity();
        if (activity != null) {
            activity.changeFragment();
        }
    }

}
