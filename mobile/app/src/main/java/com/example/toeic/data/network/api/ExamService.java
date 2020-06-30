package com.example.toeic.data.network.api;

import com.example.toeic.data.model.Exam;
import com.example.toeic.data.model.User;
import com.example.toeic.data.network.LoginResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ExamService {
    @GET("api/exam/getAll")
    Single<List<Exam>> getAllExam();

    @GET("api/exam/getAllCorrectAnswer/{id}")
    Single<List<Integer>> getAllCorrectAnswer(@Path("id") Integer examId);

}
