package com.example.toeic.feature.exam.part_three;

import android.content.Intent;
import android.os.Bundle;

import com.example.toeic.data.model.Exam;
import com.example.toeic.feature.exam.part.PartActivity;
import com.example.toeic.feature.exam.part_three_exam.PartThreeExamActivity;

import static com.example.toeic.ultis.Constraints.EXAM;

public class PartThreeActivity extends PartActivity {
    public void startPartOneExam(Exam exam) {
        Intent intent = new Intent(this, PartThreeExamActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXAM, exam);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
