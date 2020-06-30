package com.example.toeic.feature.practice.part_seven_exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Answer;
import com.example.toeic.data.model.Question;
import com.example.toeic.feature.exam.test.TestActivity;
import com.example.toeic.feature.practice.part_exam.group_question.PartGroupQuestionExamFragment;
import com.example.toeic.feature.practice.part_six_exam.PartSixExamPresent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.toeic.data.network.HttpHelper.SERVICE_RESOURCE;

public class PartSevenExamFragment extends PartGroupQuestionExamFragment implements PartSevenExamView {

    @BindView(R2.id.paragraph)
    WebView paragraph;

    @BindView(R2.id.firstImage)
    ImageView firstImage;


    @BindView(R2.id.secondImage)
    ImageView secondImage;

    @BindView(R2.id.thridImage)
    ImageView thridImage;

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


    @BindView(R2.id.slightFive)
    public View slightFive;

    @BindView(R2.id.textViewQuestionFive)
    public TextView textViewQuestionFive;

    @BindView(R2.id.radioGroupFive)
    public RadioGroup radioGroupFive;

    @BindView(R2.id.radioButtonFiveA)
    public RadioButton radioButtonFiveA;

    @BindView(R2.id.radioButtonFiveB)
    public RadioButton radioButtonFiveB;

    @BindView(R2.id.radioButtonFiveC)
    public RadioButton radioButtonFiveC;

