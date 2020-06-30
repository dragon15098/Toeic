package com.example.toeic.feature.search_word;

import com.example.base.BasePresenter;
import com.example.toeic.data.model.Word;

import java.util.List;

public interface SearchWordPresent extends BasePresenter {
    void findSuggestWord(String searchWord);

    List<Word> getSuggestWord();
}
