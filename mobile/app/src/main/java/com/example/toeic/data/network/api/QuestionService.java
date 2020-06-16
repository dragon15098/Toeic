package com.example.toeic.data.network.api;

import com.example.toeic.data.model.Question;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.*;

public interface QuestionService {
    @GET("/api/question/findPartOneByExamId/{id}")
    Single<List<Question>> findPartOneByExamId(@Path("id") Integer examId);

    @GET("/api/question/findPartTwoByExamId/{id}")
    Single<List<Question>> findPartTwoByExamId(@Path("id") Integer examId);

}
