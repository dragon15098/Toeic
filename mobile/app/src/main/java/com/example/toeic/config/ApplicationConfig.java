package com.example.toeic.config;

import android.app.Application;


public class ApplicationConfig extends Application {
    private static ApplicationConfig mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static ApplicationConfig getInstance() {
        return mInstance;
    }

}
