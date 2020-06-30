package com.example.toeic.feature.exam.exam_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Exam;
import com.example.toeic.ultis.ClickCallBack;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {

    List<Exam> exams;
    ClickCallBack clickCallBack;

    ExamAdapter(List<Exam> exams, ClickCallBack clickCallBack) {
        this.exams = exams;
        this.clickCallBack = clickCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_test_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(exams.get(position), clickCallBack);
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.examName)
        TextView examName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(Exam exam, ClickCallBack clickCallBack) {
            examName.setText(exam.getName());
            examName.setOnClickListener(v -> {
                clickCallBack.onClickEvent(examName, exam);
            });
        }
    }
}
