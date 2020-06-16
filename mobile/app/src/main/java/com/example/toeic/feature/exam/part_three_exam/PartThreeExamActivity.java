package com.example.toeic.feature.exam.part_three_exam;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.feature.exam.part_exam.group_question.PartGroupQuestionExamActivity;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.toeic.ultis.Constraints.EMPTY_STRING;

public class PartThreeExamActivity extends PartGroupQuestionExamActivity implements PartThreeExamView {


    @BindView(R2.id.explainAnswer2)
    HtmlTextView explainAnswer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_three_exam);
        ButterKnife.bind(this);
        getData();
        setUpPresent();
    }

    private void setUpPresent() {
        partGroupQuestionExamPresent = new PartThreeExamPresentImpl();
        partGroupQuestionExamPresent.attachView(this);
        ((PartThreeExamPresent) partGroupQuestionExamPresent).getAllQuestionPartThreeByExamId(exam.getId());
    }

    @Override
    public void setUpSubmitAnswer() {
        Integer firstAnswer = getFirstAnswer();
        Integer secondAnswer = getSecondAnswer();
        Integer thirdAnswer = getThirdAnswer();
        partGroupQuestionExamPresent.submitAnswer(firstAnswer, secondAnswer, thirdAnswer);
    }

    @Override
    public void notifyView() {
        clearButtonGroup();
        hideExplainQuestion();
        showFirstGroupQuestion();
        showSecondQuestion();
        showThirdQuestion();
        setUpMp3(partGroupQuestionExamPresent.getMp3Link());
    }

    private void hideExplainQuestion() {
        explainAnswer.setText(EMPTY_STRING);
    }

    @Override
    public void showCorrectAnswer() {
        showFirstCorrectAnswer();
        showSecondCorrectAnswer();
        showThirdCorrectAnswer();
    }

    @Override
    public void showExplainAnswer() {
        explainAnswer.setHtml(((PartThreeExamPresent) partGroupQuestionExamPresent).getExplain(),
                new HtmlResImageGetter(getBaseContext()));
    }

}
