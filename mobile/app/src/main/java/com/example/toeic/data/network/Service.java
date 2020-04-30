package com.example.toeic.data.network;

import com.example.toeic.data.network.api.UserService;

public class Service {
    private static UserService userService;

    public static RxSingleSchedulers getRxSingleSchedulers() {
        return RxSingleSchedulers.DEFAULT;
    }

    public static UserService callUserService() {
        return HttpHelper.getClient().create(UserService.class);
    }

}