    @BindView(R2.id.radioButtonFiveD)
    public RadioButton radioButtonFiveD;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_part_seven_exam, container, false);
        ButterKnife.bind(this, view);
        getData();
        setUpPresent();
        return view;
    }

    private void setUpPresent() {
        partGroupQuestionExamPresent = new PartSevenExamPresentImpl();
        partGroupQuestionExamPresent.attachView(this);
        ((PartSevenExamPresent) partGroupQuestionExamPresent).getAllQuestionPartSevenByExamId(exam.getId());
    }

    @OnClick(R2.id.nextQuestion)
    public void setUpNextQuestion() {
        Integer firstAnswer = getFirstAnswer();
        Integer secondAnswer = getSecondAnswer();
        Integer thirdAnswer = getThirdAnswer();
        Integer fourthAnswer = getFourthAnswer();
        Integer fifthAnswer = getFifthAnswer();
        if (isTesting()) {
            TestActivity activity = (TestActivity) getActivity();
            if (activity != null) {
                ((PartSevenExamPresent) partGroupQuestionExamPresent).submitAnswer(firstAnswer, secondAnswer, thirdAnswer, fourthAnswer, fifthAnswer);

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
        Integer fifthAnswer = getFifthAnswer();
        ((PartSevenExamPresent) partGroupQuestionExamPresent).submitAnswer(firstAnswer, secondAnswer, thirdAnswer, fourthAnswer, fifthAnswer);
        showCorrectAnswer();
    }

    @Override
    public void hideButtonWhenTesting() {
        if (isTesting()) {
            hideButton();
        }
    }


    private Integer getFourthAnswer() {
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

    private Integer getFifthAnswer() {
        if (radioGroupFive.getVisibility() != View.VISIBLE) {
            return null;
        }
        if (radioButtonFiveA.isChecked()) {
            return Answer.AnswerIndex.FRIST.getValue();
        } else if (radioButtonFiveB.isChecked()) {
            return Answer.AnswerIndex.SECOND.getValue();
        } else if (radioButtonFiveC.isChecked()) {
            return Answer.AnswerIndex.THIRD.getValue();
        } else if (radioButtonFiveD.isChecked()) {
            return Answer.AnswerIndex.FOUR.getValue();
        }
        return -1;
    }

    @Override
    public void notifyView() {
        clearButtonGroup();
        clearFourButtonGroup();
        clearFiveButtonGroup();

        showParagraph();
        showImage();

        showFirstQuestion();
        showSecondQuestion();
        showThirdQuestion();
        showFourthQuestion();
        showFifthQuestion();
    }

    private void showFourthQuestion() {
        Question fourthQuestion = ((PartSevenExamPresent) partGroupQuestionExamPresent).getFourthQuestion();
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

    private void setUpFourthQuestion(Question fourthQuestion) {
        textViewQuestionFour.setText(fourthQuestion.getQuestion());
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

    private void showFifthQuestion() {
        Question fifthQuestion = ((PartSevenExamPresent) partGroupQuestionExamPresent).getFifthQuestion();
        if (fifthQuestion != null) {
            slightFive.setVisibility(View.VISIBLE);
            textViewQuestionFive.setVisibility(View.VISIBLE);
            radioGroupFive.setVisibility(View.VISIBLE);
            setUpFifthQuestion(fifthQuestion);
            setUpFifthGroupAnswer(fifthQuestion);
        } else {
            disableFifthQuestion();
        }
    }

    private void setUpFifthQuestion(Question FifthQuestion) {
        textViewQuestionFive.setText(FifthQuestion.getQuestion());
    }

    private void setUpFifthGroupAnswer(Question fifthQuestion) {
        List<Answer> answers = fifthQuestion.getAnswers();
        radioButtonFiveA.setText(answers.get(Answer.AnswerIndex.FRIST.getValue()).getAnswer());
        radioButtonFiveB.setText(answers.get(Answer.AnswerIndex.SECOND.getValue()).getAnswer());
        radioButtonFiveC.setText(answers.get(Answer.AnswerIndex.THIRD.getValue()).getAnswer());
        radioButtonFiveD.setText(answers.get(Answer.AnswerIndex.FOUR.getValue()).getAnswer());
    }

    private void disableFifthQuestion() {
        slightFive.setVisibility(View.GONE);
        textViewQuestionFive.setVisibility(View.GONE);
        radioGroupFive.setVisibility(View.GONE);
    }

    private void clearFourButtonGroup() {
        radioGroupFour.clearCheck();
        radioButtonFourA.setTextColor(getResources().getColor(R.color.black));
        radioButtonFourB.setTextColor(getResources().getColor(R.color.black));
        radioButtonFourC.setTextColor(getResources().getColor(R.color.black));
        radioButtonFourD.setTextColor(getResources().getColor(R.color.black));
    }

    private void clearFiveButtonGroup() {
        radioGroupFive.clearCheck();
        radioButtonFiveA.setTextColor(getResources().getColor(R.color.black));
        radioButtonFiveB.setTextColor(getResources().getColor(R.color.black));
        radioButtonFiveC.setTextColor(getResources().getColor(R.color.black));
        radioButtonFiveD.setTextColor(getResources().getColor(R.color.black));
    }

    private void showImage() {
        showFirstImage();
        showSecondImage();
        showThirdImage();
    }

    private void showFirstImage() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            if (partGroupQuestionExamPresent.getUrlImage() != null) {
                String[] urlImages = partGroupQuestionExamPresent.getUrlImage().split(",");
                if (urlImages.length >= 1) {
                    String urlImage = urlImages[0].trim();
                    if (!urlImage.equals("")) {
                        firstImage.setVisibility(View.VISIBLE);
                        Glide.with(activity.getBaseContext())
                                .load(SERVICE_RESOURCE + urlImage)
                                .into(firstImage);
                    }
                } else {
                    firstImage.setVisibility(View.GONE);
                }
            } else {
                firstImage.setVisibility(View.GONE);
            }
        }
    }

    private void showSecondImage() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            if (partGroupQuestionExamPresent.getUrlImage() != null) {
                String[] urlImages = partGroupQuestionExamPresent.getUrlImage().split(",");
                if (urlImages.length >= 2) {
                    String urlImage = urlImages[1].trim();
                    if (!urlImage.equals("")) {
                        secondImage.setVisibility(View.VISIBLE);
                        Glide.with(activity.getBaseContext())
                                .load(SERVICE_RESOURCE + urlImage)
                                .into(secondImage);
                    }
                } else {
                    secondImage.setVisibility(View.GONE);
                }
            } else {
                secondImage.setVisibility(View.GONE);
            }
        }
    }

    private void showThirdImage() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            if (partGroupQuestionExamPresent.getUrlImage() != null) {
                String[] urlImages = partGroupQuestionExamPresent.getUrlImage().split(",");

                if (urlImages.length >= 3) {
                    String urlImage = urlImages[2].trim();
                    if (!urlImage.equals("")) {
                        thridImage.setVisibility(View.VISIBLE);
                        Glide.with(activity.getBaseContext())
                                .load(SERVICE_RESOURCE + urlImage)
                                .into(thridImage);
                    }
                } else {
                    thridImage.setVisibility(View.GONE);
                }
            } else {
                thridImage.setVisibility(View.GONE);
            }
        }

    }

    private void showParagraph() {
        String paragraph = ((PartSevenExamPresent) partGroupQuestionExamPresent).getParagraph();
        if (paragraph != null) {
            this.paragraph.setVisibility(View.VISIBLE);
            this.paragraph.loadData(((PartSevenExamPresent) partGroupQuestionExamPresent).getParagraph(), "text/html", "utf-8");
        } else {
            this.paragraph.setVisibility(View.GONE);
        }
    }

    @Override
    public void showCorrectAnswer() {
        showFirstCorrectAnswer();
        showSecondCorrectAnswer();
        showThirdCorrectAnswer();
        showFourthCorrectAnswer();
        showFifthCorrectAnswer();
    }

    private void showFourthCorrectAnswer() {
        if (radioGroupFour.getVisibility() == View.VISIBLE) {
            Question fourthQuestion = ((PartSevenExamPresent) partGroupQuestionExamPresent).getFourthQuestion();
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
    }

    private void showFifthCorrectAnswer() {
        if (radioGroupFive.getVisibility() == View.VISIBLE) {
            Question fifthQuestion = ((PartSevenExamPresent) partGroupQuestionExamPresent).getFifthQuestion();
            Integer correctAnswer = fifthQuestion.getCorrectAnswer();
            switch (correctAnswer) {
                case 0:
                    radioButtonFiveA.setTextColor(getResources().getColor(R.color.correctAnswer));
                    break;
                case 1:
                    radioButtonFiveB.setTextColor(getResources().getColor(R.color.correctAnswer));
                    break;
                case 2:
                    radioButtonFiveC.setTextColor(getResources().getColor(R.color.correctAnswer));
                    break;
                case 3:
                    radioButtonFiveD.setTextColor(getResources().getColor(R.color.correctAnswer));
                    break;
            }
        }

    }

    @Override
    public void showExplainAnswer() {

    }
}
