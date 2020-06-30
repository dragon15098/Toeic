package com.example.toeic.feature.practice.part;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.BaseActivity;
import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Exam;
import com.example.toeic.data.model.Part;
import com.example.toeic.feature.practice.part_main.PartMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.toeic.ultis.Constraints.EXAM;
import static com.example.toeic.ultis.Constraints.PART;

public abstract class PartActivity extends BaseActivity implements PartView {
    public abstract Part.PartIndex getPartIndex();

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
                Intent intent = new Intent(this, PartMainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(EXAM, exam);
                bundle.putSerializable(PART, getPartIndex());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }));
        presenter.getAllExam();
    }

    @Override
    public void notifyRecycleView() {
        PartAdapter adapter = (PartAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
