package com.example.toeic.feature.practice.part_six_exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Answer;
import com.example.toeic.data.model.Question;
import com.example.toeic.feature.exam.test.TestActivity;
import com.example.toeic.feature.practice.part_exam.group_question.PartGroupQuestionExamFragment;
import com.example.toeic.feature.practice.part_seven_exam.PartSevenExamPresent;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartSixExamFragment extends PartGroupQuestionExamFragment implements PartSixExamView {

    @BindView(R2.id.paragraph)
    HtmlTextView paragraph;

    @BindView(R2.id.slightFour)
    public View slightFour;

    @BindView(R2.id.textViewQuestionFour)
    public TextView textViewQuestionFour;

    @BindView(R2.id.radioGroupFour)
    public RadioGroup radioGroupFour;

    @BindView(R2.id.radioButtonFourA)
    public RadioButton radioButtonFourA;

    @BindView(R2.id.radioButtonFourB)
    public RadioButton radioButtonFourB;

    @BindView(R2.id.radioButtonFourC)
    public RadioButton radioButtonFourC;

    @BindView(R2.id.radioButtonFourD)
    public RadioButton radioButtonFourD;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_part_six_exam, container, false);
        ButterKnife.bind(this, view);
        getData();
        setUpPresent();
        hideButtonWhenTesting();
        return view;
    }

    private void setUpPresent() {
        partGroupQuestionExamPresent = new PartSixExamPresentImpl();
        partGroupQuestionExamPresent.attachView(this);
        ((PartSixExamPresent) partGroupQuestionExamPresent).getAllQuestionPartSixByExamId(exam.getId());
    }

    @OnClick(R2.id.nextQuestion)
    public void setUpNextQuestion() {
        Integer firstAnswer = getFirstAnswer();
        Integer secondAnswer = getSecondAnswer();
        Integer thirdAnswer = getThirdAnswer();
        Integer fourthAnswer = getFourthAnswer();
        if (isTesting()) {
            TestActivity activity = (TestActivity) getActivity();
            if (activity != null) {
                ((PartSixExamPresent) partGroupQuestionExamPresent).submitAnswer(firstAnswer, secondAnswer, thirdAnswer, fourthAnswer);
                activity.getPresent().updatePoint(partGroupQuestionExamPresent.getGroupQuestionPoint());
            }
        }
        partGroupQuestionExamPresent.nextQuestion();
    }

    @Override
    public void setUpSubmitAnswer() {
        Integer firstAnswer = getFirstAnswer();
        Integer secondAnswer = getSecondAnswer();
        Integer thirdAnswer = getThirdAnswer();
        Integer fourthAnswer = getFourthAnswer();
        ((PartSixExamPresent) partGroupQuestionExamPresent).submitAnswer(firstAnswer, secondAnswer, thirdAnswer, fourthAnswer);
        showCorrectAnswer();
    }

    public Integer getFourthAnswer() {
        if (radioGroupFour.getVisibility() != View.VISIBLE) {
            return null;
        }
        if (radioButtonFourA.isChecked()) {
            return Answer.AnswerIndex.FRIST.getValue();
        } else if (radioButtonFourB.isChecked()) {
            return Answer.AnswerIndex.SECOND.getValue();
        } else if (radioButtonFourC.isChecked()) {
            return Answer.AnswerIndex.THIRD.getValue();
        } else if (radioButtonFourD.isChecked()) {
            return Answer.AnswerIndex.FOUR.getValue();
        }
        return -1;
    }

    @Override
    public void hideButtonWhenTesting() {
        if (isTesting()) {
            hideButton();
        }
    }

    @Override
    public void notifyView() {
        clearButtonGroup();
        clearFourButtonGroup();
        showParagraph();
        showFirstQuestion();
        showSecondQuestion();
        showThirdQuestion();
        showFourthQuestion();
    }

    private void showFourthQuestion() {
        Question fourthQuestion = ((PartSixExamPresent) partGroupQuestionExamPresent).getFourthQuestion();
        if (fourthQuestion != null) {
            slightFour.setVisibility(View.VISIBLE);
            textViewQuestionFour.setVisibility(View.VISIBLE);
            radioGroupFour.setVisibility(View.VISIBLE);
            setUpFourthQuestion(fourthQuestion);
            setUpFourthGroupAnswer(fourthQuestion);
        } else {
            disableFourthQuestion();
        }
    }

    private void setUpFourthGroupAnswer(Question fourthQuestion) {
        List<Answer> answers = fourthQuestion.getAnswers();
        radioButtonFourA.setText(answers.get(Answer.AnswerIndex.FRIST.getValue()).getAnswer());
        radioButtonFourB.setText(answers.get(Answer.AnswerIndex.SECOND.getValue()).getAnswer());
        radioButtonFourC.setText(answers.get(Answer.AnswerIndex.THIRD.getValue()).getAnswer());
        radioButtonFourD.setText(answers.get(Answer.AnswerIndex.FOUR.getValue()).getAnswer());
    }

    private void disableFourthQuestion() {
        slightFour.setVisibility(View.GONE);
        textViewQuestionFour.setVisibility(View.GONE);
        radioGroupFour.setVisibility(View.GONE);
    }

    private void setUpFourthQuestion(Question fourthQuestion) {
        textViewQuestionFour.setText(fourthQuestion.getQuestion());
    }


    private void showParagraph() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            paragraph.setHtml(((PartSixExamPresent) partGroupQuestionExamPresent).getParagraph(),
                    new HtmlResImageGetter(activity.getBaseContext()));
        }
    }

    @Override
    public void showCorrectAnswer() {
        showFirstCorrectAnswer();
        showSecondCorrectAnswer();
        showThirdCorrectAnswer();
        showFourthCorrectAnswer();
    }

    private void showFourthCorrectAnswer() {
        Question fourthQuestion = ((PartSixExamPresent) partGroupQuestionExamPresent).getFourthQuestion();
        Integer correctAnswer = fourthQuestion.getCorrectAnswer();
        switch (correctAnswer) {
            case 0:
                radioButtonFourA.setTextColor(getResources().getColor(R.color.correctAnswer));
                break;
            case 1:
                radioButtonFourB.setTextColor(getResources().getColor(R.color.correctAnswer));
                break;
            case 2:
                radioButtonFourC.setTextColor(getResources().getColor(R.color.correctAnswer));
                break;
            case 3:
                radioButtonFourD.setTextColor(getResources().getColor(R.color.correctAnswer));
                break;
        }
    }

    private void clearFourButtonGroup() {
        radioGroupFour.clearCheck();
        radioButtonFourA.setTextColor(getResources().getColor(R.color.black));
        radioButtonFourB.setTextColor(getResources().getColor(R.color.black));
        radioButtonFourC.setTextColor(getResources().getColor(R.color.black));
        radioButtonFourD.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public void showExplainAnswer() {

    }

}
