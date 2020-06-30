package com.example.toeic.feature.practice.part_exam.group_question;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.base.BaseFragment;
import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Answer;
import com.example.toeic.data.model.Exam;
import com.example.toeic.data.model.Part;
import com.example.toeic.data.model.Question;
import com.example.toeic.data.model.Result;
import com.example.toeic.feature.exam.test.TestActivity;
import com.example.toeic.feature.practice.part_main.PartMainActivity;
import com.example.toeic.feature.result.ResultActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.toeic.ultis.Constraints.EXAM;
import static com.example.toeic.ultis.Constraints.PART;
import static com.example.toeic.ultis.Constraints.RESULT;

public abstract class PartGroupQuestionExamFragment extends BaseFragment implements PartGroupQuestionExamView {
    public Exam exam;
    private static final int RESQUEST_CODE = 1111;
    // First question
    @BindView(R2.id.textViewQuestionOne)
    public TextView textViewQuestionOne;

    @BindView(R2.id.radioGroupOne)
    public RadioGroup radioGroupOne;

    @BindView(R2.id.radioButtonOneA)
    public RadioButton radioButtonOneA;

    @BindView(R2.id.radioButtonOneB)
    public RadioButton radioButtonOneB;

    @BindView(R2.id.radioButtonOneC)
    public RadioButton radioButtonOneC;

    @BindView(R2.id.radioButtonOneD)
    public RadioButton radioButtonOneD;

    // Second question
    @BindView(R2.id.textViewQuestionTwo)
    public TextView textViewQuestionTwo;

    @BindView(R2.id.radioGroupTwo)
    public RadioGroup radioGroupTwo;

    @BindView(R2.id.radioButtonTwoA)
    public RadioButton radioButtonTwoA;

    @BindView(R2.id.radioButtonTwoB)
    public RadioButton radioButtonTwoB;

    @BindView(R2.id.radioButtonTwoC)
    public RadioButton radioButtonTwoC;

    @BindView(R2.id.radioButtonTwoD)
    public RadioButton radioButtonTwoD;

    // third question

    @BindView(R2.id.slightThree)
    public View slightThree;

    @BindView(R2.id.textViewQuestionThree)
    public TextView textViewQuestionThree;

    @BindView(R2.id.radioGroupThree)
    public RadioGroup radioGroupThree;

    @BindView(R2.id.radioButtonThreeA)
    public RadioButton radioButtonThreeA;

    @BindView(R2.id.radioButtonThreeB)
    public RadioButton radioButtonThreeB;

    @BindView(R2.id.radioButtonThreeC)
    public RadioButton radioButtonThreeC;

    @BindView(R2.id.radioButtonThreeD)
    public RadioButton radioButtonThreeD;

    public PartGroupQuestionExamPresent partGroupQuestionExamPresent;

    @OnClick(R2.id.submitButton)
    public abstract void setUpSubmitAnswer();

    @BindView(R2.id.backQuestion)
    public Button backQuestion;

    @BindView(R2.id.submitButton)
    public Button submitButton;

    @OnClick(R2.id.backQuestion)
    public void setUpBackQuestion() {
        partGroupQuestionExamPresent.backQuestion();
    }

