package com.example.toeic.feature.exam.part_exam.group_question;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Answer;
import com.example.toeic.data.model.Exam;
import com.example.toeic.data.model.Question;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.toeic.data.network.HttpHelper.SERVICE_RESOURCE;
import static com.example.toeic.ultis.Constraints.EXAM;

public abstract class PartGroupQuestionExamActivity extends BaseActivity implements PartGroupQuestionExamView {
    public Exam exam;

    @BindView(R2.id.mp3SeekBar)
    public SeekBar mp3SeekBar;

    @BindView(R2.id.playMp3)
    public Button play;

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

    public MediaPlayer mediaPlayer;

    @OnClick(R2.id.nextQuestion)
    public void setUpNextQuestion() {
        if (mediaPlayer.isPlaying()) {
            play.setText(R.string.play_mp3);
            mediaPlayer.release();
        }
        partGroupQuestionExamPresent.nextQuestion();
    }

    @OnClick(R2.id.playMp3)
    public void setUpButtonPlayMp3() {
        if (!mediaPlayer.isPlaying()) {
            play.setText(R.string.pause);
            mediaPlayer.start();
        } else {
            play.setText(R.string.play_mp3);
            mediaPlayer.pause();
        }
    }

    @OnClick(R2.id.submitButton)
    public abstract void setUpSubmitAnswer();

    @OnClick(R2.id.backQuestion)
    public void setUpBackQuestion() {
        partGroupQuestionExamPresent.backQuestion();
    }

    public void setUpMp3(String mp3Url) {
        String url = SERVICE_RESOURCE + mp3Url;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mp3SeekBar.setMax(mediaPlayer.getDuration());
            mp3SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser)
                        mediaPlayer.seekTo(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mp3SeekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 50);
            }
        }, 50);
    }

    public void getData() {
        Intent intent = getIntent();
        exam = (Exam) intent.getSerializableExtra(EXAM);
    }

    public void clearButtonGroup() {
        clearFirstButtonGroup();
        clearSecondButtonGroup();
        clearThirdButtonGroup();
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

    public void showFirstGroupQuestion() {
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
        setUpThirdQuestion(thirdQuestion);
        setUpThirdGroupAnswer(thirdQuestion);
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

    public Integer getFirstAnswer() {
        Integer firstAnswer = null;
        if (radioButtonOneA.isChecked()) {
            firstAnswer = Answer.AnswerIndex.FRIST.getValue();
        } else if (radioButtonOneB.isChecked()) {
            firstAnswer = Answer.AnswerIndex.SECOND.getValue();
        } else if (radioButtonOneC.isChecked()) {
            firstAnswer = Answer.AnswerIndex.THIRD.getValue();
        } else if (radioButtonOneD.isChecked()) {
            firstAnswer = Answer.AnswerIndex.FOUR.getValue();
        }
        return firstAnswer;
    }

    public Integer getSecondAnswer() {
        Integer secondAnswer = null;
        if (radioButtonTwoA.isChecked()) {
            secondAnswer = Answer.AnswerIndex.FRIST.getValue();
        } else if (radioButtonTwoB.isChecked()) {
            secondAnswer = Answer.AnswerIndex.SECOND.getValue();
        } else if (radioButtonTwoC.isChecked()) {
            secondAnswer = Answer.AnswerIndex.THIRD.getValue();
        } else if (radioButtonTwoD.isChecked()) {
            secondAnswer = Answer.AnswerIndex.FOUR.getValue();
        }
        return secondAnswer;
    }

    public Integer getThirdAnswer() {
        Integer thirdAnswer = null;
        if (radioButtonThreeA.isChecked()) {
            thirdAnswer = Answer.AnswerIndex.FRIST.getValue();
        } else if (radioButtonThreeB.isChecked()) {
            thirdAnswer = Answer.AnswerIndex.SECOND.getValue();
        } else if (radioButtonThreeC.isChecked()) {
            thirdAnswer = Answer.AnswerIndex.THIRD.getValue();
        } else if (radioButtonThreeD.isChecked()) {
            thirdAnswer = Answer.AnswerIndex.FOUR.getValue();
        }
        return thirdAnswer;
    }

    @Override
    public abstract void notifyView();

    @Override
    public abstract void showCorrectAnswer();
}
