package com.example.toeic.feature.search_word;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.BaseFragment;
import com.example.toeic.R;
import com.example.toeic.R2;
import com.example.toeic.data.model.Word;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static com.example.toeic.data.network.HttpHelper.SERVICE_RESOURCE;
import static com.example.toeic.ultis.Constraints.EMPTY_STRING;

public class SearchWordFragment extends BaseFragment implements SearchWordView {

    @BindView(R2.id.editText)
    EditText editText;

    @BindView(R2.id.webView)
    WebView webView;

    SearchWordPresent searchWordPresent;

    @BindView(R2.id.usVoice)
    ImageView usVoice;

    @BindView(R2.id.usText)
    TextView usText;

    MediaPlayer mediaPlayerUs;

    @BindView(R2.id.ukVoice)
    ImageView ukVoice;

    @BindView(R2.id.ukText)
    TextView ukText;

    MediaPlayer mediaPlayerUk;

    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_search_word, container, false);
        if (v != null) {
            ButterKnife.bind(this, v);
        }
        setUpPresent();
        setUpEditText();
        setUpRecycleView();
        return v;
    }

    public void setUpMp3Us(Word word) {
        String us = word.getUsMp3();
        if (us != null && !us.equals(EMPTY_STRING)) {
            String url = SERVICE_RESOURCE + word.getUsMp3();
            mediaPlayerUs = new MediaPlayer();
            mediaPlayerUs.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayerUs.setDataSource(url);
                mediaPlayerUs.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            usVoice.setVisibility(View.VISIBLE);
            usVoice.setOnClickListener(v -> mediaPlayerUs.start());
        } else {
            usVoice.setVisibility(GONE);
        }
    }

    public void setUpMp3Uk(Word word) {
        String uk = word.getUkMp3();
        if (uk != null && !uk.equals(EMPTY_STRING)) {
            String url = SERVICE_RESOURCE + word.getUkMp3();
            mediaPlayerUk = new MediaPlayer();
            mediaPlayerUk.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayerUk.setDataSource(url);
                mediaPlayerUk.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ukVoice.setVisibility(View.VISIBLE);
            ukVoice.setOnClickListener(v -> mediaPlayerUk.start());
        } else {
            ukVoice.setVisibility(GONE);
        }
    }


    private void setUpEditText() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                showRecycleView();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Handler handler = new Handler();
                handler.postDelayed(() -> searchWordPresent.findSuggestWord(s.toString()), 200);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setUpRecycleView() {
        SearchWordAdapter searchWordAdapter = new SearchWordAdapter();
        searchWordAdapter.setClickCallBack((view, object) -> showWordDetail((Word) object));
        recyclerView.setAdapter(searchWordAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showWordDetail(Word word) {
        hideRecycleView();
        webView.loadData(word.getDescription(), "text/html;  charset=utf-8", "utf-8");
        setUpMp3Us(word);
        showUsText(word);
        setUpMp3Uk(word);
        showUkText(word);
    }


    private void showUsText(Word word) {
        String us = word.getUs();
        if (us != null && !us.equals(EMPTY_STRING)) {
            usText.setVisibility(View.VISIBLE);
            usText.setText(us);
        } else {
            usText.setVisibility(GONE);
        }
    }

    private void showUkText(Word word) {
        String uk = word.getUs();
        if (uk != null && !uk.equals(EMPTY_STRING)) {
            ukText.setVisibility(View.VISIBLE);
            ukText.setText(uk);
        } else {
            ukText.setVisibility(GONE);
        }
    }

    private void hideRecycleView() {
        recyclerView.setVisibility(GONE);
    }

    private void showRecycleView() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void setUpPresent() {
        searchWordPresent = new SearchWordPresentImpl();
        searchWordPresent.attachView(this);
    }

    @Override
    public void notifyDisplay() {
        SearchWordAdapter searchWordAdapter = (SearchWordAdapter) recyclerView.getAdapter();
        if (searchWordAdapter != null) {
            searchWordAdapter.setWordList(searchWordPresent.getSuggestWord());
            searchWordAdapter.notifyDataSetChanged();
        }
    }
}
