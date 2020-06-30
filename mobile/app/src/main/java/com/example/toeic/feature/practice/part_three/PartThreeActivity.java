package com.example.toeic.feature.practice.part_three;

import android.content.Intent;
import android.os.Bundle;

import com.example.toeic.data.model.Exam;
import com.example.toeic.data.model.Part;
import com.example.toeic.feature.practice.part.PartActivity;
import com.example.toeic.feature.practice.part_three_exam.PartThreeExamFragment;

import static com.example.toeic.ultis.Constraints.EXAM;

public class PartThreeActivity extends PartActivity {
    @Override
    public Part.PartIndex getPartIndex() {
        return Part.PartIndex.PART_THREE;
    }
}