    public void getData() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Intent intent = activity.getIntent();
            exam = (Exam) intent.getSerializableExtra(EXAM);
        }
    }

    public abstract void hideButtonWhenTesting();

    public void clearButtonGroup() {
        clearFirstButtonGroup();
        clearSecondButtonGroup();
        clearThirdButtonGroup();
    }

    protected boolean isTesting() {
        FragmentActivity activity = getActivity();
        return activity instanceof TestActivity;
    }

    protected void hideButton() {
        backQuestion.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
    }

    private void clearFirstButtonGroup() {
        radioGroupOne.clearCheck();
        radioButtonOneA.setTextColor(getResources().getColor(R.color.black));
        radioButtonOneB.setTextColor(getResources().getColor(R.color.black));
        radioButtonOneC.setTextColor(getResources().getColor(R.color.black));
        radioButtonOneD.setTextColor(getResources().getColor(R.color.black));
    }

    private void clearSecondButtonGroup() {
        radioGroupTwo.clearCheck();
        radioButtonTwoA.setTextColor(getResources().getColor(R.color.black));
        radioButtonTwoB.setTextColor(getResources().getColor(R.color.black));
        radioButtonTwoC.setTextColor(getResources().getColor(R.color.black));
        radioButtonTwoD.setTextColor(getResources().getColor(R.color.black));
    }

    private void clearThirdButtonGroup() {
        radioGroupThree.clearCheck();
        radioButtonThreeA.setTextColor(getResources().getColor(R.color.black));
        radioButtonThreeB.setTextColor(getResources().getColor(R.color.black));
        radioButtonThreeC.setTextColor(getResources().getColor(R.color.black));
        radioButtonThreeD.setTextColor(getResources().getColor(R.color.black));
    }

    public void showFirstQuestion() {
        Question questionOne = partGroupQuestionExamPresent.getFirstQuestion();
        setUpFirstQuestion(questionOne);
        setUpFirstGroupAnswer(questionOne);
    }

    private void setUpFirstQuestion(Question firstQuestion) {
        textViewQuestionOne.setText(firstQuestion.getQuestion());
    }

    private void setUpFirstGroupAnswer(Question firstQuestion) {
        List<Answer> answers = firstQuestion.getAnswers();
        radioButtonOneA.setText(answers.get(Answer.AnswerIndex.FRIST.getValue()).getAnswer());
        radioButtonOneB.setText(answers.get(Answer.AnswerIndex.SECOND.getValue()).getAnswer());
        radioButtonOneC.setText(answers.get(Answer.AnswerIndex.THIRD.getValue()).getAnswer());
        radioButtonOneD.setText(answers.get(Answer.AnswerIndex.FOUR.getValue()).getAnswer());
    }

    public void showSecondQuestion() {
        Question secondQuestion = partGroupQuestionExamPresent.getSecondQuestion();
        setUpSecondQuestion(secondQuestion);
        setUpSecondGroupAnswer(secondQuestion);
    }

    private void setUpSecondQuestion(Question secondQuestion) {
        textViewQuestionTwo.setText(secondQuestion.getQuestion());
    }

    private void setUpSecondGroupAnswer(Question secondQuestion) {
        List<Answer> answers = secondQuestion.getAnswers();
        radioButtonTwoA.setText(answers.get(Answer.AnswerIndex.FRIST.getValue()).getAnswer());
        radioButtonTwoB.setText(answers.get(Answer.AnswerIndex.SECOND.getValue()).getAnswer());
        radioButtonTwoC.setText(answers.get(Answer.AnswerIndex.THIRD.getValue()).getAnswer());
        radioButtonTwoD.setText(answers.get(Answer.AnswerIndex.FOUR.getValue()).getAnswer());
    }

    public void showThirdQuestion() {
        Question thirdQuestion = partGroupQuestionExamPresent.getThirdQuestion();
        if (thirdQuestion != null) {
            slightThree.setVisibility(View.VISIBLE);
            textViewQuestionThree.setVisibility(View.VISIBLE);
            radioGroupThree.setVisibility(View.VISIBLE);
            setUpThirdQuestion(thirdQuestion);
            setUpThirdGroupAnswer(thirdQuestion);
        } else {
            disableThirdQuestion();
        }
    }

    private void setUpThirdQuestion(Question thirdQuestion) {
        textViewQuestionThree.setText(thirdQuestion.getQuestion());
    }

    private void setUpThirdGroupAnswer(Question thirdQuestion) {
        List<Answer> answers = thirdQuestion.getAnswers();
        radioButtonThreeA.setText(answers.get(Answer.AnswerIndex.FRIST.getValue()).getAnswer());
        radioButtonThreeB.setText(answers.get(Answer.AnswerIndex.SECOND.getValue()).getAnswer());
        radioButtonThreeC.setText(answers.get(Answer.AnswerIndex.THIRD.getValue()).getAnswer());
        radioButtonThreeD.setText(answers.get(Answer.AnswerIndex.FOUR.getValue()).getAnswer());
    }

    private void disableThirdQuestion() {
        textViewQuestionThree.setVisibility(View.GONE);
        radioGroupThree.setVisibility(View.GONE);
        slightThree.setVisibility(View.GONE);
    }

    public void showFirstCorrectAnswer() {
        Question firstQuestion = partGroupQuestionExamPresent.getFirstQuestion();
        Integer correctAnswer = firstQuestion.getCorrectAnswer();
        switch (correctAnswer) {
            case 0:
                radioButtonOneA.setTextColor(getResources().getColor(R.color.correctAnswer));
                break;
            case 1:
                radioButtonOneB.setTextColor(getResources().getColor(R.color.correctAnswer));
                break;
            case 2:
                radioButtonOneC.setTextColor(getResources().getColor(R.color.correctAnswer));
                break;
            case 3:
                radioButtonOneD.setTextColor(getResources().getColor(R.color.correctAnswer));
                break;
        }
    }

    public void showSecondCorrectAnswer() {
        Question secondQuestion = partGroupQuestionExamPresent.getSecondQuestion();
        Integer correctAnswer = secondQuestion.getCorrectAnswer();
        switch (correctAnswer) {
            case 0:
                radioButtonTwoA.setTextColor(getResources().getColor(R.color.correctAnswer));
                break;
            case 1:
                radioButtonTwoB.setTextColor(getResources().getColor(R.color.correctAnswer));
                break;
            case 2:
                radioButtonTwoC.setTextColor(getResources().getColor(R.color.correctAnswer));
                break;
            case 3:
                radioButtonTwoD.setTextColor(getResources().getColor(R.color.correctAnswer));
                break;
        }
    }

    public void showThirdCorrectAnswer() {
        if (radioGroupThree.getVisibility() == View.VISIBLE) {
            Question thirdQuestion = partGroupQuestionExamPresent.getThirdQuestion();
            Integer correctAnswer = thirdQuestion.getCorrectAnswer();
            switch (correctAnswer) {
                case 0:
                    radioButtonThreeA.setTextColor(getResources().getColor(R.color.correctAnswer));
                    break;
                case 1:
                    radioButtonThreeB.setTextColor(getResources().getColor(R.color.correctAnswer));
                    break;
                case 2:
                    radioButtonThreeC.setTextColor(getResources().getColor(R.color.correctAnswer));
                    break;
                case 3:
                    radioButtonThreeD.setTextColor(getResources().getColor(R.color.correctAnswer));
                    break;
            }
        }
    }

    public Integer getFirstAnswer() {
        if (radioButtonOneA.isChecked()) {
            return Answer.AnswerIndex.FRIST.getValue();
        } else if (radioButtonOneB.isChecked()) {
            return Answer.AnswerIndex.SECOND.getValue();
        } else if (radioButtonOneC.isChecked()) {
            return Answer.AnswerIndex.THIRD.getValue();
        } else if (radioButtonOneD.isChecked()) {
            return Answer.AnswerIndex.FOUR.getValue();
        }
        return -1;
    }

    public Integer getSecondAnswer() {
        if (radioButtonTwoA.isChecked()) {
            return Answer.AnswerIndex.FRIST.getValue();
        } else if (radioButtonTwoB.isChecked()) {
            return Answer.AnswerIndex.SECOND.getValue();
        } else if (radioButtonTwoC.isChecked()) {
            return Answer.AnswerIndex.THIRD.getValue();
        } else if (radioButtonTwoD.isChecked()) {
            return Answer.AnswerIndex.FOUR.getValue();
        }
        return -1;
    }

    public Integer getThirdAnswer() {
        if (radioGroupThree.getVisibility() != View.VISIBLE) {
            return null;
        }
        if (radioButtonThreeA.isChecked()) {
            return Answer.AnswerIndex.FRIST.getValue();
        } else if (radioButtonThreeB.isChecked()) {
            return Answer.AnswerIndex.SECOND.getValue();
        } else if (radioButtonThreeC.isChecked()) {
            return Answer.AnswerIndex.THIRD.getValue();
        } else if (radioButtonThreeD.isChecked()) {
            return Answer.AnswerIndex.FOUR.getValue();
        }
        return -1;
    }

    @Override
    public void endPart() {
        FragmentActivity activity = getActivity();
        if (activity instanceof PartMainActivity) {
            showResult();
        } else if (activity instanceof TestActivity) {
            nextPart();
        }
    }

    private void showResult() {
        PartMainActivity activity = (PartMainActivity) getActivity();
        if (activity != null) {
            Part.PartIndex partIndex = activity.getPartIndex();
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(PART, partIndex.getValue());
            switch (partIndex.getValue()) {
                case 3:
                    bundle.putSerializable(RESULT, Result.builder().partThreeScore(partGroupQuestionExamPresent.getGroupQuestionPoint()).build());
                    break;
                case 4:
                    bundle.putSerializable(RESULT, Result.builder().partFourScore(partGroupQuestionExamPresent.getGroupQuestionPoint()).build());
                    break;
                case 6:
                    bundle.putSerializable(RESULT, Result.builder().partSixScore(partGroupQuestionExamPresent.getGroupQuestionPoint()).build());
                    break;
                case 7:
                    bundle.putSerializable(RESULT, Result.builder().partSevenScore(partGroupQuestionExamPresent.getGroupQuestionPoint()).build());
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

    @Override
    public abstract void notifyView();

    @Override
    public abstract void showCorrectAnswer();
}
