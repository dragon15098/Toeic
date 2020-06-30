package com.example.toeic.data.network.api;

import com.example.toeic.data.model.Question;
import com.example.toeic.data.model.Word;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WordService {
    @GET("/api/word/getSuggestWord/{word}")
    Single<List<Word>> getSuggestWord(@Path("word") String word,
                                      @Query("pageNumber") Integer pageNumber,
                                      @Query("pageSize") Integer pageSize);
}
