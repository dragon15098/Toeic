package com.example.toeic.feature.exam.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.base.BaseActivity;
import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.feature.exam.result.TestResultActivity;
import com.example.toeic.feature.practice.PartFragmentFactory;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.toeic.ultis.Constraints.RESULT;

public class TestActivity extends BaseActivity implements TestView {


    private static final int RESULT_CODE = 1000;
    @BindView(R2.id.tvTimer)
    TextView tvTimer;

    CountDownTimer countDownTimer;
    int time = 2 * 60 * 60 * 1000; // 4 minutes
    int interval = 1000; // 1 second

    TestPresent testPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        setUpPresent();
        changeFragment();
        setUpCountDownTimer();
    }


    @OnClick(R2.id.finish)
    public void pressFinishButton() {
        showResult();
    }


    private void setUpCountDownTimer() {
        countDownTimer = new CountDownTimer(time, interval) {
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(getDateFromMillis(millisUntilFinished));
            }

            public void onFinish() {
                tvTimer.setText(R.string.end_time);
                Vibrator vibrator = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                }
                if (vibrator != null && vibrator.hasVibrator()) {
                    vibrator.vibrate(500);
                }
            }
        };
        countDownTimer.start();
    }

    private void setUpPresent() {
        testPresent = new TestPresentImpl();
        testPresent.attachView(this);
    }

    public static String getDateFromMillis(long d) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.ROOT);
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return df.format(d);
    }

    public void changeFragment() {
        testPresent.updateCurrentPart();
        int currentPart = testPresent.getCurrentPart();
        if (currentPart <= 7) {
            replaceFragment(new PartFragmentFactory(currentPart).getPartFragment());
        } else {
            showResult();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_fragment, fragment);
        fragmentTransaction.commit();
    }

    private void showResult() {
        Intent result = new Intent(this, TestResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(RESULT, testPresent.getResult());
        result.putExtras(bundle);
        startActivityForResult(result, RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CODE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                onBackPressed();
            } else {
                showAnswer();
            }
        }
    }

    private void showAnswer() {

    }

    public TestPresent getPresent() {
        return testPresent;
    }
}
