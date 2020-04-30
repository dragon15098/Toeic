package com.example.toeic.data.network.api;

import com.example.toeic.data.model.User;
import com.example.toeic.data.network.LoginResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @POST("api/login")
    Single<LoginResponse> login(@Body User user);

}
