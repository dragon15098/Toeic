package com.example.toeic.feature.exam.part_one;

import android.content.Intent;
import android.os.Bundle;

import com.example.toeic.data.model.Exam;
import com.example.toeic.feature.exam.part.PartActivity;
import com.example.toeic.feature.exam.part_one_exam.PartOneExamActivity;

import static com.example.toeic.ultis.Constraints.EXAM;

public class PartOneActivity extends PartActivity {
    public void startPartOneExam(Exam exam) {
        Intent intent = new Intent(this, PartOneExamActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXAM, exam);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
