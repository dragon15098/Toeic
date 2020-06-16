package com.example.toeic.feature.exam.part;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.BaseActivity;
import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Exam;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class PartActivity extends BaseActivity implements PartView {
    public abstract void startPartOneExam(Exam exam);

    @BindView(R2.id.exams)
    RecyclerView recyclerView;

    PartPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part);
        ButterKnife.bind(this);
        setUpPresenter();
        setUpRecycleView();

    }

    private void setUpPresenter() {
        presenter = new PartPresenterImpl();
        presenter.attachView(this);
    }

    private void setUpRecycleView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(new PartAdapter(presenter.getData(), (view, object) -> {
            if (object instanceof Exam) {
                Exam exam = (Exam) object;
                startPartOneExam(exam);
            }
        }));
        presenter.getAllExam();
    }

    @Override
    public void notifyRecycleView() {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
