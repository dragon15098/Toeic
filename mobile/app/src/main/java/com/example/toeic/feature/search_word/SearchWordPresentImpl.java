package com.example.toeic.feature.search_word;

import androidx.appcompat.view.menu.BaseMenuPresenter;

import com.example.base.BasePresenterImpl;
import com.example.toeic.R;
import com.example.toeic.config.ApplicationConfig;
import com.example.toeic.data.model.Word;
import com.example.toeic.data.network.Service;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class SearchWordPresentImpl extends BasePresenterImpl implements SearchWordPresent {
    private List<Word> suggestions = new ArrayList<>();

    @Override
    public void findSuggestWord(String searchWord) {
        callApi(Service.callWordService()
                .getSuggestWord(searchWord, 0, 10)
                .compose(Service.getRxSingleSchedulers().applySchedulers())
                .subscribeWith(new DisposableSingleObserver<List<Word>>() {
                    @Override
                    public void onSuccess(List<Word> words) {
                        getSuggestWordSuccess(words);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                })

        );

    }

    @Override
    public List<Word> getSuggestWord() {
        return suggestions;
    }

    private void getSuggestWordSuccess(List<Word> words) {
        suggestions.clear();
        if (!words.isEmpty()) {
            suggestions.addAll(words);
        } else {
            suggestions.add(Word.builder().word(ApplicationConfig.getInstance().getString(R.string.word_not_found)).build());
        }
        SearchWordView searchWordView = (SearchWordView) view;
        searchWordView.notifyDisplay();
    }
}
