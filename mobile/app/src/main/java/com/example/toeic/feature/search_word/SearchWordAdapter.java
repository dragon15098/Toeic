package com.example.toeic.feature.search_word;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Word;
import com.example.toeic.ultis.ClickCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchWordAdapter extends RecyclerView.Adapter<SearchWordAdapter.ViewHolder> {

    List<Word> wordList = new ArrayList<>();

    ClickCallBack clickCallBack;

    public void setClickCallBack(ClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public void setWordList(List<Word> words) {
        wordList = words;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_word_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(wordList.get(position), clickCallBack);
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.word)
        TextView word;

        @BindView(R2.id.us)
        TextView us;

        @BindView(R2.id.wordCard)
        CardView wordCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(Word word, ClickCallBack clickCallBack) {
            this.word.setText(word.getWord());
            us.setText(word.getUs());
            wordCard.setOnClickListener(v -> clickCallBack.onClickEvent(this.word, word));

        }
    }
}
