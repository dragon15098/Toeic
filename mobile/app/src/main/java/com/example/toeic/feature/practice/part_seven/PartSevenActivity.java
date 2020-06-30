package com.example.toeic.feature.practice.part_seven;

import android.content.Intent;
import android.os.Bundle;

import com.example.toeic.data.model.Exam;
import com.example.toeic.data.model.Part;
import com.example.toeic.feature.practice.part.PartActivity;
import com.example.toeic.feature.practice.part_seven_exam.PartSevenExamFragment;

import static com.example.toeic.ultis.Constraints.EXAM;

public class PartSevenActivity extends PartActivity {
    @Override
    public Part.PartIndex getPartIndex() {
        return Part.PartIndex.PART_SEVEN;
    }
}
