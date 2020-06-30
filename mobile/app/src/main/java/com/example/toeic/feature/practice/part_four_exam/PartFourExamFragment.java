package com.example.toeic.feature.practice.part_four_exam;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.feature.exam.test.TestActivity;
import com.example.toeic.feature.practice.part_exam.group_question.PartGroupQuestionExamFragment;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.toeic.data.network.HttpHelper.SERVICE_RESOURCE;
import static com.example.toeic.ultis.Constraints.EMPTY_STRING;

public class PartFourExamFragment extends PartGroupQuestionExamFragment implements PartFourExamView {

    @BindView(R2.id.explainAnswer)
    HtmlTextView explainAnswer;

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
        Integer firstAnswer = getFirstAnswer();
        Integer secondAnswer = getSecondAnswer();
        Integer thirdAnswer = getThirdAnswer();
        if (isTesting()) {
            TestActivity activity = (TestActivity) getActivity();
            if (activity != null) {
                ((PartFourExamPresent) partGroupQuestionExamPresent).submitAnswer(firstAnswer, secondAnswer, thirdAnswer);
                activity.getPresent().updatePoint(partGroupQuestionExamPresent.getGroupQuestionPoint());
            }
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_part_four_exam, container, false);
        ButterKnife.bind(this, view);
        getData();
        setUpPresent();
        hideButtonWhenTesting();
        return view;
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

    private void setUpPresent() {
        partGroupQuestionExamPresent = new PartFourExamPresentImpl();
        partGroupQuestionExamPresent.attachView(this);
        ((PartFourExamPresent) partGroupQuestionExamPresent).getAllQuestionPartFourByExamId(exam.getId());
    }

    @Override
    public void setUpSubmitAnswer() {
        Integer firstAnswer = getFirstAnswer();
        Integer secondAnswer = getSecondAnswer();
        Integer thirdAnswer = getThirdAnswer();
        ((PartFourExamPresent) partGroupQuestionExamPresent).submitAnswer(firstAnswer, secondAnswer, thirdAnswer);
    }

    @Override
    public void hideButtonWhenTesting() {
        if (isTesting()) {
            hideButton();
        }
    }

    @Override
    public void notifyView() {
        hideExplainQuestion();
        clearButtonGroup();
        showFirstQuestion();
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
        FragmentActivity activity = getActivity();
        if (activity != null) {
            explainAnswer.setHtml(((PartFourExamPresent) partGroupQuestionExamPresent).getExplain(),
                    new HtmlResImageGetter(activity.getBaseContext()));
        }
    }

}
