package com.example.toeic.feature.practice.part_main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.base.BaseActivity;
import com.example.toeic.R;
import com.example.toeic.data.model.Part;
import com.example.toeic.feature.practice.PartFragmentFactory;
import com.example.toeic.feature.practice.part_five_exam.PartFiveExamFragment;
import com.example.toeic.feature.practice.part_four_exam.PartFourExamFragment;
import com.example.toeic.feature.practice.part_one_exam.PartOneExamFragment;
import com.example.toeic.feature.practice.part_seven_exam.PartSevenExamFragment;
import com.example.toeic.feature.practice.part_six_exam.PartSixExamFragment;
import com.example.toeic.feature.practice.part_three_exam.PartThreeExamFragment;
import com.example.toeic.feature.practice.part_two_exam.PartTwoExamFragment;

import static com.example.toeic.ultis.Constraints.PART;

public class PartMainActivity extends BaseActivity implements PartMainView {
    public Part.PartIndex partIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_main);
        getPartNumber();
        changeFragment();
    }

    private void getPartNumber() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                partIndex = (Part.PartIndex) extras.getSerializable(PART);
            }
        }
    }

    private void changeFragment() {
        int partNumber = partIndex.getValue();
        replaceFragment(new PartFragmentFactory(partNumber).getPartFragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_fragment, fragment);
        fragmentTransaction.commit();
    }

    public Part.PartIndex getPartIndex() {
        return partIndex;
    }

}
