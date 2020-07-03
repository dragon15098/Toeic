package com.example.toeic.feature.practice;

import androidx.fragment.app.Fragment;

import com.example.toeic.data.model.Part;
import com.example.toeic.feature.practice.part_five_exam.PartFiveExamFragment;
import com.example.toeic.feature.practice.part_four_exam.PartFourExamFragment;
import com.example.toeic.feature.practice.part_four_exam.PartFourExamPresent;
import com.example.toeic.feature.practice.part_one_exam.PartOneExamFragment;
import com.example.toeic.feature.practice.part_seven_exam.PartSevenExamFragment;
import com.example.toeic.feature.practice.part_six_exam.PartSixExamFragment;
import com.example.toeic.feature.practice.part_three_exam.PartThreeExamFragment;
import com.example.toeic.feature.practice.part_two_exam.PartTwoExamFragment;

public class PartFragmentFactory {
    private int partNumber;

    public PartFragmentFactory(Integer partNumber) {
        this.partNumber = partNumber;
    }

    public Fragment getPartFragment() {
        if (partNumber == Part.PartIndex.PART_ONE.getValue()) {
            return new PartOneExamFragment();
        } else if (partNumber == Part.PartIndex.PART_TWO.getValue()) {
            return new PartTwoExamFragment();
        } else if (partNumber == Part.PartIndex.PART_THREE.getValue()) {
            return new PartThreeExamFragment();
        } else if (partNumber == Part.PartIndex.PART_FOUR.getValue()) {
            return new PartFourExamFragment();
        } else if (partNumber == Part.PartIndex.PART_FIVE.getValue()) {
            return new PartFiveExamFragment();
        } else if (partNumber == Part.PartIndex.PART_SIX.getValue()) {
            return new PartSixExamFragment();
        } else if (partNumber == Part.PartIndex.PART_SEVEN.getValue()) {
            return new PartSevenExamFragment();
        }
        return null;
    }
}
