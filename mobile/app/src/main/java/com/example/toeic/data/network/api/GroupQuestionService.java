package com.example.toeic.data.network.api;

import com.example.toeic.data.model.GroupQuestion;
import com.example.toeic.data.model.Question;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GroupQuestionService {
    @GET("/api/group_question/findPartThreeByExamId/{id}")
    Single<List<GroupQuestion>> findPartThreeByExamId(@Path("id") Integer examId);

    @GET("/api/group_question/findPartFourByExamId/{id}")
    Single<List<GroupQuestion>> findPartFourByExamId(@Path("id") Integer examId);
}
