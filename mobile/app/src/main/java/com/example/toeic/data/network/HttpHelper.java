package com.example.toeic.data.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpHelper {
    private static Retrofit retrofit;
    private static String token = "";

    static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = createRetrofit(token);
        }
        return retrofit;
    }

    public static void initRetrofit(String accessToken) {
        token = accessToken;
        createRetrofit(token);
    }

    private static Retrofit createRetrofit(String token) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().addInterceptor(interceptor);
        okHttpClientBuilder.addInterceptor(chain -> {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(newRequest);
        }).build();
        return new Retrofit.Builder()
                .client(okHttpClientBuilder.build())
                .baseUrl("http://192.168.0.102:8080")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
