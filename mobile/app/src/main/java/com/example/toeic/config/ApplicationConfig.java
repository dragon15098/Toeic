package com.example.toeic.config;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.toeic.data.model.Result;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

import static com.example.toeic.ultis.Constraints.EMPTY_STRING;
import static com.example.toeic.ultis.Constraints.RESULT;


public class ApplicationConfig extends Application {
    private static ApplicationConfig mInstance;

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        sharedPreferences = getSharedPreferences("TOEIC", MODE_PRIVATE);
    }

    public void saveResult(Result result) {
        Gson gson = new Gson();
        sharedPreferences.edit().putString(RESULT, gson.toJson(result)).apply();
    }

    public Result getOldResult() {
        Gson gson = new Gson();
        String string = sharedPreferences.getString(RESULT, EMPTY_STRING);
        return gson.fromJson(string, Result.class);
    }

    public static ApplicationConfig getInstance() {
        return mInstance;
    }

}
