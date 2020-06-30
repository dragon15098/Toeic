package com.example.toeic.feature.practice.part_one_exam;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
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

public class PartOneExamFragment extends PartQuestionExamFragment implements PartOneExamView {

    @BindView(R2.id.imageView)
    ImageView imageView;

    @BindView(R2.id.radioButtonD)
    RadioButton radioButtonD;

    @BindView(R2.id.mp3SeekBar)
    public SeekBar mp3SeekBar;

    @BindView(R2.id.playMp3)
    public Button play;

    public MediaPlayer mediaPlayer;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_part_one_exam, container, false);
        ButterKnife.bind(this, view);
        getData();
        setUpPresent();
        hideButtonWhenTesting();
        return view;
    }

    @OnClick(R2.id.submitButton)
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


    private void setUpPresent() {
        partQuestionExamPresent = new PartOneExamPresentImpl();
        partQuestionExamPresent.attachView(this);
        ((PartOneExamPresent) partQuestionExamPresent).getAllQuestionPartOneByExamId(exam.getId());
    }


    @Override
    public void notifyView() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Glide.with(activity.getBaseContext())
                    .load(SERVICE_RESOURCE + partQuestionExamPresent.getUrlImage())
                    .into(imageView);
        }
        setUpMp3(partQuestionExamPresent.getMp3Link());
        hideAllAnswer();
    }

    private void hideAllAnswer() {
        radioButtonA.setText(R.string.A);
        radioButtonB.setText(R.string.B);
        radioButtonC.setText(R.string.C);
        radioButtonD.setText(R.string.D);
        resetTextColor();
        uncheckAllAnswer();
    }

    private void uncheckAllAnswer() {
        radioGroup.clearCheck();
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
        } else {
            radioButtonD.setTextColor(colorRes);
        }
    }

    private void resetTextColor() {
        int colorBlack = getResources().getColor(R.color.black);
        radioButtonA.setTextColor(colorBlack);
        radioButtonB.setTextColor(colorBlack);
        radioButtonC.setTextColor(colorBlack);
        radioButtonD.setTextColor(colorBlack);
    }

    @Override
    public void showCorrectAnswer() {
        radioButtonA.setText(partQuestionExamPresent.getExplainAnswerA());
        radioButtonB.setText(partQuestionExamPresent.getExplainAnswerB());
        radioButtonC.setText(partQuestionExamPresent.getExplainAnswerC());
        radioButtonD.setText(partQuestionExamPresent.getExplainAnswerD());
        changeCorrectAnswerColor();
    }


}
