package com.example.toeic.feature.exam.part_two;

import android.content.Intent;
import android.os.Bundle;

import com.example.toeic.data.model.Exam;
import com.example.toeic.feature.exam.part.PartActivity;
import com.example.toeic.feature.exam.part_two_exam.PartTwoExamActivity;

import static com.example.toeic.ultis.Constraints.EXAM;

public class PartTwoActivity extends PartActivity {
    @Override
    public void startPartOneExam(Exam exam) {
        Intent intent = new Intent(this, PartTwoExamActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXAM, exam);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
