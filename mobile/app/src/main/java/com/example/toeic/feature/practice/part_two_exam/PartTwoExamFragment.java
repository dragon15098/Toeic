package com.example.toeic.feature.practice.part_two_exam;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Answer;
import com.example.toeic.feature.exam.test.TestActivity;
import com.example.toeic.feature.practice.part_exam.question.PartQuestionExamFragment;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.toeic.data.network.HttpHelper.SERVICE_RESOURCE;

public class PartTwoExamFragment extends PartQuestionExamFragment implements PartTwoExamView {

    @BindView(R2.id.radioButtonA)
    RadioButton radioButtonA;

    @BindView(R2.id.radioButtonB)
    RadioButton radioButtonB;

    @BindView(R2.id.radioButtonC)
    RadioButton radioButtonC;

    @BindView(R2.id.mp3SeekBar)
    SeekBar mp3SeekBar;

    @BindView(R2.id.playMp3)
    Button play;

    @BindView(R2.id.radioGroup)
    RadioGroup radioGroup;

    public MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_part_two_exam, container, false);
        ButterKnife.bind(this, view);
        getData();
        setUpPresent();
        hideButtonWhenTesting();
        return view;
    }

    @OnClick(R2.id.nextQuestion)
    public void setUpNextQuestion() {
        if (mediaPlayer.isPlaying()) {
            play.setText(R.string.play_mp3);
            mediaPlayer.release();
        }
        if (isTesting()) {
            TestActivity activity = (TestActivity) getActivity();
            if (activity != null) {
                partQuestionExamPresent.submitAnswer(getCurrentAnswer());
                activity.getPresent().updatePoint(partQuestionExamPresent.getQuestionPoint());
            }
        }
        partQuestionExamPresent.nextQuestion();
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

    private void setUpPresent() {
        partQuestionExamPresent = new PartTwoExamPresentImpl();
        partQuestionExamPresent.attachView(this);
        ((PartTwoExamPresent) partQuestionExamPresent).getAllQuestionPartTwoByExamId(exam.getId());
    }

    @Override
    public void notifyView() {
        setUpMp3(partQuestionExamPresent.getMp3Link());
        hideAllAnswer();
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

    @Override
    public void showCorrectAnswer() {
        radioButtonA.setText(partQuestionExamPresent.getExplainAnswerA());
        radioButtonB.setText(partQuestionExamPresent.getExplainAnswerB());
        radioButtonC.setText(partQuestionExamPresent.getExplainAnswerC());
        changeCorrectAnswerColor();
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

    @OnClick(R2.id.submitButton)
    public void setUpSubmitAnswer() {
        Integer currentAnswer = getCurrentAnswer();
        showCorrectAnswer();
    }

    @Override
    public void hideButtonWhenTesting() {
        if (isTesting()) {
            hideBackButton();
        }
    }

    private void hideAllAnswer() {
        radioButtonA.setText(R.string.A);
        radioButtonB.setText(R.string.B);
        radioButtonC.setText(R.string.C);
        resetTextColor();
        uncheckAllAnswer();
    }

    private void resetTextColor() {
        int colorBlack = getResources().getColor(R.color.black);
        radioButtonA.setTextColor(colorBlack);
        radioButtonB.setTextColor(colorBlack);
        radioButtonC.setTextColor(colorBlack);
    }

    private void uncheckAllAnswer() {
        radioGroup.clearCheck();
    }
}
