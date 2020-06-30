package com.example.toeic.feature.practice.part_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.BaseFragment;
import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Part;
import com.example.toeic.feature.practice.part_five.PartFiveActivity;
import com.example.toeic.feature.practice.part_four.PartFourActivity;
import com.example.toeic.feature.practice.part_one.PartOneActivity;
import com.example.toeic.feature.practice.part_seven.PartSevenActivity;
import com.example.toeic.feature.practice.part_six.PartSixActivity;
import com.example.toeic.feature.practice.part_three.PartThreeActivity;
import com.example.toeic.feature.practice.part_two.PartTwoActivity;
import com.example.toeic.ultis.ClickCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PartFragment extends BaseFragment implements PartAdapterView, ClickCallBack {

    @BindView(R2.id.parts)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_part, container, false);
        if (v != null) {
            ButterKnife.bind(this, v);
        }
        setUpRecycleView();
        return v;
    }

    private void setUpRecycleView() {
        PartAdapter partAdapter = new PartAdapter(this);
        recyclerView.setAdapter(partAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onClickEvent(View view, Object object) {
        if (object instanceof Part) {
            Part part = (Part) object;
            if (part.partId == Part.PartIndex.PART_ONE.getValue()) {
                startPartOneActivity();
            } else if (part.partId == Part.PartIndex.PART_TWO.getValue()) {
                startPartTwoActivity();
            } else if (part.partId == Part.PartIndex.PART_THREE.getValue()) {
                startPartThreeActivity();
            } else if (part.partId == Part.PartIndex.PART_FOUR.getValue()) {
                startPartFourActivity();
            } else if (part.partId == Part.PartIndex.PART_FIVE.getValue()) {
                startPartFiveActivity();
            } else if (part.partId == Part.PartIndex.PART_SIX.getValue()) {
                startPartSixActivity();
            } else if (part.partId == Part.PartIndex.PART_SEVEN.getValue()) {
                startPartSevenActivity();
            }
        }
    }


    private void startPartOneActivity() {
        Intent intent = new Intent(getActivity(), PartOneActivity.class);
        startActivity(intent);
    }

    private void startPartTwoActivity() {
        Intent intent = new Intent(getActivity(), PartTwoActivity.class);
        startActivity(intent);
    }

    private void startPartThreeActivity() {
        Intent intent = new Intent(getActivity(), PartThreeActivity.class);
        startActivity(intent);
    }


    private void startPartFourActivity() {
        Intent intent = new Intent(getActivity(), PartFourActivity.class);
        startActivity(intent);
    }

    private void startPartFiveActivity() {
        Intent intent = new Intent(getActivity(), PartFiveActivity.class);
        startActivity(intent);
    }

    private void startPartSixActivity() {
        Intent intent = new Intent(getActivity(), PartSixActivity.class);
        startActivity(intent);
    }

    private void startPartSevenActivity() {
        Intent intent = new Intent(getActivity(), PartSevenActivity.class);
        startActivity(intent);
    }


}
