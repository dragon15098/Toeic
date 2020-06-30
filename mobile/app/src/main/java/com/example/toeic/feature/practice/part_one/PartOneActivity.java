package com.example.toeic.feature.practice.part_one;

import android.content.Intent;
import android.os.Bundle;

import com.example.toeic.data.model.Exam;
import com.example.toeic.data.model.Part;
import com.example.toeic.feature.practice.part.PartActivity;
import com.example.toeic.feature.practice.part_main.PartMainActivity;
import com.example.toeic.feature.practice.part_one_exam.PartOneExamFragment;

import static com.example.toeic.ultis.Constraints.EXAM;
import static com.example.toeic.ultis.Constraints.PART;

public class PartOneActivity extends PartActivity {
    @Override
    public Part.PartIndex getPartIndex() {
        return Part.PartIndex.PART_ONE;
    }
}
