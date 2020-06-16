package com.example.toeic.feature.exam.part;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Exam;
import com.example.toeic.ultis.ClickCallBack;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PartAdapter extends RecyclerView.Adapter<PartAdapter.ViewHolder> {
    private List<Exam> exams;
    private ClickCallBack clickCallBack;

    PartAdapter(List<Exam> data, ClickCallBack clickCallBack) {
        this.exams = data;
        this.clickCallBack = clickCallBack;
    }

    @NonNull
    @Override
    public PartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_part_one_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(exams.get(position));
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.examName)
        TextView textView;

        @BindView(R2.id.examCard)
        CardView cardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(Exam exam) {
            textView.setText(exam.getName());
            cardView.setOnClickListener(v -> {
                clickCallBack.onClickEvent(cardView, exam);
            });
        }
    }
}
