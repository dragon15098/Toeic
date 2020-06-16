package com.example.toeic.feature.exam.part_exam.question;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.example.base.BaseActivity;
import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Answer;
import com.example.toeic.data.model.Exam;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.toeic.data.network.HttpHelper.SERVICE_RESOURCE;
import static com.example.toeic.ultis.Constraints.EXAM;

public abstract class  PartQuestionExamActivity extends BaseActivity implements PartQuestionExamView {
    public Exam exam;

    @BindView(R2.id.radioButtonA)
    public RadioButton radioButtonA;

    @BindView(R2.id.radioButtonB)
    public RadioButton radioButtonB;

    @BindView(R2.id.radioButtonC)
    public RadioButton radioButtonC;

    @BindView(R2.id.mp3SeekBar)
    public SeekBar mp3SeekBar;

    @BindView(R2.id.playMp3)
    public Button play;

    public PartQuestionExamPresent partQuestionExamPresent;

    public MediaPlayer mediaPlayer;

    @OnClick(R2.id.nextQuestion)
    public void setUpNextQuestion() {
        if(mediaPlayer.isPlaying()){
            play.setText(R.string.play_mp3);
            mediaPlayer.release();
        }
        partQuestionExamPresent.nextQuestion();
    }

    @OnClick(R2.id.backQuestion)
    public void setUpBackQuestion() {
        partQuestionExamPresent.backQuestion();
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

    @OnClick(R2.id.submitButton)
    public abstract void setUpSubmitAnswer();

    public void getData() {
        Intent intent = getIntent();
        exam = (Exam) intent.getSerializableExtra(EXAM);
    }

    @Override
    public abstract void notifyView();

    void hideAllAnswer() {
        radioButtonA.setText(R.string.A);
        radioButtonB.setText(R.string.B);
        radioButtonC.setText(R.string.C);
        resetTextColor();
        uncheckAllAnswer();
    }

    void uncheckAllAnswer() {
        radioButtonA.setChecked(false);
        radioButtonB.setChecked(false);
        radioButtonC.setChecked(false);
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

    private void resetTextColor() {
        int colorBlack = getResources().getColor(R.color.black);
        radioButtonA.setTextColor(colorBlack);
        radioButtonB.setTextColor(colorBlack);
        radioButtonC.setTextColor(colorBlack);
    }

    @Override
    public abstract void showCorrectAnswer();
}
