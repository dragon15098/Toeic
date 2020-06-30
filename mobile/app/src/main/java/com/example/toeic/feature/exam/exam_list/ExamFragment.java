package com.example.toeic.feature.exam.exam_list;

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
import com.example.toeic.data.model.Exam;
import com.example.toeic.feature.exam.test.TestActivity;
import com.example.toeic.ultis.ClickCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.toeic.ultis.Constraints.EXAM;

public class ExamFragment extends BaseFragment implements ExamView {

    ExamPresent examPresent;

    @BindView(R2.id.exams)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_exam, container, false);
        if (v != null) {
            ButterKnife.bind(this, v);
        }
        setUpPresenter();
        setUpRecycleView();

        return v;
    }

    private void setUpPresenter() {
        examPresent = new ExamPresentImpl();
        examPresent.attachView(this);
        examPresent.getAllExam();
    }

    private void setUpRecycleView() {
        ExamAdapter examAdapter = new ExamAdapter(examPresent.getExams(), (view, object) -> {
            Exam exam = (Exam) object;
            Intent intent = new Intent(getContext(), TestActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(EXAM, exam);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(examAdapter);
    }

    @Override
    public void notifyView() {
        ExamAdapter adapter = (ExamAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }


}
