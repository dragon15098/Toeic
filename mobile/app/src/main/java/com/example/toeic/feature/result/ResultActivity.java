package com.example.toeic.feature.result;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import static com.example.toeic.ultis.Constraints.PART;
import static com.example.toeic.ultis.Constraints.RESULT;

public class ResultActivity extends AppCompatActivity {

    @BindView(R2.id.totalCorrectAnswer)
    TextView totalCorrectAnswer;

    @BindView(R2.id.totalWrongAnswer)
    TextView totalWrongAnswer;

    @BindView(R2.id.viewKonfetti)
    KonfettiView viewConfetti;

    @OnClick(R2.id.btnBack)
    public void onBackButton() {
        onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        getData();
        setUpConfetti();
    }

    private void setUpConfetti() {
        viewConfetti.build()
                .addColors(Color.rgb(252, 225, 138),
                        Color.rgb(255, 114, 109),
                        Color.rgb(180, 141, 239),
                        Color.rgb(244, 48, 109))
                .setDirection(0.0, 359.0)
                .setSpeed(4f, 7f)
                .setTimeToLive(2000L)
                .setFadeOutEnabled(true)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5))
                .setPosition(0, viewConfetti.getWidth() + 1000f, -50f, -50f)
                .streamFor(300, 3000L);
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Result result = (Result) extras.getSerializable(RESULT);
            if (result != null) {
                Integer partNumber = (Integer) extras.getSerializable(PART);
                if (partNumber != null) {
                    switch (partNumber) {
                        case 1:
                            showPartOneResult(result);
                            break;
                        case 2:
                            showPartTwoResult(result);
                            break;
                        case 3:
                            showPartThreeResult(result);
                            break;
                        case 4:
                            showPartFourResult(result);
                            break;
                        case 5:
                            showPartFiveResult(result);
                            break;
                        case 6:
                            showPartSixResult(result);
                            break;
                        case 7:
                            showPartSevenResult(result);
                            break;
                    }
                }
            }
        }
    }

    private void showPartOneResult(Result result) {
        totalCorrectAnswer.setText(getString(R.string.correct_number, result.getPartOneScore().getTotalCorrectAnswer()));
        totalWrongAnswer.setText(getString(R.string.wrong_number, result.getPartOneScore().getTotalWrongAnswer()));
    }

    private void showPartTwoResult(Result result) {
        totalCorrectAnswer.setText(getString(R.string.correct_number, result.getPartTwoScore().getTotalCorrectAnswer()));
        totalWrongAnswer.setText(getString(R.string.wrong_number, result.getPartTwoScore().getTotalWrongAnswer()));
    }

    private void showPartThreeResult(Result result) {
        totalCorrectAnswer.setText(getString(R.string.correct_number, result.getPartThreeScore().getTotalCorrectAnswer()));
        totalWrongAnswer.setText(getString(R.string.wrong_number, result.getPartThreeScore().getTotalWrongAnswer()));
    }

    private void showPartFourResult(Result result) {
        totalCorrectAnswer.setText(getString(R.string.correct_number, result.getPartFourScore().getTotalCorrectAnswer()));
        totalWrongAnswer.setText(getString(R.string.wrong_number, result.getPartFourScore().getTotalWrongAnswer()));
    }

    private void showPartFiveResult(Result result) {
        totalCorrectAnswer.setText(getString(R.string.correct_number, result.getPartFiveScore().getTotalCorrectAnswer()));
        totalWrongAnswer.setText(getString(R.string.wrong_number, result.getPartFiveScore().getTotalWrongAnswer()));
    }

    private void showPartSixResult(Result result) {
        totalCorrectAnswer.setText(getString(R.string.correct_number, result.getPartSixScore().getTotalCorrectAnswer()));
        totalWrongAnswer.setText(getString(R.string.wrong_number, result.getPartSixScore().getTotalWrongAnswer()));
    }

    private void showPartSevenResult(Result result) {
        totalCorrectAnswer.setText(getString(R.string.correct_number, result.getPartSevenScore().getTotalCorrectAnswer()));
        totalWrongAnswer.setText(getString(R.string.wrong_number, result.getPartSevenScore().getTotalWrongAnswer()));
    }

    private void showExamResult(Result result) {
        Intent intent = new Intent(this, ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(RESULT, result);
        intent.putExtras(bundle);
    }
}
