package com.example.toeic.feature.exam.part_three_exam;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.feature.exam.part_exam.group_question.PartGroupQuestionExamActivity;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PartThreeExamActivity extends PartGroupQuestionExamActivity implements PartThreeExamView {

    @BindView(R2.id.explainAnswer)
    TextView explainAnswer;

    @BindView(R2.id.explainAnswer2)
    HtmlTextView explainAnswer2;

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
        explainAnswer.setText(((PartThreeExamPresent) partGroupQuestionExamPresent).getExplain());
        explainAnswer2.setHtml("<p>W: Hi, Michael, can I have a word?</p> <p>M: Certainly, Megan. Come in. How is the preparation for the conference going?</p> <p>W: Well, that's what I wanted to speak to you about.</p> <p>M: No problems I hope...</p> <p>W: No; no real problems. But I've just finished writing my presentation, and really I have no idea if it's at the right level for the audience. I was wondering, since you've been to a lot of these conferences, if you could listen to my presentation and tell me what you think.</p> <p>M: Yes, of course. However, I have a deadline on Thursday, and my schedule's pretty full... When's your conference?</p> <p>W: The 10th.</p> <p>M: Oh, okay! You have a bit of time then. How about Friday the 5th, in the morning?</p> <p>W: That would be great, thanks.&nbsp;<br> &nbsp;</p>",
                new HtmlResImageGetter(getBaseContext()));
    }

}
