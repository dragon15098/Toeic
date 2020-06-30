package com.example.toeic.feature.practice.part_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Part;
import com.example.toeic.ultis.ClickCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PartAdapter extends RecyclerView.Adapter<PartAdapter.ExamViewHolder> {

    private List<Part> parts = new ArrayList<>();
    private ClickCallBack clickCallBack;

    PartAdapter(ClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
        parts.add(Part.builder()
                .partId(1)
                .name(Part.PartName.PART_ONE.getValue())
                .drawable(Part.PartIcon.PART_ONE.getValue()).build());
        parts.add(Part.builder()
                .partId(2)
                .name(Part.PartName.PART_TWO.getValue())
                .drawable(Part.PartIcon.PART_TWO.getValue()).build());
        parts.add(Part.builder()
                .partId(3)
                .name(Part.PartName.PART_THREE.getValue())
                .drawable(Part.PartIcon.PART_THREE.getValue()).build());
        parts.add(Part.builder()
                .partId(4)
                .name(Part.PartName.PART_FOUR.getValue())
                .drawable(Part.PartIcon.PART_FOUR.getValue()).build());
        parts.add(Part.builder()
                .partId(5)
                .name(Part.PartName.PART_FIVE.getValue())
                .drawable(Part.PartIcon.PART_FIVE.getValue()).build());
        parts.add(Part.builder()
                .partId(6)
                .name(Part.PartName.PART_SIX.getValue())
                .drawable(Part.PartIcon.PART_SIX.getValue()).build());
        parts.add(Part.builder()
                .partId(7)
                .name(Part.PartName.PART_SEVEN.getValue())
                .drawable(Part.PartIcon.PART_SEVEN.getValue()).build());
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_part_item, parent, false);
        return new ExamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        if (position * 2 + 1 < parts.size()) {
            holder.bindView(parts.get(position * 2), parts.get(position * 2 + 1));
        } else {
            holder.bindView(parts.get(position * 2), null);
        }
    }

    @Override
    public int getItemCount() {
        if (parts.size() % 2 == 0) {
            return parts.size() / 2;
        } else {
            return parts.size() / 2 + 1;
        }
    }

    class ExamViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.partImageLeft)
        ImageView imagePartLeft;


        @BindView(R2.id.partNameLeft)
        TextView partNameLeft;


        @BindView(R2.id.countProcessLeft)
        TextView countProcessLeft;


        @BindView(R2.id.partImageRight)
        ImageView imagePartRight;


        @BindView(R2.id.partNameRight)
        TextView partNameRight;


        @BindView(R2.id.countProcessRight)
        TextView countProcessRight;

        @BindView(R2.id.cardViewLeft)
        CardView cardViewLeft;

        @BindView(R2.id.cardViewRight)
        CardView cardViewRight;

        ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(Part partLeft, Part partRight) {
            setUpPartLeft(partLeft);
            if (partRight != null) {
                setUpPartRight(partRight);
            } else {
                cardViewRight.setVisibility(View.INVISIBLE);
            }
        }

        private void setUpPartLeft(Part partLeft) {
            partNameLeft.setText(partLeft.name);
            imagePartLeft.setImageDrawable(partLeft.getDrawable());
            cardViewLeft.setOnClickListener(v -> clickCallBack.onClickEvent(cardViewLeft, partLeft));
        }

        private void setUpPartRight(Part partRight) {
            partNameRight.setText(partRight.name);
            imagePartRight.setImageDrawable(partRight.getDrawable());
            cardViewRight.setOnClickListener(v -> clickCallBack.onClickEvent(cardViewLeft, partRight));
        }
    }
}
